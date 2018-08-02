package pete.eremeykin.bst.test.framework;

import pete.eremeykin.bst.Bst;

public class SynchronousBstModifier<K extends Comparable<K>, V> extends BstWrapper<K, V> {

	private Bst<K, V> robust;

	public SynchronousBstModifier(Bst<K, V> underTest, Bst<K, V> robust) {
		super(underTest);
		this.robust = robust;
	}

	@Override
	public void put(K key, V value) {
		real.put(key, value);
		robust.put(key, value);
	}

	@Override
	public void deleteMin() {
		real.deleteMin();
		robust.deleteMin();
	}

	@Override
	public void delete(K key) {
		real.delete(key);
		robust.delete(key);
	}
	
	

	public Bst<K, V> getRobust() {
		return robust;
	}

	public Bst<K, V> getReal() {
		return real;
	}
	
	

}
