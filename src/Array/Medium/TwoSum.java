package Array.Medium;

import java.util.HashMap;

public class TwoSum {

    // Given an array of integers arr and an integer target, return indices of the two numbers such that they add up to target.

    public int[] twoSumBetterOptimal(int[] arr, int target) {
        int N = arr.length;
        int leftPtr = 0;
        int rightPtr = N - 1;
        while (leftPtr < rightPtr) {
            int sum = arr[leftPtr] + arr[rightPtr];
            if (sum == target) {
                return new int[]{leftPtr, rightPtr};
            } else if (sum < target) {
                leftPtr++;
            } else {
                rightPtr--;
            }
        }
        return new int[]{-1, -1};
    }

    public int[] twoSumBetter(int[] arr, int target) {
        int N = arr.length;

        HashMap<Integer, Integer> idxMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int j = target - arr[i];
            if (idxMap.containsKey(j))
                return new int[]{i, idxMap.get(j)};
            idxMap.put(arr[i], i);
        }

        return new int[]{-1, -1};
    }

    public int[] twoSumBruteForce(int[] arr, int target) {
        int N = arr.length;
        int[] res = new int[2];
        for (int i = 0; i < N; i++) {
//            for (int j = i + 1; j < N; j++) {
//                if (arr[i] + arr[j] == target) {
//                    res[0] = i;
//                    res[1] = j;
//                    break;
//                }
            // Let's find the apt 'j' via binary search only if it's given that arr is sorted
            int low = i + 1;
            int high = N - 1;
            while (low <= high) {
//                int mid = (low + high) / 2;
                int mid = low + (high - low) / 2;
                int sum = arr[i] + arr[mid];
                if (sum == target)
                    return new int[]{i, mid};
                else if (sum < target) { // that means mid ain't sufficing
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return res;
    }


}
