package samaritano.inject.binder;

import samaritano.inject.Binder;
import samaritano.inject.Key;

public final class LinkedBindingBuilder<T> extends TypedBindingBuilder<T> {

	public static <T> LinkedBindingBuilder<T> get(Binder binder, Class<T> type) {
		return new LinkedBindingBuilder<>(binder, type);
	}

	private LinkedBindingBuilder(Binder binder, Class<T> type) {
		super(binder, type);
	}

	public ScopedBindingBuilder<T> to(Class<? extends T> implementation) {
		ImplementationBinding<T> binding = ImplementationBinding.get(
				Key.of(type()), Key.of(implementation));
		binder().bind(binding);
		return ScopedBindingBuilder.get(binder(), type(), binding);
	}

	public ScopedBindingBuilder<T> toSelf() {
		return to(type());
	}

}