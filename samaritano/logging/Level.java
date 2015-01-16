package samaritano.logging;

public enum Level {

	ALL(0),
	
	INFO(1),
	
	WARNING(2),
	ERROR(2),
	
	DEBUG(3),
	
	OFF(4);
	
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