package samaritano.inject.binder;

import samaritano.inject.Injector;
import samaritano.inject.Key;

final class ConstantBinding<T> extends AbstractBinding<T> {

	static <T> ConstantBinding<T> get(Key<T> key, T value) {
		return new ConstantBinding<>(key, value);
	}

	private final T value;

	private ConstantBinding(Key<T> key, T value) {
		super(key);
		this.value = value;
	}

	@Override
	public T instanceOf(Key<T> type, Injector injector) throws Exception {
		return value;
	}

}