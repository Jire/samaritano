package samaritano.event;

import samaritano.inject.Injector;

public interface EventManager {

	void register(EventListener listener);

	void register(Injector injector);

	void post(Event event);

}