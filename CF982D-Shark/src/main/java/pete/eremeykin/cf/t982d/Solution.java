package pete.eremeykin.cf.t982d;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

    private Day[] days;

    static class Day implements Comparable<Day> {
        int index;
        int value;

        private Day(int index, int value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Day{" +
                    "index=" + index +
                    ", value=" + value +
                    '}';
        }

        @Override
        public int compareTo(Day o) {
            return this.value - o.value;
        }
    }

    public class DisjoinedSet {

        int[] parent;
        int[] rank; // height of the tree
        int[] locLength;

        public DisjoinedSet(int capacity) {
            parent = new int[capacity];
            rank = new int[capacity];
            locLength = new int[capacity];
        }

        public void makeSet(int dayIndex) {
            parent[dayIndex] = dayIndex;
            rank[dayIndex] = 1;
            locLength[dayIndex] = 1;
        }

        public void union(int c1, int c2) {
            int f1 = find(c1);
            int f2 = find(c2);
            if (f1 == f2)
                return;
            int old = locLength[f1];
            locLength[f1] += locLength[f2];
            locLength[f2] += old;

            if (rank[f1] > rank[f2])
                parent[f2] = f1;
            else {
                parent[f1] = f2;
                if (rank[f1] == rank[f2])
                    rank[f2]++;
            }
        }

        int find(int dayIndex) {
            if (dayIndex < 0 || dayIndex >= parent.length || rank[dayIndex] == 0)
                return -1;
            int c = dayIndex;
            while (parent[c] != c) { // find root
                c = parent[c];
            }
            int root = c;
            c = dayIndex;
            while (parent[c] != root) { // compress path
                int nextC = parent[c];
                parent[c] = root;
                c = nextC;
            }
            return c;
        }

        int len(int dayIndex) {
            int f = find(dayIndex);
            if (f < 0 || f >= parent.length)
                return -1;
            return locLength[f]; // rank is height of the tree, not the location length!
        }

    }

    private Integer call() {
        Arrays.sort(days);

        int maxLocLength = 0;
        int numLocWithMaxLength = 0;
        int numLocTotal = 0;
        int bestK = 0;
        int bestNumLoc = 0;

        DisjoinedSet ds = new DisjoinedSet(days.length);

        for (int i = 0; i < days.length; i++) {
            ds.makeSet(days[i].index);
            numLocTotal++;
            int left = ds.find(days[i].index - 1); // the index of a day that is one position left
            if (left != -1) {
                ds.union(days[i].index, left);
                numLocTotal--;
            }
            int right = ds.find(days[i].index + 1); // the index of a day that is one position right
            if (right != -1) {
                ds.union(days[i].index, right);
                numLocTotal--;
            }
            int l = ds.len(days[i].index);
            if (l > maxLocLength) {
                maxLocLength = l;
                numLocWithMaxLength = 0;
            }
            if (l == maxLocLength)
                numLocWithMaxLength++;
            if (numLocTotal > bestNumLoc && numLocWithMaxLength == numLocTotal) {
                bestK = days[i].value;
                bestNumLoc = numLocTotal;
            }
        }
        return bestK + 1;
    }

    public static void main(String... args) throws IOException {
        Solution s = new Solution();
        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(r);
        int n = Integer.valueOf(br.readLine());
        Scanner scanner = new Scanner(br.readLine());
        s.days = new Day[n];
        for (int i = 0; i < s.days.length; i++) {
            s.days[i] = new Day(i, scanner.nextInt());
        }
        String result = s.call().toString();
        System.out.println(result);
    }

}
