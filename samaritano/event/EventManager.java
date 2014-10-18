package samaritano.event;

public interface EventManager {

	void register(EventListener listener);

	void dispatch(Event event);

}