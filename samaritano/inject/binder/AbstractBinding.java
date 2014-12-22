package samaritano.inject.binder;

import samaritano.inject.Binding;
import samaritano.inject.Key;

abstract class AbstractBinding<T> implements Binding<T> {

	private final Key<T> key;

	AbstractBinding(Key<T> key) {
		this.key = key;
	}

	@Override
	public final Key<T> key() {
		return key;
	}
	
}