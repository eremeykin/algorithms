package tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import InsertionSort.InsertionSort;

import org.junit.Test;
import org.junit.runner.RunWith;
import api.Sort;

@RunWith(Parameterized.class)
public class IntegerSortTest {

	private Sort<Integer> sortInstance;

	public IntegerSortTest(Sort<Integer> sortInstance) {
		this.sortInstance = sortInstance;
	}

	@Test
	public void testRandom() {
		Random rand = new Random(4654851545l);
		int count = rand.nextInt(500) + 50;
		// int count = 0;
		Integer[] array = new Integer[count];
		for (int i = 0; i < count; i++) {
			array[i] = rand.nextInt(100);
		}
		Integer[] initialArray = Arrays.copyOf(array, array.length);
		array = sortInstance.sort(array);
		String message = "Initial array: ";
		for (int i = 0; i < initialArray.length; i++)
			message += initialArray[i] + " ";
		message += "\nNew Array:    ";
		for (int i = 0; i < array.length; i++) {
			message += array[i] + " ";
		}
		System.out.println(message);
		for (int i = 0; i < initialArray.length - 1; i++) {
			assert array[i].compareTo(array[i + 1]) >= 0 : "Wrong order:" + array[i] + "<" + array[i + 1];
		}
		Arrays.sort(initialArray, (Integer x, Integer y) -> y.compareTo(x));
		assert initialArray.length == array.length : "Wrong array length";
		System.out.println(Arrays.toString(initialArray));
		for (int i = 0; i < initialArray.length; i++) {
			assert initialArray[i] == array[i];
		}
	}

	@SuppressWarnings("rawtypes")
	@Parameters
	public static Collection instancesToTest() {
		ArrayList<Sort<Integer>> list = new ArrayList<Sort<Integer>>();
		list.add(new InsertionSort<Integer>());
		return list;
	};
}
