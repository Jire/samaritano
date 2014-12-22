package samaritano.inject.binder;

import samaritano.inject.Binder;
import samaritano.inject.Scope;

public final class ScopedBindingBuilder<T> extends TypedBindingBuilder<T> {

	public static <T> ScopedBindingBuilder<T> get(Binder binder, Class<T> type,
			ImplementationBinding<T> binding) {
		return new ScopedBindingBuilder<T>(binder, type, binding);
	}

	private final ImplementationBinding<T> binding;

	private ScopedBindingBuilder(Binder binder, Class<T> type,
			ImplementationBinding<T> binding) {
		super(binder, type);
		this.binding = binding;
	}

	public void in(Scope scope) {
		binder().bind(ScopedBinding.get(binding, scope));
	}

}