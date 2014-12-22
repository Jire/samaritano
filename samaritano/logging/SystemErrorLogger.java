package samaritano.logging;

final class SystemErrorLogger extends AbstractLogger {

	SystemErrorLogger() {
		super(Level.ALL);
	}

	@Override
	protected void showLog(Object message, Level level) {
		System.err.println(message);
	}

}