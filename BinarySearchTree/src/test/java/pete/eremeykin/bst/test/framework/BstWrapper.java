package pete.eremeykin.bst.test.framework;

import pete.eremeykin.bst.Bst;

public class BstWrapper<K extends Comparable<K>, V> implements Bst<K, V> {

	protected Bst<K, V> real;

	public BstWrapper(Bst<K, V> bst) {
		this.real = bst;
	}

	public int size() {
		
		return real.size();
	}

	public V get(K key) {
		return real.get(key);
	}

	public void put(K key, V value) {
		real.put(key, value);
	}

	public K min() {
		return real.min();
	}

	public K max() {
		return real.max();
	}

	public K floor(K key) {
		return real.floor(key);
	}

	public K ceiling(K key) {
		return real.ceiling(key);
	}

	public K select(int rank) {
		return real.select(rank);
	}

	public int rank(K key) {
		return real.rank(key);
	}

	public void deleteMin() {
		real.deleteMin();
	}

	public void delete(K key) {
		real.delete(key);
	}

	public Iterable<K> keys(K lo, K hi) {
		return real.keys(lo, hi);
	}

}
