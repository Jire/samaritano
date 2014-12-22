package samaritano.logging;

public final class Logging {

	private Logging() {
	}

	private static final Logger logger = new SystemErrorLogger();

	public static Logger logger() {
		return logger;
	}

}