package samaritano.inject;

@FunctionalInterface
public interface Provider<T> {

	T provide() throws Exception;

}