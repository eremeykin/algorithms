package pete.eremeykin.cf.t982d;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class SampleTest {

    private ByteArrayOutputStream myOut;

    @Before
    public void setUp() {
        myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));
    }


    @Test
    public void testSample1() {
        String input = "8\n" +
                "1 2 7 3 4 8 5 6\n";
        String expected = "7";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Solution.main(input);
        String actual = myOut.toString();
        assertEquals(expected, actual);


    }
}
