package pete.eremeykin.cf.t982d;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class SampleTest {

    private String input;
    private String expected;

    @Parameterized.Parameters(name = "sample {index}")
    public static Iterable<String[]> data() {
        return Arrays.asList(new String[][]{
                {"8\n1 2 7 3 4 8 5 6\n", "7\n"},
                {"6\n25 1 2 3 14 36\n", "2\n"}});
    }

    public SampleTest(String input, String expected) {
        this.input = input;
        this.expected = expected;
    }

    private ByteArrayOutputStream myOut;

    @Before
    public void setUp() {
        myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));
    }

    @Test
    public void testSample() throws IOException {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Solution.main(input);
        String actual = myOut.toString();
        assertEquals(expected, actual);
    }
}