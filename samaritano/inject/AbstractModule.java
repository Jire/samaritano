package samaritano.inject;

import samaritano.affirm.Affirm;
import samaritano.inject.binder.ConstantBindingBuilder;
import samaritano.inject.binder.LinkedBindingBuilder;
import samaritano.inject.binder.ProviderBindingBuilder;

public abstract class AbstractModule implements Module {
	
	private Binder binder;

	@Override
	public final void configure(Binder binder) {
		Affirm.truth(this.binder == null);
		this.binder = binder;
		
		try {
			configure();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	protected abstract void configure();
	
	protected final <T> LinkedBindingBuilder<T> bind(Class<T> type) {
		return binder.bind(type);
	}

	protected final <T> ConstantBindingBuilder<T> bind(T value) {
		return binder.bind(value);
	}
	
	protected final <T> ProviderBindingBuilder<T> bind(Provider<T> provider) {
		return binder.bind(provider);
	}
	
}