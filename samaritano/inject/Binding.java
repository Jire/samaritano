package samaritano.inject;

public interface Binding<T> {

	Key<T> key();

	T instanceOf(Key<T> key, Injector injector) throws Exception;

}