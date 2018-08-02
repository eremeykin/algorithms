package api;

public interface MaxSubArray {

	Result run(int[] input);

	public class Result {

		int sum, start, end;

		public Result(int start, int end, int sum) {
			this.start = start;
			this.end = end;
			this.sum = sum;
		}

		void setSum(int sum) {
			this.sum = sum;
		}

		void setStart(int start) {
			this.start = start;
		}

		void setEnd(int end) {
			this.end = end;
		}

		int getSum() {
			return sum;
		}

		int getStart() {
			return start;
		}

		int getEnd() {
			return end;
		}

	}
}
