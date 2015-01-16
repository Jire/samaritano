package samaritano.logging;

abstract class AbstractLogger implements Logger {

	private final Outputter outputter;

	private Level level;

	AbstractLogger(Outputter outputter, Level defaultLevel) {
		this.outputter = outputter;
		this.level = defaultLevel;
	}

	private final Logger log(Object message, Level level) {
		if (level().shouldLog(level))
			outputter.showLog(message, level);
		return this;
	}

	@Override
	public final Logger info(Object message) {
		return log(message, Level.INFO);
	}

	@Override
	public final Logger error(Object message) {
		return log(message, Level.ERROR);
	}

	@Override
	public final Logger warn(Object message) {
		return log(message, Level.WARNING);
	}

	@Override
	public final Logger debug(Object message) {
		return log(message, Level.DEBUG);
	}

	@Override
	public final Level level() {
		return level;
	}

	@Override
	public final Logger setLevel(Level level) {
		this.level = level;
		return this;
	}

}