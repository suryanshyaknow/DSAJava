package Arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MajorityElementII {

    public List<Integer> majorityElementOptimal(int[] nums) {
        int N = nums.length;
        // Moore's voting algo
        // It works by pairing and cancelling out different elements.
        // Since the majority ele appears more than half the time, it
        // survives all cancellations and ends up as the final candidate.

        // We're gonna implement this algo for finding two elements

        int ele1 = -1;
        int ele2 = -1;
        int cnt1 = 0;
        int cnt2 = 0;

        // Simple idea is pairwise cancellation for both the elements
        for (int i = 0; i < N; i++) {
            if (cnt1 == 0 && nums[i] != ele2) {
                // We reinitialize the ele
                ele1 = nums[i];
                cnt1 = 1;
            } else if (cnt2 == 0 && nums[i] != ele1) {
                ele2 = nums[i];
                cnt2 = 1;
            } else if (ele1 == nums[i])
                cnt1++;
            else if (ele2 == nums[i])
                cnt2++;
            else {
                cnt1--;
                cnt2--;
            }
        }

        // Confirm if they indeed are majority elements
        cnt1 = 0;
        cnt2 = 0;
        for (int i = 0; i < N; i++) {
            if (ele1 == nums[i])
                cnt1++;
            else if (ele2 == nums[i])
                cnt2++;
        }

        List<Integer> res = new ArrayList<>();
        if (cnt1 > N / 3)
            res.add(ele1);
        if (cnt2 > N / 3)
            res.add(ele2);

        return res;
    }

    public List<Integer> majorityElement(int[] nums) {
        int N = nums.length;

        // First off, there could be at max two elements
        // cuz say N = 9
        // For the ele to be majority it's gotta appear more
        // than 3 times, i.e., 4. And only two such eles (4 + 4 = 8)
        // could be accommodated.

        HashMap<Integer, Integer> map = new HashMap<>();
        int minFreq = N / 3 + 1;
        List<Integer> res = new ArrayList<>();

        // Simply traverse thru the given array and keep track of
        // elements' freq and early exit if you happen to find two
        // majority elements.
        for (int i = 0; i < N; i++) {
            int freq = map.getOrDefault(nums[i], 0);
            freq++;
            map.put(nums[i], freq);

            if (freq >= minFreq) {
                if (res.isEmpty() || !res.contains(nums[i]))
                    res.add(nums[i]);
            }
            if (res.size() == 2)
                break;
        }
        return res;

        // Time complexity: O(N)
        // Space complexity: O(N) for hashMap
    }
}
