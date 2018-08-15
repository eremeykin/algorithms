package pete.eremeykin.cf.t982d;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    public String call(String input){
        char[] chars = input.toCharArray();
        char[] charResult = format(input.toCharArray(), 0, chars.length);
        return String.valueOf(charResult);
    }

    public static void main(String... args) throws IOException {
        InputStreamReader r = new InputStreamReader(System.in);
        String input = new BufferedReader(r).readLine();
        Solution s = new Solution();
        String result = s.call(input);
        System.out.println(result);
    }

}
