package samaritano.inject.binder;

import samaritano.inject.Injector;
import samaritano.inject.Key;
import samaritano.inject.Scope;

final class ScopedBinding<T> extends AbstractBinding<T> {

	static <T> ScopedBinding<T> get(ImplementationBinding<T> binding, Scope scope) {
		return new ScopedBinding<T>(binding, scope);
	}

	private final ImplementationBinding<T> binding;
	private final Scope scope;

	private ScopedBinding(ImplementationBinding<T> binding, Scope scope) {
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