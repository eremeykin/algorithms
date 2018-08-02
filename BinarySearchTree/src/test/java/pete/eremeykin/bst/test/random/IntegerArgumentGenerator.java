package pete.eremeykin.bst.test.random;

import java.util.Random;

public class IntegerArgumentGenerator extends ArgumentGenerator<Integer> {

	public IntegerArgumentGenerator(double prob) {
		super(prob);
	}
	
	public static IntegerArgumentGenerator fromExisting(ArgumentGenerator<Integer> iag, double prob) {
		IntegerArgumentGenerator newIag = new IntegerArgumentGenerator(prob);
		newIag.oldValues = iag.oldValues;
		return newIag;
	}

	private static Random random = new Random(20502L);

	@Override
	Integer generateNew() {
		return random.nextInt(10000);
	}

}
