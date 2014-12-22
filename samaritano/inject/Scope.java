package samaritano.inject;

public interface Scope {

	<T> T apply(Key<T> key, T object);

	<T> T resolve(Key<T> key);

}