package samaritano.inject.binder;

import samaritano.inject.Injector;
import samaritano.inject.Key;
import samaritano.inject.Provider;

final class ProviderBinding<T> extends AbstractBinding<T> {

	static <T> ProviderBinding<T> get(Key<T> key, Provider<T> provider) {
		return new ProviderBinding<>(key, provider);
	}

	private final Provider<T> provider;

	ProviderBinding(Key<T> key, Provider<T> provider) {
		super(key);
		this.provider = provider;
	}

	@Override
	public T instanceOf(Key<T> key, Injector injector) throws Exception {
		return provider.provide();
	}

}