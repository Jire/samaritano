package samaritano.inject;

import java.util.HashMap;
import java.util.Map;

public enum Scopes implements Scope {

	DEFAULT,

	SINGLETON {

		private final Map<Class<?>, Object> singletons = new HashMap<>();

		@Override
		public <T> T apply(Key<T> key, T object) {
			if (!singletons.containsKey(key.type()))
				singletons.put(key.type(), object);
			return object;
		}

		@Override
		@SuppressWarnings("unchecked")
		public <T> T resolve(Key<T> key) {
			Object object = singletons.get(key.type());
			return object == null ? null : (T) object;
		}

	};

	@Override
	public <T> T apply(Key<T> key, T object) {
		return object;
	}

	@Override
	public <T> T resolve(Key<T> key) {
		return null;
	}

}