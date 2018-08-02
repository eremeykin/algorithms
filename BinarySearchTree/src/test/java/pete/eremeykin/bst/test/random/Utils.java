package pete.eremeykin.bst.test.random;

import java.util.Random;

public class Utils {

	static Random random = new Random(34513L);
	
	public static boolean probBoolean(double prob) {
		int p = (int) (prob * 100);
		if (p > 100) {
			p = 100;
		}
		int trial = random.nextInt(101);
		return trial > p;
	}
}
