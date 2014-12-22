package samaritano.logging;

public interface Logger {

	void info(Object message);

	void error(Object message);

	void warning(Object message);

	void debug(Object message);

	Level level();

	void setLevel(Level level);

}