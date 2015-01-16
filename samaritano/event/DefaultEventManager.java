package samaritano.event;

import static java.util.stream.Stream.of;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import samaritano.inject.Injector;

public final class DefaultEventManager implements EventManager {

	private final SubscriberRegistry registry = SubscriberRegistry.create(this);
	private final Set<Injector> injectors = new HashSet<>();

	@Override
	public void register(EventListener listener) {
		registry.register(listener);
	}

	@Override
	public void register(Injector injector) {
		injectors.add(injector);
	}

	@Override
	public void post(Event event) {
		registry.findSubscribers(event).forEach(subscriber -> {
			try {
				Class<?>[] types = subscriber.parameters();
				Object[] parameters = new Object[types.length];
				if (types.length > 1 || !event.getClass().equals(types[0])) {
					for (int i = 0; i < types.length; i++)
						parameters[i] = scout(event, types[i]);
				} else parameters[0] = event;
				subscriber.method().invoke(subscriber.listener(), parameters);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

	@SuppressWarnings("unchecked")
	private <T> T scout(Event event, Class<?> parameter)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		Class<? extends Event> type = event.getClass();

		// Return event if parameter requires it
		if (parameter.equals(type))
			return (T) event;

		// Check for available methods
		Optional<Method> method = findMethod(type, parameter);
		if (method.isPresent()) {
			method.get().setAccessible(true);
			return (T) method.get().invoke(event);
		}

		// Check for available fields
		Optional<Field> field = findField(type, parameter);
		if (field.isPresent()) {
			field.get().setAccessible(true);
			return (T) field.get().get(event);
		}

		// Finally, check the injectors for any matches
		Optional<Object> object = findObject(type, parameter);
		if (object.isPresent())
			return (T) object.get();

		// We can't find anything, shouldn't be possible.
		throw new NullPointerException();
	}

	private static Optional<Field> findField(Class<? extends Event> t, Class<?> p) {
		return of(t.getDeclaredFields()).filter(f -> p.equals(f.getType())
						&& f.isAnnotationPresent(Provide.class)).findFirst();
	}

	private static Optional<Method> findMethod(Class<? extends Event> t, Class<?> p) {
		return of(t.getDeclaredMethods()).filter(m -> m.getParameterCount() == 0 
						&& p.equals(m.getReturnType())
						&& m.isAnnotationPresent(Provide.class)).findFirst();
	}
	
	private Optional<Object> findObject(Class<? extends Event> t, Class<?> p) {
		Iterator<?> iterator = injectors.stream().map(i -> {
			return i.instanceOf(p);
		}).collect(Collectors.toSet()).iterator();
		while (iterator.hasNext()) {
			Object result = iterator.next();
			if (result != null)
				return Optional.of(result);
		}
		return Optional.empty();
	}

}