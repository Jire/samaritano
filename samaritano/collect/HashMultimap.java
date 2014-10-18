package samaritano.collect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public final class HashMultimap<K, V> extends AbstractMultimap<K, V> {

	public static <K, V> Multimap<K, V> create() {
		return new HashMultimap<>();
	}

	private HashMultimap() {
		super(new HashMap<>());
	}

	@Override
	protected Collection<V> generateCollection() {
		return new ArrayList<>();
	}

}