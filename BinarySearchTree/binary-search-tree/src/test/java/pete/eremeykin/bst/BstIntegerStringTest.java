package pete.eremeykin.bst;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static pete.eremeykin.bst.test.mathers.BstSizeMatcher.hasSize;
import static pete.eremeykin.bst.test.random.IntegerArgumentGenerator.fromExisting;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import pete.eremeykin.bst.test.random.ArgumentGenerator;
import pete.eremeykin.bst.test.random.IntegerArgumentGenerator;
import pete.eremeykin.bst.test.random.StringArgumentGenerator;

public class BstIntegerStringTest extends BstBaseTest<Integer, String> {

	private ArgumentGenerator<String> stringGenerator = new StringArgumentGenerator(0.75);
	private ArgumentGenerator<Integer> integerGenerator = new IntegerArgumentGenerator(0.75);
	private ArgumentGenerator<Integer> hFreqIntegerGenerator = fromExisting(integerGenerator, 0.05);
	private ArgumentGenerator<Integer> lFreqIntegerGenerator = fromExisting(integerGenerator, 0.05);

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

	@Override
	protected void put() {
		Integer key = integerGenerator.generate();
		String value = stringGenerator.generate();
		System.out.println(String.format("put(%s,%s)", key, value));
		this.sbModifier.put(key, value);
	}

	@Override
	protected void delete() {
		Integer key = integerGenerator.generate();
		System.out.println(String.format("delete(%s)", key));
		this.sbModifier.delete(key);
	}

	@Override
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

	@Override
	protected void get() {
		Integer key = hFreqIntegerGenerator.generate();
		String actualValue = this.sbModifier.get(key);
		String expectedValue = this.sbModifier.getRobust().get(key);
		System.out.println(
				String.format("actual.get(%s) = %s expected.get(%s) = %s", key, actualValue, key, expectedValue));
		Assert.assertThat(actualValue, equalTo(expectedValue));
	}

	@Override
	protected void min() {
		Integer actual = sbModifier.getReal().min();
		Integer expected = sbModifier.getRobust().min();
		System.out.println(String.format("actual.min() = %s expected.min() = %s", actual, expected));
		assertThat(actual, equalTo(expected));

	}

	@Override
	protected void floor() {
		Integer key = new Random(6345L).nextInt(100000);
		Integer actual = sbModifier.getReal().floor(key);
		Integer expected = sbModifier.getRobust().floor(key);
		System.out.println(String.format("actual.floor(%s) = %s expected.floor(%s) = %s", key, actual, key, expected));
		assertThat(actual, equalTo(expected));
	}

	@Override
	protected void select() {
		Integer rank = integerGenerator.generate();
		Integer actual = sbModifier.getReal().select(rank);
		Integer expected = sbModifier.getRobust().select(rank);
		System.out.println(
				String.format("actual.select(%s) = %s expected.select(%s) = %s", rank, actual, rank, expected));
		assertThat(actual, equalTo(expected));
	}

	@Override
	protected void rank() {
		Integer key = hFreqIntegerGenerator.generate();
		Integer actual = sbModifier.getReal().rank(key);
		Integer expected = sbModifier.getRobust().rank(key);
		System.out
				.println(String.format("actual.select(%s) = %s expected.select(%s) = %s", key, actual, key, expected));
		assertThat(actual, equalTo(expected));

	}

}
