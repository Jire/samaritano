package samaritano.inject.binder;

import samaritano.inject.Binder;

abstract class AbstractBindingBuilder {

	private final Binder binder;

	AbstractBindingBuilder(Binder binder) {
		this.binder = binder;
	}

	protected final Binder binder() {
		return binder;
	}

}