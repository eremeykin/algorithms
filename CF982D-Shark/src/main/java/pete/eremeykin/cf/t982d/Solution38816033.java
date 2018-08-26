package pete.eremeykin.cf.t982d;

import java.util.*;
import java.io.*;

public class Solution38816033 {


    public static void main(String... args) {
        JS scan = new JS();
        PrintWriter out = new PrintWriter(System.out);
        int n = scan.nextInt();
        Item[] arr = new Item[n];
        boolean[] checked = new boolean[n];
        DSU d = new DSU(n);
        for (int i = 0; i < n; i++) arr[i] = new Item(scan.nextInt(), i);
        Arrays.sort(arr);
        int numSegments = 0; //total number of segments
        int numLongest = 0; //total number of longest segments
        int maxLen = 0;
        int ans = Integer.MAX_VALUE;
        int ansNumSegs = 0;
        for (int i = 0; i < n; i++) {
            Item it = arr[i];
            checked[it.idx] = true;
            numSegments++;
            int left = it.idx - 1;
            if (left >= 0 && checked[left]) {
                d.union(it.idx, left);
                numSegments--;
            }
            int right = it.idx + 1;
            if (right < n && checked[right]) {
                d.union(it.idx, right);
                numSegments--;
            }
            int len = 0;
            int parent = d.find(it.idx);
            if (parent == it.idx) len = d.numkids[it.idx];
            else len = d.numkids[parent];
            if (len > maxLen) {
                numLongest = 1;
                maxLen = len;
            } else if (len == maxLen)
                numLongest++;
            if (numLongest == numSegments) {
                if (numSegments > ansNumSegs) {
                    ans = it.val + 1;
                    ansNumSegs = numSegments;
                }
            }
        }
        System.out.println(ans);
    }

    static class DSU {
        int[] s;
        int[] numkids;

        public DSU(int n) {
            numkids = new int[n];
            s = new int[n];
            for (int i = 0; i < n; i++) {
                s[i] = -1;
                numkids[i] = 1;
            }
        }

        public int find(int i) {
            return s[i] < 0 ? i : (s[i] = find(s[i]));
        }

        public boolean union(int a, int b) {
            if ((a = find(a)) == (b = find(b))) return false;
//			System.out.println("unioned "+a+" and "+b);
            if (s[a] == s[b]) s[a]--;
            if (s[a] <= s[b]) {
                s[b] = a;
                numkids[a] += numkids[b];
//				System.out.println("now "+a+" has "+numkids[a]+" children");
//				System.out.println(b+" had "+numkids[b]+" children");
            } else {
                s[a] = b;
                numkids[b] += 1 + numkids[a];
//				System.out.println("now "+b+" has "+numkids[b]+" children");
//				System.out.println(a+" had "+numkids[a]+" children");
            }
            return true;
        }
    }


    static class Item implements Comparable<Item> {

        int val;
        int idx;

        public Item(int v, int i) {
            this.val = v;
            this.idx = i;
        }

        @Override
        public int compareTo(Item o) {
            return this.val - o.val;
        }
    }

    static class JS {
        public int BS = 1 << 16;
        public char NC = (char) 0;
        byte[] buf = new byte[BS];
        int bId = 0, size = 0;
        char c = NC;
        double num = 1;
        BufferedInputStream in;

        public JS() {
            in = new BufferedInputStream(System.in, BS);
        }

        public JS(String s) throws FileNotFoundException {
            in = new BufferedInputStream(new FileInputStream(new File(s)), BS);
        }

        public char nextChar() {
            while (bId == size) {
                try {
                    size = in.read(buf);
                } catch (Exception e) {
                    return NC;
                }
                if (size == -1) return NC;
                bId = 0;
            }
            return (char) buf[bId++];
        }

        public int nextInt() {
            return (int) nextLong();
        }

        public long nextLong() {
            num = 1;
            boolean neg = false;
            if (c == NC) c = nextChar();
            for (; (c < '0' || c > '9'); c = nextChar()) {
                if (c == '-') neg = true;
            }
            long res = 0;
            for (; c >= '0' && c <= '9'; c = nextChar()) {
                res = (res << 3) + (res << 1) + c - '0';
                num *= 10;
            }
            return neg ? -res : res;
        }

        public double nextDouble() {
            double cur = nextLong();
            return c != '.' ? cur : cur + nextLong() / num;
        }

        public String next() {
            StringBuilder res = new StringBuilder();
            while (c <= 32) c = nextChar();
            while (c > 32) {
                res.append(c);
                c = nextChar();
            }
            return res.toString();
        }

        public String nextLine() {
            StringBuilder res = new StringBuilder();
            while (c <= 32) c = nextChar();
            while (c != '\n') {
                res.append(c);
                c = nextChar();
            }
            return res.toString();
        }

        public boolean hasNext() {
            if (c > 32) return true;
            while (true) {
                c = nextChar();
                if (c == NC) return false;
                else if (c > 32) return true;
            }
        }
    }
}

