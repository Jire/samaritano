package samaritano.inject.binder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;

import samaritano.Primitives;
import samaritano.inject.Inject;
import samaritano.inject.Injector;
import samaritano.inject.Key;

final class ImplementationBinding<T> extends AbstractBinding<T> {

	static <T> ImplementationBinding<T> get(Key<T> key,
			Key<? extends T> implementation) {
		return new ImplementationBinding<>(key, implementation);
	}

	private final Key<? extends T> implementation;

	private ImplementationBinding(Key<T> key, Key<? extends T> implementation) {
		super(key);
		this.implementation = implementation;
	}

	@Override
	public T instanceOf(Key<T> type, Injector injector) throws Exception {
		Constructor<T> constructor = findConstructor(implementation.type());
		Class<?>[] types = constructor.getParameterTypes();
		Annotation[][] annotations = constructor.getParameterAnnotations();
		Object[] parameters = new Object[types.length];
		parameters: for (int i = 0; i < parameters.length; i++) {
			Class<?> parameter = Primitives.wrap(types[i]);
			if (annotations.length > 0 && annotations[i].length > 0) {
				for (Annotation annotation : annotations[i])
					parameters[i] = injector.instanceOf(Key.of(parameter, annotation.annotationType()));
				continue parameters;
			}
			parameters[i] = injector.instanceOf(parameter);
		}
		return constructor.newInstance(parameters);
	}

	@SuppressWarnings("unchecked")
	private Constructor<T> findConstructor(Class<? extends T> implementation)
			throws NoSuchMethodException, SecurityException {
		for (Constructor<?> constructor : implementation.getConstructors())
			if (constructor.isAnnotationPresent(Inject.class))
				return (Constructor<T>) constructor;
		return (Constructor<T>) implementation.getConstructor();
	}

}