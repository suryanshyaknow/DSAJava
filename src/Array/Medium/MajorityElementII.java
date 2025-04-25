package Array.Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MajorityElementII {

    public List<Integer> majorityElement(int[] nums) {
        int N = nums.length;
        // First off, there'll be at max two elements
        // Reason: Say, n = 9
        // n/3 = 3
        // Implying a number's frequency should be more than 3, i.e. atleast 4
        // and since the size is 9, only two such numbers at max could be accommodated.

        // So we're gonna implement the Moore's voting algo but instead for
        // two numbers
        int majEle1 = nums[0];
        int count1 = 0;
        int majEle2 = Integer.MIN_VALUE;
        int count2 = 0;

        for (int i = 0; i < N; i++) {
            if (count1 == 0 && majEle2 != nums[i]) {
                majEle1 = nums[i];
                count1 = 1;
            } else if (count2 == 0 && majEle1 != nums[i]) {
                majEle2 = nums[i];
                count2 = 1;
            } else if (nums[i] == majEle1)
                count1++;
            else if (nums[i] == majEle2)
                count2++;
            else {
                count1--;
                count2--;
            }
        }

        List<Integer> res = new ArrayList<>();
        // Manual check for majEle1 & majEle2
        count1 = 0;
        count2 = 0;
        for (int i = 0; i < N; i++) {
            if (nums[i] == majEle1)
                count1++;
            else if (nums[i] == majEle2)
                count2++;
        }
        if (count1 > N / 3)
            res.add(majEle1);
        if (count2 > N / 3)
            res.add(majEle2);

        return res;
    }

    public List<Integer> majorityElementBruteForce(int[] nums) {
        int N = nums.length;
        // First off, there'll be at max two elements
        // Reason: Say, n = 9
        // n/3 = 3
        // Implying a number's frequency should be more than 3, i.e. atleast 4
        // and since the size is 9, only two such numbers at max could be accommodated.

        // Brute force approach employing the usage of above observation
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int majEle = nums[i];
            if (res.isEmpty() || !res.contains(majEle)) {
                int count = 1;
                for (int j = i + 1; j < N; j++) {
                    if (nums[j] == majEle) {
                        count += 1;
                    }
                }
                if (count > N / 3) {
                    res.add(majEle);
                }
            }
            if (res.size() == 2)
                break;
        }
        return res;
    }

    public List<Integer> majorityElementBetter(int[] nums) {
        int N = nums.length;
        // First off, there'll be at max two elements
        // Reason: Say, n = 9
        // n/3 = 3
        // Implying a number's frequency should be more than 3, i.e. atleast 4
        // and since the size is 9, only two such numbers at max could be accommodated.

        // Brute force approach employing the usage of above observation
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> freqMap = new HashMap<>();
        int minFreq = N / 3 + 1;

        for (int i = 0; i < N; i++) {
            int val = freqMap.getOrDefault(nums[i], 0);
            freqMap.put(nums[i], ++val);
            if (val >= minFreq) {
                if (res.isEmpty() || !res.contains(nums[i]))
                    res.add(nums[i]);
            }
            if (res.size() == 2)
                break;
        }
        return res;
    }
}
