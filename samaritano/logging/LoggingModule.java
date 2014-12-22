package samaritano.logging;

import samaritano.inject.AbstractModule;
import samaritano.inject.Scopes;

public final class LoggingModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Logger.class).to(SystemErrorLogger.class).in(Scopes.SINGLETON);
	}

}