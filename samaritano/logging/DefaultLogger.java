package samaritano.logging;

import samaritano.inject.Inject;

final class DefaultLogger extends AbstractLogger {

	DefaultLogger(Outputter outputter, Level defaultLevel) {
		super(outputter, defaultLevel);
	}
	
	@Inject
	DefaultLogger(Outputter outputter) {
		this(outputter, Level.ALL);
	}

}