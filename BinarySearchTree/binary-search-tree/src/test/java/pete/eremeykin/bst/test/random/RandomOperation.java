package pete.eremeykin.bst.test.random;

import java.util.EnumMap;
import java.util.Random;

import pete.eremeykin.bst.test.framework.Methods;

public class RandomOperation {

	private static Random random = new Random(3465345L);

	public static Methods generate(EnumMap<Methods, Double> probs) {
		int rand = random.nextInt(101);
		int cutoff = 0;
		for (Methods m : Methods.values()) {
			int rangeLength = 0;
			try {
				rangeLength = (int) (probs.get(m) * 100);
			} catch (NullPointerException e) {

			}
			if (cutoff <= rand && rand <= cutoff + rangeLength) {
				return m;
			}
			cutoff += rangeLength;
		}
		return Methods.PUT;
	}

}
