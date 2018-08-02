package pete.eremeykin.bst;

import static java.util.Objects.requireNonNull;

import java.util.ArrayDeque;
import java.util.Queue;

public class BstImpl<K extends Comparable<K>, V> implements Bst<K, V> {

	private Node root;

	private class Node {
		private K key;
		private V value;
		private Node left;
		private Node rigth;
		private int N;

		private Node(K key, V value, int N) {
			this.key = key;
			this.value = value;
			this.N = N;
		}

	}

	public int size() {
		return size(this.root);
	}

	private int size(Node root) {
		if (root == null) {
			return 0;
		}
		return root.N;
	}

	public V get(K key) {
		if (size() == 0) {
			return null;
		}
		Node extructed = get(root, requireNonNull(key));
		if (extructed != null) {
			return extructed.value;
		}
		return null;
	}

	private Node get(Node current, K key) {
		if (current == null) {
			return null;
		}
		int cmp = key.compareTo(current.key);
		if (cmp > 0) {
			return get(current.rigth, key);
		} else if (cmp < 0) {
			return get(current.left, key);
		} else
			return current;
	}

	public void put(K key, V value) {
		root = put(root, key, value);
	}

	public Node put(Node current, K key, V value) {
		if (current == null) {
			return new Node(key, value, 1);
		}
		int cmp = key.compareTo(current.key);
		if (cmp > 0) {
			current.rigth = put(current.rigth, key, value);
		} else if (cmp < 0) {
			current.left = put(current.left, key, value);
		} else {
			current.value = value;
		}
		current.N = size(current.left) + size(current.rigth) + 1;
		return current;
	}

	public K min() {
		Node result = min(root);
		return result == null ? null : result.key;
	}

	private Node min(Node current) {
		if (current.left != null) {
			return min(current.left);
		}
		return current;
	}

	public K max() {
		Node result = max(root);
		return result == null ? null : result.key;
	}

	private Node max(Node current) {
		if (current != null) {
			return max(current.rigth);
		}
		return current;
	}

	public K floor(K key) {
		key = requireNonNull(key);
		BstImpl<K, V>.Node result = floor(root, key);
		return result == null ? null : result.key;
	}

	private Node floor(Node current, K key) {
		if (current == null) {
			return null;
		}
		int cmp = key.compareTo(current.key);
		if (cmp < 0) {
			return floor(current.left, key);
		} else if (cmp > 0) {
			Node result = floor(current.rigth, key);
			return result == null ? current : result;
		} else {
			return current;
		}
	}

	public K ceiling(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	public K select(int rank) {
		Node result = select(root, rank);
		return result == null ? null : result.key;
	}

	private Node select(Node current, int rank) {
		if (current == null) {
			return null;
		}
		int t = size(current.left);
		if (t > size(current)) {
			return select(current.left, rank);
		} else if (t < size(current)) {
			return select(current.rigth, rank - t - 1);
		} else {
			return current;
		}
	}

	public int rank(K key) {
		return rank(root, key);
	}

	private int rank(Node current, K key) {
		if (current == null) {
			return 0;
		}
		int cmp = key.compareTo(current.key);
		if (cmp < 0) {
			return rank(current.left, key);
		} else if (cmp > 0) {
			return current.left.N + 1 + rank(current.rigth, key);
		} else {
			return current.left.N;
		}
	}

	public void deleteMin() {
		root = deleteMin(root);
	}

	private Node deleteMin(Node current) {
		if (current.left == null) {
			return current.rigth;
		}
		current.left = deleteMin(current.left);
		current.N = size(current.left) + size(current.rigth) + 1;
		return current;

	}

	public void delete(K key) {
		root = delete(root, key);
	}

	public Node delete(Node current, K key) {
		if (current == null) {
			return null;
		}
		int cmp = key.compareTo(current.key);
		if (cmp < 0) {
			current.left = delete(current.left, key);
		} else if (cmp > 0) {
			current.rigth = delete(current.rigth, key);
		} else {
			if (current.rigth == null) {
				return current.left;
			}
			if (current.left == null) {
				return current.rigth;
			}
			Node t = current;
			current = min(t.rigth);
			current.rigth = deleteMin(t.rigth);
			current.left = t.left;
		}
		current.N = size(current.left) + size(current.rigth) + 1;
		return current;
	}

	public Iterable<K> keys(K lo, K hi) {
		Queue<K> queue = new ArrayDeque<K>();
		keys(root, lo, hi, queue);
		return queue;
	}

	private void keys(Node current, K lo, K hi, Queue<K> queue) {
		int loCmp = lo.compareTo(current.key);
		int hiCmp = hi.compareTo(current.key);
		if (loCmp < 0) {
			keys(current.left, lo, hi, queue);
		}
		if (loCmp <= 0 && hiCmp >= 0) {
			queue.add(current.key);
		}
		if (hiCmp < 0) {
			keys(current.rigth, lo, hi, queue);
		}
	}

	@Override
	public String toString() {
		return String.format("Bst with size = %s", this.size());
	}

}
