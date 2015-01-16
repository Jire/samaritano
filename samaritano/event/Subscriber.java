package samaritano.event;

import java.lang.reflect.Method;

final class Subscriber {

	static Subscriber create(EventManager manager, EventListener listener, Method method, Class<?>[] parameters) {
		return new Subscriber(manager, listener, method, parameters);
	}

	private final EventManager manager;
	private final EventListener listener;
	private final Method method;
	private final Class<?>[] parameters;

	private Subscriber(EventManager manager, EventListener listener, Method method, Class<?>[] parameters) {
		this.manager = manager;
		this.listener = listener;
		this.method = method;
		this.parameters = parameters;
	}

	EventManager manager() {
		return manager;
	}

	EventListener listener() {
		return listener;
	}

	Method method() {
		return method;
	}
	
	Class<?>[] parameters() {
		return parameters;
	}

}