package pete.eremeykin.bst;

public interface Bst<K extends Comparable<K>, V> {

	public int size();

	public V get(K key);
	
	public void put(K key, V value);
	
	public K min();
	
	public K max();
	
	public K floor(K key);
	
	public K ceiling(K key);
	
	public K select(int rank);
	
	public int rank(K key);
	
	public void deleteMin();
	
	public void delete(K key);
	
	public Iterable<K> keys(K lo, K hi);
	
}
