package mergesort;

import java.util.Arrays;

import api.Sort;

public class MergeSort<T extends Comparable<T>> implements Sort<T> {

	private T[] input;
	private T[] aux;

	@Override
	public T[] sort(T[] input) {
		this.input = input;
		aux = (T[]) new Comparable[input.length];
		sort(0, input.length - 1);
		return input;
	}

	private void sort(int i, int j) {
		System.out.println("sort [" + i + ";" + j + "] " + Arrays.toString(input));
		if (j <= i)
			return;
		int k = i + (j - i) / 2;
		sort(i, k);;
		sort(k + 1, j);
		merge(i, k, j);
	}

	private void merge(int i, int j, int k) {
		for (int r = i; r <= k; r++) {
			aux[r] = input[r];
		}
		int li = i, ri = j+1;
		for (int p = i; p <= k; p++) {
			if (li > j) {
				input[p] = aux[ri++];
			} else if (ri > k) {
				input[p] = aux[li++];
			} else if (aux[li].compareTo(aux[ri]) > 0) {
				input[p] = aux[li++];
			} else {
				input[p] = aux[ri++];
			}
		}
	}

}
