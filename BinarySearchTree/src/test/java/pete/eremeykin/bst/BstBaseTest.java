package pete.eremeykin.bst;

import static org.junit.Assert.assertThat;
import static pete.eremeykin.bst.test.mathers.BstSizeMatcher.hasSize;

import java.util.EnumMap;
import java.util.Random;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

import pete.eremeykin.bst.test.framework.Methods;
import pete.eremeykin.bst.test.framework.SynchronousBstModifier;
import pete.eremeykin.bst.test.framework.TreeMapToBstAdapter;
import pete.eremeykin.bst.test.random.RandomOperation;

public abstract class BstBaseTest<K extends Comparable<K>, V> {

	private static Random random = new Random(34456432L);
	Bst<K, V> underTestBst;
	Bst<K, V> robustBST;
	SynchronousBstModifier<K, V> sbModifier;

	@Before
	public void setUp() {
		underTestBst = new BstImpl<K, V>();
		robustBST = new TreeMapToBstAdapter<K, V>(new TreeMap<K, V>());
		sbModifier = new SynchronousBstModifier<K, V>(underTestBst, robustBST);
	}

	private static EnumMap<Methods, Double> getMap() {
		EnumMap<Methods, Double> map = new EnumMap<Methods, Double>(Methods.class);
		map.put(Methods.PUT, 0.4);
		map.put(Methods.DELETE, 0.1);
		map.put(Methods.DELETE_MIN, 0.1);
		map.put(Methods.MIN, 0.1);
		map.put(Methods.GET, 0.3);
//		map.put(Methods.FLOOR, 0.02);
//		map.put(Methods.SELECT, 0.04);
//		map.put(Methods.RANK, 0.04);
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
	

	protected abstract void put();

	protected abstract void delete();

	protected abstract void deleteMin();

	protected abstract void get();

	protected abstract void min();

	protected abstract void floor();

	protected abstract void select();

	protected abstract void rank();
}
