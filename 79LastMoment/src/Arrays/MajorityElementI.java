package Arrays;

import java.util.HashMap;

public class MajorityElementI {

    public int majorityElement(int[] nums) {
        int N = nums.length;

        // Hashset to keep track of frequency of each ele
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < N; i++) {
            int freq = hashMap.getOrDefault(nums[i], 0);
            freq++;
            hashMap.put(nums[i], freq);

            if (freq > N / 2)
                return nums[i];
        }
        return -1;
    }
}
