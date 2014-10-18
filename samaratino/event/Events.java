package samaritano.event;

public final class Events {

	private static final EventManager manager = new DefaultEventManager();

	public static EventManager manager() {
		return manager;
	}

}