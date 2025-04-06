package TwoPointers;

import java.util.*;

public class TripletSumToZero {

    public static List<List<Integer>> searchTripletsDesignGurus(int[] arr) {
        List<List<Integer>> triplets = new ArrayList<>();
        // TODO: Write your code here
        // First off, sort the array
        Arrays.sort(arr);

        // Iterate with the first pointer being the anchor
        // Now the problem boils down to finding a pair whose sum negate the anchor
        for (int i = 0; i < arr.length - 2; i++) {
            if (i > 0 && arr[i] == arr[i - 1]) continue;

            int first = i + 1;
            int last = arr.length - 1;

            while (first < last) { // NOT '<=' because then arr[first] would be same as arr[last] and the pair won't be unique
                int sum = arr[first] + arr[last];
                if (sum == -arr[i]) { // Triplet found
                    triplets.add(Arrays.asList(arr[i], arr[first], arr[last]));
                    first++;
                    last--;
                } else if (sum < -arr[i]) { // Means sum's gotta up his game
                    first++;
                } else {
                    last--;
                }
            }
        }
        return triplets;
        /*
        - To find triplets, you often use two loops, meaning you’re checking N × N = N² possible pairs.
        - In the worst case, every pair could form a valid triplet, so you end up storing N² triplets.
        - Since storing each triplet takes space, the total space used becomes O(N²).
         */
    }

    public List<List<Integer>> searchTripletsOptimal(int[] nums) {
        // The idea is to make an ele a pivot,
        // and use two pointers approach to find the rem elements
        // of the triplet.

        // First off, to use to approach, given arr gotta be sorted
        Arrays.sort(nums);
        int N = nums.length;
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // Skip duplicate i

            int leftPtr = i + 1;
            int rightPtr = N - 1;
            int target = -nums[i];

            while (leftPtr < rightPtr) {
                int sum = nums[leftPtr] + nums[rightPtr];
                if (sum == target) {
                    res.add(Arrays.asList(nums[i], nums[leftPtr], nums[rightPtr]));
                    leftPtr++;
                    rightPtr--;
                    while (leftPtr < rightPtr && nums[leftPtr] == nums[leftPtr - 1]) leftPtr++;
                    while (leftPtr < rightPtr && nums[rightPtr] == nums[rightPtr + 1]) rightPtr--;
                } else if (sum < target) {
                    leftPtr++;
                    while (leftPtr < rightPtr && nums[leftPtr] == nums[leftPtr - 1]) leftPtr++;
                } else {
                    rightPtr--;
                    while (leftPtr < rightPtr && nums[rightPtr] == nums[rightPtr + 1]) rightPtr--;
                }
            }
        }
        // Time: O(N Log N) + O(N^2)
        // Space: O(N^2) (i.e. number of unique elements to return the ans)
        return res;
    }

    public List<List<Integer>> searchTripletsBetter(int[] nums) {
        int N = nums.length;
        HashSet<List<Integer>> hashSet = new HashSet<>();
        for (int i = 0; i < N - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // Skipping dupes for i

            // From here on, the problem boils down to TwoSum ;)
            int target = -nums[i];
            HashMap<Integer, Integer> idxMap = new HashMap<>();
            for (int j = i + 1; j < N; j++) {
//                if (j > i + 1 && nums[j] == nums[j - 1]) continue; // Skipping dupes for j
//                Can't do it because if we skip the first val of dupe, then there might not be enough ele present to form a triplet
//                Example: [0, 0, 0]

                int remDuplet = target - nums[j];
                if (idxMap.containsKey(remDuplet)) {
                    List<Integer> triplet = new ArrayList<Integer>(Arrays.asList(nums[i], nums[j], remDuplet));
                    // Sort it to avoid the dupes
                    Collections.sort(triplet);
                    hashSet.add(triplet);
                }
                idxMap.put(nums[j], j);
            }
        }

        // Time Complexity: O(N^2). Collections.sort(triplet) inside is O(3 log 3) → constant time, so ignorable.
        // Or O(N^2 * log M) in case ordered set has been used. M being the size of unordered set (map) to find the 'remDuplet'.
        // Space: O(N) (being used to store the elements while searching for 'remDuplet') + O(num of unique triplets) * 2 (Set as well as list)

        return new ArrayList<>(hashSet);
    }

    public static void main(String[] args) {
        int[] arr = {-3, 0, 1, 2, -1, 1, -2};
        System.out.println(searchTripletsDesignGurus(arr));

        int[] arr1 = {-1, 0, 1, 2, -1, -4};
        System.out.println(searchTripletsDesignGurus(arr1));
    }
}
