package samaritano.inject;

import java.lang.annotation.Annotation;

import samaritano.Nullable;

public final class Key<T> {

	public static <T> Key<T> of(Class<T> type) {
		return of(type, null);
	}

	public static <T> Key<T> of(Class<T> type,
			@Nullable Class<? extends Annotation> annotation) {
		return new Key<T>(type, annotation);
	}

	private final Class<T> type;

	private Class<? extends Annotation> annotation;

	private Key(Class<T> type, @Nullable Class<? extends Annotation> annotation) {
		this.type = type;
		this.annotation = annotation;
	}

	public Class<T> type() {
		return type;
	}

	public Class<? extends Annotation> annotation() {
		return annotation;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		if (annotation != null)
			hash = annotation.hashCode();
		return type.hashCode() ^ hash;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Key))
			return false;
		Key<?> key = (Key<?>) o;
		return hashCode() == key.hashCode();
	}

}