package samaritano.collect;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

abstract class AbstractMultimap<K, V> implements Multimap<K, V> {

	private final Map<K, Collection<V>> map;

	protected AbstractMultimap(Map<K, Collection<V>> map) {
		this.map = map;
	}

	@Override
	public final Map<K, Collection<V>> asMap() {
		return Collections.unmodifiableMap(map);
	}

	protected abstract Collection<V> generateCollection();

	@Override
	public final void put(K key, V value) {
		Collection<V> collection = map.get(key);
		if (collection == null)
			collection = generateCollection();
		collection.add(value);
		map.put(key, collection);
	}

	@Override
	public final Collection<V> get(K key) {
		return map.get(key);
	}

	@Override
	public final int size() {
		int size = 0;
		for (Collection<V> collection : map.values())
			size += collection.size();
		return size;
	}

}