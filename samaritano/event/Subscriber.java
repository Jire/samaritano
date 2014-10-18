package samaritano.event;

import java.lang.reflect.Method;

final class Subscriber {

	static Subscriber create(EventManager manager, EventListener listener, Method method) {
		return new Subscriber(manager, listener, method);
	}

	private final EventManager manager;
	private final EventListener listener;
	private final Method method;

	private Subscriber(EventManager manager, EventListener listener, Method method) {
		this.manager = manager;
		this.listener = listener;
		this.method = method;
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

}