package pete.eremeykin.cf.t147a;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {

	public String call(String input) {
		char[] chars = input.toCharArray();
		char[] charResult = format(input.toCharArray(), 0, chars.length);
		return String.valueOf(charResult);
	}

	private char[] format(char[] input, int lo, int hi) {
		if (hi - lo <= 1) {
			return new char[] { input[lo] };
		}
		int mid = (lo + hi) / 2;
		char[] left = format(input, lo, mid);
		char[] right = format(input, mid, hi);
		return merge(left, right);
	}

	private char[] merge(char[] left, char[] right) {
		char leftCh = left[left.length - 1];
		char rightCh = right[0];
		CharType leftType = CharType.typeOf(leftCh);
		CharType rightType = CharType.typeOf(rightCh);
		if (leftType == CharType.LETTER && rightType == CharType.SPACE) { // 1.3
			if (right.length > 1 && CharType.typeOf(right[1]) == CharType.MARK) {
				right = Arrays.copyOfRange(right, 1, right.length - 1);
			}
		}
		if (leftType == CharType.MARK && rightType == CharType.LETTER) { // 2.1
			char[] newRight = new char[right.length + 1];
			System.arraycopy(right, 0, newRight, 1, right.length);
			newRight[0] = ' ';
			right = newRight;
		}
		if (leftType == CharType.SPACE && rightType != CharType.LETTER) {// 3.2 & 3.3
			left = Arrays.copyOfRange(left, 0, left.length - 1);
		}
		return roboMerge(left, right);
	}

	static char[] roboMerge(char[] left, char[] right) {
		int N = left.length + right.length;
		char[] result = new char[N];
		System.arraycopy(left, 0, result, 0, left.length);
		System.arraycopy(right, 0, result, left.length, right.length);
		return result;
	}

	enum CharType {
		LETTER, MARK, SPACE;

		public static CharType typeOf(char ch) {
			int code = (int) ch;
			int aCode = (int) 'a';
			int zCode = (int) 'z';
			if (code >= aCode && code <= zCode) {
				return LETTER;
			}
			if (ch == '.' || code == ',' || code == '!' || code == '?') {
				return MARK;
			}
			if (code == ' ') {
				return SPACE;
			}
			return null;
		}
	}

	public static void main(String... args) throws IOException {
		InputStreamReader r = new InputStreamReader(System.in);
		String input = new BufferedReader(r).readLine();
		Solution s = new Solution();
		String result = s.call(input);
		System.out.println(result);
	}

}
