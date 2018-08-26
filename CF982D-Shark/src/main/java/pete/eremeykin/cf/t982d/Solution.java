package pete.eremeykin.cf.t982d;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    Day[] days;
    Day[] order;


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
            if (value > o.value) {
                return -1;
            }
            if (value < o.value) {
                return 1;
            }
            return 0;
        }
    }

    class Location {
        Day startDay;
        Day endDay;
        int length;

        Location(Day startDay, Day endDay) {
            this.startDay = startDay;
            this.endDay = endDay;
            this.length = endDay.index - startDay.index + 1;
        }


        List<Location> splitAt(int index) {
            ArrayList<Location> result = new ArrayList<>();
            int left = index - 1;
            int right = index + 1;
            if (left > 0) {
                Location leftLoc = new Location(this.startDay, Solution.this.days[left]);
                if (leftLoc.length > 0)
                    result.add(leftLoc);
            }
            if (right < days.length) {
                Location rightLoc = new Location(days[right], this.endDay);
                if (rightLoc.length > 0)
                    result.add(rightLoc);
            }
            return result;
        }

        boolean contains(Day d) {
            return d.index >= startDay.index && d.index <= endDay.index;
        }

        boolean contains(int dayIndex) {
            return contains(days[dayIndex]);
        }

        @Override
        public String toString() {
            return "Location{" +
                    "startDay=" + startDay.index +
                    ", endDay=" + endDay.index +
                    ", length=" + length +
                    '}';
        }
    }

    static class K {
        int kValue;
        int locLen;
        int locNum;

        K(int kValue, int locLen, int locNum) {
            this.kValue = kValue;
            this.locLen = locLen;
            this.locNum = locNum;
        }

        @Override
        public String toString() {
            return "K{" +
                    "kValue=" + kValue +
                    ", locLen=" + locLen +
                    ", locNum=" + locNum +
                    '}';
        }
    }

    private Integer dumn() {
        Map<Integer, List<Location>> ks = new HashMap<>();
        for (int i = 0; i < days.length; i++) {
            Day day = days[i];
            int k = day.value;
            Integer start = null;
            Integer end = null;
            for (int j = 0; j < days.length; j++) {
                Day cday = days[j];
                if (cday.value < k) {
                    if (start == null)
                        start = j;
                } else {
                    if (start != null) {
                        Location loc = new Location(days[start], days[j - 1]);
                        ks.get(k);
                        start = null;
                    }
                }
            }
        }
        int bestK = Integer.MAX_VALUE;
        int maxLocs = 0;
        outer:
        for (Map.Entry<Integer, List<Location>> me : ks.entrySet()) {
            Integer currK = me.getKey();
            List<Location> locs = me.getValue();
            if (locs.isEmpty()) continue;
            int l = locs.get(0).length;
            for (int i = 0; i < locs.size(); i++) {
                Location location = locs.get(i);
                if (location.length != l)
                    continue outer;
            }
            if (locs.size() > maxLocs) {
                maxLocs = locs.size();
                bestK = currK;
            }
        }
        return bestK;
    }

    private Integer call() {
        order = new Day[days.length];
        System.arraycopy(days, 0, order, 0, days.length); //n
        Arrays.sort(order);// O(n log n)

        List<K> ks = new LinkedList<>();
        ks.add(new K(order[0].value + 1, days.length, 1));
        List<Location> locations = new LinkedList<>();
        locations.add(new Location(days[0], days[days.length - 1]));
        int kValue;
        for (int i = 0; i < days.length; i++) {
            kValue = order[i].value;
            Integer locLen = null;
            for (ListIterator<Location> iterator = locations.listIterator(); iterator.hasNext(); ) {
                Location next = iterator.next();
                if (next.contains(order[i])) {
                    iterator.remove();
                    for (Location nLoc : next.splitAt(order[i].index)) {
                        iterator.add(nLoc);
                        iterator.previous();
                    }
                    continue;
                }
                if (locLen == null) {
                    locLen = next.length;
                }
                if (locLen != next.length) {
                    locLen = -1;
                }
            }
            if (locLen != null && locLen > 0) {
                ks.add(new K(kValue, locLen, locations.size()));
            }
        }
        K bestK = ks.get(0);
        for (int i = 0; i < ks.size(); i++) {
            K k = ks.get(i);
            if (k.locNum >= bestK.locNum) {
                bestK = k;
            }
        }
        return bestK.kValue;
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
