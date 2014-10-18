package samaritano.affirm;

interface Affirmative<T> {

	boolean affirm(T reference, Object... parameters);

}