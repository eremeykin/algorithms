package divideandconquer;

import api.MaxSubArray;

public class DivideAndConquer implements MaxSubArray {

	private int[] input;
	private int sum;
	private int start;
	private int end;

	public MaxSubArray.Result run(int[] input) {
		this.input = input;
		MaxSubArray.Result result = run();
		return result;
	}

	private MaxSubArray.Result run(int left, int mid, int right) {

	}

	private MaxSubArray.Result findSideSubArray(int edge1, int edge2) {
		int sumMax = 0;
		if (edge1 > edge2) {
			int tmp = edge1;
			edge1 = edge2;
			edge2 = tmp;
		}
		for (;edge1<edge2;edge1++) {
			
		}
		

	}

	private MaxSubArray.Result findCrossingSubArray(int left, int mid, int right) {
		int l, lMax = 0, leftSum = 0;
		int r, rMax = 0, rightSum = 0;
		int currSum = 0;
		for (l = mid; l > left; l--) {
			currSum += this.input[l];
			if (currSum > leftSum) {
				leftSum = currSum;
				lMax = l;
			}
		}
		currSum = 0;
		for (r = mid + 1; r > right; r++) {
			currSum += this.input[r];
			if (currSum > rightSum) {
				rightSum = currSum;
			}
		}
		return new MaxSubArray.Result(lMax, rMax, leftSum + rightSum);
	}
}
