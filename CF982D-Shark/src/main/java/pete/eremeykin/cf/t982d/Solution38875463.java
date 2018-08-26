package pete.eremeykin.cf.t982d;

import java.util.*;
import java.io.*;

public class Solution38875463 {

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new
                    InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    static Pair[] array;
    static int[] left, right;
    static int res, reslocs, collocs, mxlocs, colmxlocs;
    static boolean locs;

    public static void main(String... args) {
        FastReader reader = new FastReader();

        array=null; left=null; right=null;
        res=0; reslocs=0; collocs =0; mxlocs =0; colmxlocs=0;
        locs = false;

        int n = reader.nextInt();
        Pair[] array = new Pair[n + 2];
        left = new int[n + 2];
        right = new int[n + 2];

        for (int i = 1; i <= n; i++) {
            int temp = reader.nextInt();
            array[i] = new Pair(temp, i);
        }

        Arrays.sort(array, 1, n + 1);

        for (int i = 1; i <= n; i++) {
            add(array[i].index);

            if (locs && collocs > reslocs) {
                reslocs = collocs;
                res = array[i].value + 1;
            }
        }

        System.out.println(res);
    }

    static void add(int u) {
        int l = u, r = u;

        if (left[u - 1] != 0) {
            collocs--;
            l = left[u - 1];
        }
        if (right[u + 1] != 0) {
            collocs--;
            r = right[u + 1];
        }

        collocs++;
        left[r] = l;
        right[l] = r;

        if (r - l + 1 > mxlocs) {
            mxlocs = r - l + 1;
            colmxlocs = 0;
        }

        if (r - l + 1 == mxlocs)
            colmxlocs++;

        if (colmxlocs == collocs)
            locs = true;
        else
            locs = false;
    }
}


class Pair implements Comparable<Pair> {
    int value;
    int index;

    Pair(int v, int i) {
        value = v;
        index = i;
    }

    public int compareTo(Pair temp) {
        return Integer.compare(this.value, temp.value);
    }
}