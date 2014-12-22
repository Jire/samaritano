package samaritano.inject;

abstract class AbstractInjector implements Injector {

	private final Binder binder;

	AbstractInjector(Binder binder) {
		this.binder = binder;
	}

	@Override
	public final <T> T instanceOf(Class<T> type) {
		return instanceOf(Key.of(type));
	}

	@Override
	public final Binder binder() {
		return binder;
	}

}