package samaritano.inject;

public interface Injector {

	<T> T instanceOf(Key<T> key);

	<T> T instanceOf(Class<T> type);

	Binder binder();

}