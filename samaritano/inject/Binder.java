package samaritano.inject;

import java.util.Collection;
import java.util.Map;

import samaritano.inject.binder.ConstantBindingBuilder;
import samaritano.inject.binder.LinkedBindingBuilder;
import samaritano.inject.binder.ProviderBindingBuilder;

public interface Binder {

	Map<Key<?>, Binding<?>> bindings();

	<T> Binding<T> getBinding(Key<T> key);

	<T> Collection<Binding<T>> getBindings(Class<T> type);

	<T> void bind(Binding<T> binding);

	<T> LinkedBindingBuilder<T> bind(Class<T> type);

	<T> ConstantBindingBuilder<T> bind(T value);

	<T> ProviderBindingBuilder<T> bind(Provider<T> provider);

	void install(Module module);

}