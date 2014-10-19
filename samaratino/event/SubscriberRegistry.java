package samaritano.event;

import static java.util.Collections.unmodifiableCollection;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import samaritano.affirm.Affirm;
import samaritano.collect.HashMultimap;
import samaritano.collect.Multimap;

final class SubscriberRegistry {

	static SubscriberRegistry create(EventManager manager) {
		return new SubscriberRegistry(manager);
	}

	private final EventManager manager;
	private final Multimap<Class<? extends Event>, Subscriber> subscribers =
																HashMultimap.create();

	private SubscriberRegistry(EventManager manager) {
		this.manager = manager;
	}

	void register(EventListener listener) {
		for (Method method : findSubscriptionMethods(listener)) {
			method.setAccessible(true);
			Class<?>[] parameters = method.getParameterTypes();
			@SuppressWarnings("unchecked")
			Class<? extends Event> event = (Class<? extends Event>) parameters[0];
			subscribers.put(event, Subscriber.create(manager, listener, method));
		}
	}

	Collection<Subscriber> findSubscribers(Event event) {
		return unmodifiableCollection(subscribers.get(event.getClass()));
	}

	private static Iterable<Method> findSubscriptionMethods(
			EventListener listener) {
		Set<Method> methods = new HashSet<>();
		for (Method method : listener.getClass().getMethods()) {
			if (!method.isAnnotationPresent(Subscribe.class))
				continue;
			Affirm.truth(method.getParameterCount() == 1);
			Class<?> event = method.getParameterTypes()[0];
			Affirm.truth(Event.class.isAssignableFrom(event));
			methods.add(method);
		}
		return methods;
	}

}