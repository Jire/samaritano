package samaritano.inject.binder;

import samaritano.inject.Binder;
import samaritano.inject.Binding;
import samaritano.inject.Key;
import samaritano.inject.Provider;

public final class LinkedBindingBuilder<T> extends TypedBindingBuilder<T> {

	public static <T> LinkedBindingBuilder<T> get(Binder binder, Class<T> type) {
		return new LinkedBindingBuilder<>(binder, type);
	}

	private LinkedBindingBuilder(Binder binder, Class<T> type) {
		super(binder, type);
	}

	public ScopedBindingBuilder<T> to(Class<? extends T> implementation) {
		Binding<T> binding = ImplementationBinding.get(Key.of(type()), Key.of(implementation));
		binder().bind(binding);
		return ScopedBindingBuilder.get(binder(), type(), binding);
	}

	public ScopedBindingBuilder<T> toSelf() {
		return to(type());
	}
	
	public ScopedBindingBuilder<T> to(Provider<T> provider) {
		Binding<T> binding = ProviderBinding.get(Key.of(type()), provider);
		binder().bind(binding);
		return ScopedBindingBuilder.get(binder(), type(), binding);
	}

	public void to(T value) {
		binder().bind(ConstantBinding.get(Key.of(type()), value));
	}

}