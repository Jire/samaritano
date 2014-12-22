package samaritano.logging;

abstract class AbstractLogger implements Logger {

	private Level level;

	protected AbstractLogger(Level defaultLevel) {
		level = defaultLevel;
	}

	protected abstract void showLog(Object message, Level level);

	private final Logger log(Object message, Level level) {
		if (level().shouldLog(level))
			showLog(message, level);
		return this;
	}

	@Override
	public final void info(Object message) {
		log(message, Level.INFO);
	}

	@Override
	public final void error(Object message) {
		log(message, Level.ERROR);
	}

	@Override
	public final void warning(Object message) {
		log(message, Level.WARNING);
	}

	@Override
	public final void debug(Object message) {
		log(message, Level.DEBUG);
	}

	@Override
	public final Level level() {
		return level;
	}

	@Override
	public final void setLevel(Level level) {
		this.level = level;
	}

}