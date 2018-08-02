package pete.eremeykin.bst.test.random;

import java.util.Random;

public class StringArgumentGenerator extends ArgumentGenerator<String> {

	private static int LEN_MAX = 20;

	public StringArgumentGenerator(double prob) {
		super(prob);
	}

	private static Random random = new Random(9930L);

	@Override
	String generateNew() {
		int length = random.nextInt(LEN_MAX);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			char c = (char) (random.nextInt(123 - 97) + 97);
			sb.append(c);
		}
		return sb.toString();
	}

}
