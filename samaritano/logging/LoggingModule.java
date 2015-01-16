package samaritano.logging;

import samaritano.inject.AbstractModule;

public final class LoggingModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Outputter.class).to(Logging.systemErrorOutputter());
		bind(Logger.class).to(Logging.globalLogger());
	}

}