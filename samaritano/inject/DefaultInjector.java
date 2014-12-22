package samaritano.inject;

final class DefaultInjector extends AbstractInjector {

	DefaultInjector(Binder binder) {
		super(binder);
	}

	@Override
	public <T> T instanceOf(Key<T> key) {
		Binding<T> binding = binder().getBinding(key);
		if (binding != null) {
			try {
				return binding.instanceOf(key, this);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		try {
			return key.type().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

}