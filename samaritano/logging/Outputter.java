package samaritano.logging;

@FunctionalInterface
public interface Outputter {
	
	void showLog(Object message, Level level);

}