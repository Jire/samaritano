package samaritano.inject;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import samaritano.inject.binder.ConstantBindingBuilder;
import samaritano.inject.binder.LinkedBindingBuilder;
import samaritano.inject.binder.ProviderBindingBuilder;

abstract class AbstractBinder implements Binder {

	private final Map<Key<?>, Binding<?>> bindings = new HashMap<>();

	@Override
	public final Map<Key<?>, Binding<?>> bindings() {
		return bindings;
	}

	@Override
	@SuppressWarnings("unchecked")
	public final <T> Binding<T> getBinding(Key<T> key) {
		return (Binding<T>) bindings().get(key);
	}

	@Override
	@SuppressWarnings("unchecked")
	public final <T> Collection<Binding<T>> getBindings(Class<T> type) {
		Set<Binding<T>> results = new HashSet<>();
		bindings.entrySet().stream()
				.filter(e -> e.getKey().type().equals(type))
				.forEach(e -> results.add((Binding<T>) e.getValue()));
		return results;
	}

	@Override
	public final <T> void bind(Binding<T> binding) {
		bindings().put(binding.key(), binding);
	}

	@Override
	public final <T> LinkedBindingBuilder<T> bind(Class<T> type) {
		return LinkedBindingBuilder.get(this, type);
	}

	@Override
	public final <T> ConstantBindingBuilder<T> bind(T value) {
		return ConstantBindingBuilder.get(this, value);
	}

	@Override
	public final <T> ProviderBindingBuilder<T> bind(Provider<T> provider) {
		return ProviderBindingBuilder.get(this, provider);
	}

	@Override
	public final void install(Module module) {
		module.configure(this);
	}

}