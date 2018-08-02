package pete.eremeykin.bst;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.EnumMap;
import java.util.Random;
import java.util.TreeMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static pete.eremeykin.bst.test.mathers.BstSizeMatcher.*;
import pete.eremeykin.bst.test.framework.Methods;
import pete.eremeykin.bst.test.framework.SynchronousBstModifier;
import pete.eremeykin.bst.test.framework.TreeMapToBstAdapter;
import pete.eremeykin.bst.test.random.ArgumentGenerator;
import pete.eremeykin.bst.test.random.IntegerArgumentGenerator;
import pete.eremeykin.bst.test.random.RandomOperation;
import pete.eremeykin.bst.test.random.StringArgumentGenerator;
import static pete.eremeykin.bst.test.random.IntegerArgumentGenerator.*;

public class BstTest{

	private static Random random = new Random(34456432L);
	Bst<Integer, String> bst;
	Bst<Integer, String> underTestBst;
	Bst<Integer, String> robustBST;
	SynchronousBstModifier<Integer, String> sbModifier;

	@Before
	public void setUp() {
		underTestBst = new BstImpl<Integer, String>();
		robustBST = new TreeMapToBstAdapter<Integer, String>(new TreeMap<Integer, String>());
		sbModifier = new SynchronousBstModifier<Integer, String>(underTestBst, robustBST);
	}

	private static EnumMap<Methods, Double> getMap() {
		EnumMap<Methods, Double> map = new EnumMap<Methods, Double>(Methods.class);
		map.put(Methods.PUT, 0.4);
		map.put(Methods.DELETE, 0.1);
		map.put(Methods.DELETE_MIN, 0.1);
		map.put(Methods.MIN, 0.1);
		map.put(Methods.GET, 0.3);
		return map;
	}

	@Test
	public void testSize() {
		int iterations = random.nextInt(3400) + 100;
		for (int c = 0; c < iterations; c++) {
			int operationsNumber = random.nextInt(500);
			for (int i = 0; i < operationsNumber; i++) {
				Methods method = RandomOperation.generate(getMap());
				switch (method) {
				case PUT:
					put();
					break;
				case DELETE:
					delete();
					break;
				case DELETE_MIN:
					deleteMin();
					break;
				case GET:
					get();
					break;
				case MIN:
					min();
					break;
				case FLOOR:
					floor();
					break;
				case RANK:
					rank();
					break;
				case SELECT:
					select();
					break;
				}
			}
		}
		Integer actualSize = robustBST.size();
		assertThat(underTestBst, hasSize(actualSize));
	};
	
	private ArgumentGenerator<String> stringGenerator = new StringArgumentGenerator(0.75);
	private ArgumentGenerator<Integer> integerGenerator = new IntegerArgumentGenerator(0.75);
	private ArgumentGenerator<Integer> hFreqIntegerGenerator = fromExisting(integerGenerator, 0.05);

	@Test
	public void test2() {
		sbModifier.put(1008, "vgthfnlczrfqwafp");
		sbModifier.put(2914, "ftkqozeep");
		sbModifier.put(6231, "igytvjcp");
		assertThat(sbModifier.getReal().get(6231), equalTo(sbModifier.getRobust().get(6231)));
		sbModifier.put(220, "zagvclr");
		sbModifier.put(5081, "nccwcmgpqk");
		assertThat(sbModifier.getReal().get(5081), equalTo(sbModifier.getRobust().get(5081)));
		assertThat(sbModifier.getReal().get(2914), equalTo(sbModifier.getRobust().get(2914)));
		sbModifier.delete(7489);
		assertThat(sbModifier.getReal().get(220), equalTo(sbModifier.getRobust().get(220)));
	}

	protected void put() {
		Integer key = integerGenerator.generate();
		String value = stringGenerator.generate();
		System.out.println(String.format("put(%s,%s)", key, value));
		this.sbModifier.put(key, value);
	}

	protected void delete() {
		Integer key = integerGenerator.generate();
		System.out.println(String.format("delete(%s)", key));
		this.sbModifier.delete(key);
	}

	protected void deleteMin() {
		Integer actual = sbModifier.getReal().min();
		Integer expected = sbModifier.getRobust().min();
		assertThat(actual, equalTo(expected));
		assertThat(sbModifier.getReal(), hasSize(sbModifier.getRobust().size()));
		System.out.println("deleteMin()");
		this.sbModifier.deleteMin();
		actual = sbModifier.getReal().min();
		expected = sbModifier.getRobust().min();
		assertThat(actual, equalTo(expected));
		assertThat(sbModifier.getReal(), hasSize(sbModifier.getRobust().size()));
	}

	protected void get() {
		Integer key = hFreqIntegerGenerator.generate();
		String actualValue = this.sbModifier.get(key);
		String expectedValue = this.sbModifier.getRobust().get(key);
		System.out.println(
				String.format("actual.get(%s) = %s expected.get(%s) = %s", key, actualValue, key, expectedValue));
		Assert.assertThat(actualValue, equalTo(expectedValue));
	}

	protected void min() {
		Integer actual = sbModifier.getReal().min();
		Integer expected = sbModifier.getRobust().min();
		System.out.println(String.format("actual.min() = %s expected.min() = %s", actual, expected));
		assertThat(actual, equalTo(expected));

	}

	protected void floor() {
		Integer key = new Random(6345L).nextInt(100000);
		Integer actual = sbModifier.getReal().floor(key);
		Integer expected = sbModifier.getRobust().floor(key);
		System.out.println(String.format("actual.floor(%s) = %s expected.floor(%s) = %s", key, actual, key, expected));
		assertThat(actual, equalTo(expected));
	}

	protected void select() {
		Integer rank = integerGenerator.generate();
		Integer actual = sbModifier.getReal().select(rank);
		Integer expected = sbModifier.getRobust().select(rank);
		System.out.println(
				String.format("actual.select(%s) = %s expected.select(%s) = %s", rank, actual, rank, expected));
		assertThat(actual, equalTo(expected));
	}

	protected void rank() {
		Integer key = hFreqIntegerGenerator.generate();
		Integer actual = sbModifier.getReal().rank(key);
		Integer expected = sbModifier.getRobust().rank(key);
		System.out
				.println(String.format("actual.select(%s) = %s expected.select(%s) = %s", key, actual, key, expected));
		assertThat(actual, equalTo(expected));

	}


}
