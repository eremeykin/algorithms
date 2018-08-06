package pete.eremeykin.cf.t147a;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SimpleTest {

	private ByteArrayOutputStream myOut;

	@Before
	public void setUp() {
		myOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(myOut));
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testRoboCopy() {
		char[] left = "test String".toCharArray();
		char[] right = " second String".toCharArray();
		char[] res = Solution.roboMerge(left, right);
		assertArrayEquals(res, "test String second String".toCharArray());
	}

	@Test
	public void testSample1() throws IOException {
		String input = "galileo galilei was an   italian physicist  ,mathematician,astronomer";
		String expected = "galileo galilei was an italian physicist, mathematician, astronomer\n";
		System.setIn(new ByteArrayInputStream(input.getBytes()));

		Solution.main(input);
		String actual = myOut.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void testSample2() throws IOException {
		String input = "galileo  was  born  in  pisa\n";
		String expected = "galileo was born in pisa\n";
		System.setIn(new ByteArrayInputStream(input.getBytes()));

		Solution.main(input);
		String actual = myOut.toString();
		assertEquals(expected, actual);
	}

}
