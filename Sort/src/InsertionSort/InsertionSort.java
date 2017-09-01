package InsertionSort;

import api.Sort;

public class InsertionSort<T extends Comparable<T>> implements Sort<T> {

	private void swap(T[] array, int i1, int i2) {
		T temp;
		temp=array[i1];
		array[i1]=array[i2];
		array[i2]=temp;
	}
		
	@Override
	public T[] sort(T[] input) {
		for (int i=1;i<input.length;i++) {
			for (int j=i-1;j>=0 && input[j].compareTo(input[j+1])<0;j--) {
				swap(input,j,j+1);
			}
		}
		return input;
	}

}
