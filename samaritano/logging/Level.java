package samaritano.logging;

public enum Level {

	ALL(0),
	
	INFO(1),
	ERROR(2),
	WARNING(3),
	
	DEBUG(2),
	
	OFF(3);
	
	private final int value;
	
	Level(int value) {
		this.value = value;
	}
	
	public int value() {
		return value;
	}
	
	public boolean shouldLog(Level level) {
		return shouldLog(this, level);
	}
	
	public static boolean shouldLog(Level subject, Level level) {
		return subject.value() < level.value();
	}
	
}