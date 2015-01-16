package samaritano.inject.binder;

import samaritano.inject.Binder;
import samaritano.inject.Binding;
import samaritano.inject.Scope;

public final class ScopedBindingBuilder<T> extends TypedBindingBuilder<T> {

	public static <T> ScopedBindingBuilder<T> get(Binder binder, Class<T> type,
			Binding<T> binding) {
		return new ScopedBindingBuilder<T>(binder, type, binding);
	}

	private final Binding<T> binding;

	private ScopedBindingBuilder(Binder binder, Class<T> type,
			Binding<T> binding) {
		super(binder, type);
		this.binding = binding;
	}

	public void in(Scope scope) {
		binder().bind(ScopedBinding.get(binding, scope));
	}

}