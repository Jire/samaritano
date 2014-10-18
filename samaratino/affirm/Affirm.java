package samaritano.affirm;

public final class Affirm {

	private static final Affirmative<Object> NOT_NULL = new NotNullAffirmative<>();
	private static final Affirmative<Boolean> TRUTH = new TruthAffirmative();

	private static <T> void affirm(Affirmative<T> affirmative, T reference) {
		if (!affirmative.affirm(reference))
			throw new IllegalStateException();
	}

	public static void notNull(Object reference) {
		affirm(NOT_NULL, reference);
	}

	public static void truth(boolean reference) {
		affirm(TRUTH, reference);
	}

}