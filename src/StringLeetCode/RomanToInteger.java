package StringLeetCode;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {

    public int romanToInt(String s) {
        int N = s.length();
        // Create a hashMap outta mappings
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int res = 0;
        for (int i = 0; i < N; i++) {
            int curr = map.get(s.charAt(i));
            int next = (i + 1 < N) ? map.get(s.charAt(i + 1)) : 0;
            if (curr < next)
                res -= map.get(s.charAt(i));
            else
                res += map.get(s.charAt(i));
        }
        return res;
    }

}
