package samaritano.inject;

import samaritano.affirm.Affirm;
import samaritano.inject.binder.ConstantBindingBuilder;
import samaritano.inject.binder.LinkedBindingBuilder;

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
	
}