package samaritano.inject.binder;

import java.lang.annotation.Annotation;

import samaritano.inject.Binder;
import samaritano.inject.Key;

public final class ConstantBindingBuilder<T> extends AbstractBindingBuilder {

	public static <T> ConstantBindingBuilder<T> get(Binder binder, T value) {
		return new ConstantBindingBuilder<>(binder, value);
	}

	private final T value;

	private ConstantBindingBuilder(Binder binder, T value) {
		super(binder);
		this.value = value;
	}

	@SuppressWarnings("unchecked")
	public void to(Class<? extends Annotation> annotation) {
		binder().bind(ConstantBinding.get(
			Key.of((Class<T>) value.getClass(), annotation), value));
	}

}