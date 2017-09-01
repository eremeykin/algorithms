package api;

public interface Sort<T extends Comparable<T>> {
	
	T [] sort(T[] input);

}
