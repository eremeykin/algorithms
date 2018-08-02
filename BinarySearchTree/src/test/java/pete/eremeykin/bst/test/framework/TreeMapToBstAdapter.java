package pete.eremeykin.bst.test.framework;

import java.util.NoSuchElementException;
import java.util.TreeMap;

import pete.eremeykin.bst.Bst;

public class TreeMapToBstAdapter<Key extends Comparable<Key>, Value> implements Bst<Key, Value> {

	private TreeMap<Key, Value> map;

	public TreeMapToBstAdapter(TreeMap<Key, Value> map) {
		this.map = map;
	}

	public int size() {
		return map.size();
	}

	public Value get(Key key) {
		return map.get(key);
	}

	public void put(Key key, Value value) {
		map.put(key, value);
	}

	public Key min() {
		try {
			return map.firstKey();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	public Key max() {
		return map.lastKey();
	}

	public Key floor(Key key) {
		return map.floorKey(key);
	}

	public Key ceiling(Key key) {
		return map.ceilingKey(key);
	}

	public Key select(int rank) {
		throw new UnsupportedOperationException();
	}

	public int rank(Key key) {
		throw new UnsupportedOperationException();
	}

	public void deleteMin() {
		Key minKey = this.min();
		if (minKey != null) {
			map.remove(minKey);
		}
	}

	public void delete(Key key) {
		map.remove(key);
	}

	public Iterable<Key> keys(Key lo, Key hi) {
		return map.keySet();
	}

}
