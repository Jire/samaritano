package samaritano.event;

public final class DefaultEventManager implements EventManager {

	private final SubscriberRegistry registry = SubscriberRegistry.create(this);

	@Override
	public void register(EventListener listener) {
		registry.register(listener);
	}

	@Override
	public void post(Event event) {
		registry.findSubscribers(event).forEach(subscriber -> {
			try {
				subscriber.method().invoke(subscriber.listener(), event);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

}