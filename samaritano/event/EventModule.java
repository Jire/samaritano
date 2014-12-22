package samaritano.event;

import samaritano.inject.AbstractModule;
import samaritano.inject.Scopes;

public final class EventModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(EventManager.class).to(DefaultEventManager.class)
				.in(Scopes.SINGLETON);
	}

}