package pete.eremeykin.msa;

import java.util.Arrays;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class SimpleTest extends TestCase {

	private Msa msa;

	@Before
	public void setUp() {
		msa = new MsaImpl();
	}

	@Test
	public void testExample() {
		int[] input = new int[] { 13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7 };
		int[] result = msa.maxSubArray(input);
		int[] expected = new int[] { 18, 20, -7, 12 };
		Assert.assertArrayEquals(result, expected);
	}

	@Test
	public void testRandom() {
		for (int i = 0; i < 50; i++) {
			int[] array = genRandomInput();
			String as = Arrays.toString(array);
			System.out.println(as);
			int[] expected = new DumbMsa().maxSubArray(array);
			int[] actual = msa.maxSubArray(array);
			int scoreExpected = score(expected, 0, expected.length);
			int scoreActual = score(actual, 0, actual.length);
			Assert.assertEquals(scoreExpected, scoreActual);
		}
	}

	private int[] genRandomInput() {
		Random rand = new Random();
		int N = rand.nextInt(500) + 1;
		int[] result = new int[N];
		for (int i = 0; i < N; i++) {
			result[i] = rand.nextInt(100)-50;
		}
		return result;
	}

	private int score(int[] array, int start, int end) {
		int score = 0;
		for (int i = start; i < end; i++) {
			score += array[i];
		}
		return score;
	}

	private class DumbMsa implements Msa {

		public int[] maxSubArray(int[] array) {
			int maxStart = 0;
			int maxEnd = 0;
			int maxScore = 0;
			for (int i = 0; i < array.length; i++) {
				for (int j = i; j <= array.length; j++) {
					int score = score(array, i, j);
					if (score > maxScore) {
						maxStart = i;
						maxEnd = j;
						maxScore = score;
					}
				}
			}
			return Arrays.copyOfRange(array, maxStart, maxEnd);
		}

	}

}
