package BinarySearch;

import java.util.*;

public class TimeBasedKeyValueStore {

    private static class Pair {
        private int timestamp;
        private String value;

        Pair(int timestamp, String value) {
            this.timestamp = timestamp;
            this.value = value;
        }
    }

    private HashMap<String, List<Pair>> hashMap;

    public TimeBasedKeyValueStore() {
        hashMap = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if (!hashMap.containsKey(key)) {
            hashMap.put(key, new ArrayList<>());
        }
        List<Pair> arr = hashMap.get(key);
        arr.add(new Pair(timestamp, value));
    }

    public String get(String key, int timestamp) {
        // It's noted that timestamps are strictly increasing
        // To optimize the solution we can use BS to our advantage.
        String res = "";

        if (!hashMap.containsKey(key)) return res;

        List<Pair> arr = hashMap.get(key);
        int N = arr.size();

        int l = 0;
        int r = N - 1;

        while (l <= r) {
            int mid = (l + r) / 2;
            int ts = arr.get(mid).timestamp;
            if (ts == timestamp) return arr.get(mid).value;

            if (timestamp < ts) {
                r = mid - 1;
            } else {
                // It's given that if exact timestamp is
                // not there then we gotta pick the closest
                // smaller
                res = arr.get(mid).value;
                l = mid + 1;
            }
        }

        return res;
    }

}
