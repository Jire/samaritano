package samaritano.inject.binder;

import samaritano.inject.Binding;
import samaritano.inject.Injector;
import samaritano.inject.Key;
import samaritano.inject.Scope;

final class ScopedBinding<T> extends AbstractBinding<T> {

	static <T> ScopedBinding<T> get(Binding<T> binding, Scope scope) {
		return new ScopedBinding<T>(binding, scope);
	}

	private final Binding<T> binding;
	private final Scope scope;

	private ScopedBinding(Binding<T> binding, Scope scope) {
		super(binding.key());
		this.binding = binding;
		this.scope = scope;
	}

	@Override
	public T instanceOf(Key<T> key, Injector injector) throws Exception {
		T result = scope.resolve(key);
		if (result != null)
			return result;
		return scope.apply(key, binding.instanceOf(key, injector));
	}

}