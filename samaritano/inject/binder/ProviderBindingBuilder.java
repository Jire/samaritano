package samaritano.inject.binder;

import java.lang.annotation.Annotation;

import samaritano.inject.Binder;
import samaritano.inject.Key;
import samaritano.inject.Provider;

public final class ProviderBindingBuilder<T> extends AbstractBindingBuilder {

	public static <T> ProviderBindingBuilder<T> get(Binder binder,
			Provider<T> provider) {
		return new ProviderBindingBuilder<>(binder, provider);
	}

	private final Provider<T> provider;

	private ProviderBindingBuilder(Binder binder, Provider<T> provider) {
		super(binder);
		this.provider = provider;
	}

	@SuppressWarnings("unchecked")
	public void to(Class<? extends Annotation> annotation) {
		try {
			Class<T> type = (Class<T>) provider.getClass().getMethod("provide").getReturnType();
			if (type == null)
				throw new NullPointerException();
			binder().bind(ProviderBinding.get(Key.of(type, annotation), provider));
		} catch (NoSuchMethodException | SecurityException
				| NullPointerException e) {
			throw new Error(e);
		}
	}

}