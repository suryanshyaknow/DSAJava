package Array.Hard;

import java.util.*;

public class FourSum {

    public List<List<Integer>> fourSumBetter(int[] nums, int target) {
        int N = nums.length;
        HashSet<List<Integer>> quadruplets = new HashSet<>();

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (i > 0 && nums[i] == nums[i - 1]) continue;
                // Now the problem boils down to finding duplets
                // whose sum is equal to target - nums[i] - nums[j]
                HashMap<Long, Integer> hashMap = new HashMap<>();

                for (int k = j + 1; k < N; k++) {
                    int candidate = nums[k];
                    long complement = (long) target - nums[i] - nums[j] - nums[k];
                    if (hashMap.containsKey(complement)) {
                        // Quadruplet found
                        List<Integer> quadruplet = new ArrayList<>(Arrays.asList(nums[i], nums[j], candidate, (int) complement));
                        Collections.sort(quadruplet);
                        quadruplets.add(quadruplet);
                    }
                    hashMap.put((long) candidate, k);
                }
            }
        }
        return new ArrayList<>(quadruplets);
    }

    public List<List<Integer>> fourSumOptimal(int[] nums, int target) {
        int N = nums.length;
        List<List<Integer>> quadruplets = new ArrayList<>();

        // Fix two anchors and use two pointers
        // And use sliding window tech to find other two.
        // First off, sort the given array.
        // Also, don't forget to skip the dupes.
        Arrays.sort(nums);

        for (int i = 0; i < N - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            for (int j = i + 1; j < N - 2; j++) {
                if (j > i+1 && nums[j] == nums[j - 1])
                    continue;

                long sum = nums[i] + nums[j];
                long newTarget = target - sum;
                int leftPtr = j + 1;
                int rightPtr = N - 1;
                while (leftPtr < rightPtr) {
                    int currentSum = nums[leftPtr] + nums[rightPtr];
                    if (nums[leftPtr] + nums[rightPtr] == newTarget) {
                        List<Integer> quad = new ArrayList<>(
                                Arrays.asList(nums[i], nums[j], nums[leftPtr], nums[rightPtr]));
                        quadruplets.add(quad);
                        leftPtr++;
                        rightPtr--;
                        while (leftPtr < rightPtr && nums[leftPtr] == nums[leftPtr - 1])
                            leftPtr++;
                        while (rightPtr > leftPtr && nums[rightPtr] == nums[rightPtr + 1])
                            rightPtr--;
                    } else if (currentSum < newTarget) {
                        leftPtr++;
                        while (leftPtr < rightPtr && nums[leftPtr] == nums[leftPtr - 1])
                            leftPtr++;
                    } else {
                        rightPtr--;
                        while (leftPtr > rightPtr && nums[rightPtr] == nums[rightPtr + 1])
                            rightPtr--;
                    }
                }
            }
        }
        return quadruplets;
    }

}
