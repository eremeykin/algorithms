package pete.eremeykin.msa;

import static java.util.Arrays.copyOfRange;

import java.util.Arrays;

public class MsaImpl implements Msa {

	private static class SubArray implements Comparable<SubArray> {
		private int[] array;
		private int start;
		private int end;
		private int sum;

		// including first excluding last
		SubArray(int[] array, int start, int end, int sum) {
			assert start <= end;
			this.array = array;
			this.start = start;
			this.end = end;
			this.sum = sum;
		}

		public int compareTo(SubArray other) {
			if (other.sum > this.sum) {
				return -1;
			}
			if (other.sum < this.sum) {
				return +1;
			}
			return 0;
		}

		public int[] asArray() {
			return copyOfRange(array, start, end);
		}

		public static SubArray merge(SubArray left, SubArray right) {
			assert left.end == right.start;
			return new SubArray(left.array, left.start, right.end, left.sum + right.sum);
		}

	}

	public int[] maxSubArray(int[] array) {
		return maxSubArray(array, 0, array.length).asArray();
	}

	private SubArray maxSubArray(int[] array, int left, int right) {
		if (right - left < 2) {
			SubArray empty = new SubArray(array, left, left, 0);
			SubArray simple = new SubArray(array, left, left + 1, array[left]);
			return empty.compareTo(simple) > 0 ? empty : simple;
		}
		int mid = (right + left) / 2;
		SubArray leftMax = maxSubArray(array, left, mid);
		SubArray rightMax = maxSubArray(array, mid, right);
		SubArray midMax = maxSubArrayMid(array, left, mid, right);
		SubArray maxSubArray = midMax;
		if (maxSubArray.compareTo(leftMax) < 0) {
			maxSubArray = leftMax;
		}
		if (maxSubArray.compareTo(rightMax) < 0) {
			maxSubArray = rightMax;
		}
		return maxSubArray;
	}

	private SubArray maxSubArrayMid(int[] array, int left, int mid, int right) {
		SubArray currentLeft = new SubArray(array, mid, mid, 0);
		SubArray leftMax = currentLeft;
		for (int i = mid - 1; i >= left; i--) {
			int newSum = currentLeft.sum + array[i];
			currentLeft = new SubArray(array, i, mid, newSum);
			if (currentLeft.compareTo(leftMax) > 0) {
				leftMax = currentLeft;
			}
		}
		SubArray currentRight = new SubArray(array, mid, mid, 0);
		SubArray rightMax = currentRight;
		for (int i = mid; i < right; i++) {
			int newSum = currentRight.sum + array[i];
			currentRight = new SubArray(array, mid, i+1, newSum);
			if (currentRight.compareTo(rightMax) > 0) {
				rightMax = currentRight;
			}
		}
		return SubArray.merge(leftMax, rightMax);
	}

}
