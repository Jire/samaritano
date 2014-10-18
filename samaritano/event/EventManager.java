package samaritano.event;

public interface EventManager {

	void register(EventListener listener);

	void post(Event event);

}