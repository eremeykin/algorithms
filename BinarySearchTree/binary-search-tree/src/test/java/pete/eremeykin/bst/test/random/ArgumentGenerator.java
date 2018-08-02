package pete.eremeykin.bst.test.random;

import static pete.eremeykin.bst.test.random.Utils.probBoolean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class ArgumentGenerator<T> {

	private double prob;
	protected List<T> oldValues = new ArrayList<T>();

	private static Random random = new Random(65223452L);

	public ArgumentGenerator(double prob) {
		this.prob = prob;
	}

	
	public T generate() {
		if (probBoolean(this.prob) && oldValues.size()>0) {
			int randomIndex = random.nextInt(oldValues.size());
			return oldValues.get(randomIndex);
		}
		T newElement = generateNew();
		oldValues.add(newElement);
		return newElement;
	}

	abstract T generateNew();
}
