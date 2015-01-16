package samaritano.logging;

public final class Logging {

	private Logging() {
	}

	private static final Outputter SYSTEM_ERROR_OUTPUTTER = new SystemErrorOutputter();
	
	public static Outputter systemErrorOutputter() {
		return SYSTEM_ERROR_OUTPUTTER;
	}
	
	// must be under SYSTEM_ERROR_OUTPUTTER or Java will pass null
	private static final Logger GLOBAL_LOGGER = logger();

	public static Logger globalLogger() {
		return GLOBAL_LOGGER;
	}
	
	public static Logger logger() {
		return logger(systemErrorOutputter());
	}

	public static Logger logger(Outputter outputter) {
		return new DefaultLogger(outputter);
	}
	
}