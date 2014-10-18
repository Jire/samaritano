package samaritano.affirm;

final class NotNullAffirmative<T> implements Affirmative<T> {

	@Override
	public boolean affirm(T reference, Object... values) {
		return reference != null;
	}

}