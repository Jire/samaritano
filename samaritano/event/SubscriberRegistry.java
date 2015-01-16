package samaritano.event;

import static java.util.Collections.unmodifiableCollection;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import samaritano.collect.HashMultimap;
import samaritano.collect.Multimap;

final class SubscriberRegistry {

	static SubscriberRegistry create(EventManager manager) {
		return new SubscriberRegistry(manager);
	}

	private final EventManager manager;
	private final Multimap<Class<? extends Event>, Subscriber> subscribers = HashMultimap
			.create();

	private SubscriberRegistry(EventManager manager) {
		this.manager = manager;
	}

	@SuppressWarnings("unchecked")
	void register(EventListener listener) {
		for (Method method : findSubscriptionMethods(listener)) {
			method.setAccessible(true);
			Class<?> event = method.getAnnotation(Subscribe.class).value();
			Class<?>[] parameters = method.getParameterTypes();
			if (NoSubscription.class.equals(event))
				event = parameters[0];
			subscribers.put((Class<? extends Event>) event,
					Subscriber.create(manager, listener, method, parameters));
		}
	}

	Collection<Subscriber> findSubscribers(Event event) {
		return unmodifiableCollection(subscribers.get(event.getClass()));
	}

	private static Iterable<Method> findSubscriptionMethods(EventListener listener) {
		Set<Method> methods = new HashSet<>();
		for (Method method : listener.getClass().getDeclaredMethods())
			if (method.isAnnotationPresent(Subscribe.class))
				methods.add(method);
		return methods;
	}

}