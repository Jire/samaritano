package samaritano.inject.binder;

import samaritano.inject.Binder;

abstract class TypedBindingBuilder<T> extends AbstractBindingBuilder {

	private final Class<T> type;

	protected TypedBindingBuilder(Binder binder, Class<T> type) {
		super(binder);
		this.type = type;
	}

	protected final Class<T> type() {
		return type;
	}

}