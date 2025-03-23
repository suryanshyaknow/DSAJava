package Array.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RearrangeEleBySign {

    public static int[] rearrangeArrayUsingBruteForce(int[] nums) {
        int N = nums.length;
        int[] posArr = new int[N / 2];
        int posIdx = 0;
        int[] negArr = new int[N / 2];
        int negIdx = 0;
        // Populate negArr and posArr
        for (int i = 0; i < N; i++) {
            if (nums[i] < 0)
                negArr[negIdx++] = nums[i];
            else
                posArr[posIdx++] = nums[i];
        }

        // Rearrange the original array
        for (int i = 0; i < N / 2; i++) {
            nums[2 * i] = posArr[i];
            nums[2 * i + 1] = negArr[i];
        }
        return nums;
    }

    public static int[] rearrangeArrayUsingBruteForceWhereNegativesAndPositivesAreNotEqual(int[] nums) {
        int N = nums.length;
        List<Integer> posArr = new ArrayList<>();
        int posIdx = 0;
        List<Integer> negArr = new ArrayList<>();
        int negIdx = 0;
        // Populate negArr and posArr
        for (int i = 0; i < N; i++) {
            if (nums[i] < 0)
                negArr.add(negIdx++, nums[i]);
            else
                posArr.add(posIdx++, nums[i]);
        }

        // Rearrange the original array
        if (negArr.size() > posArr.size()) {
            for (int i = 0; i < posArr.size(); i++) {
                nums[2 * i] = posArr.get(i);
                nums[2 * i + 1] = negArr.get(i);
            }
            // Fill the remaining negative places
            int idx = posArr.size() * 2;
            for (int i = posArr.size(); i < negArr.size(); i++) { // Cuz we have filled 'posArr.length' number of elements
                nums[idx++] = negArr.get(i);
            }
        } else {
            for (int i = 0; i < negArr.size(); i++) {
                nums[2 * i] = posArr.get(i);
                nums[2 * i + 1] = negArr.get(i);
            }
            // Fill the remaining positive places
            int idx = negArr.size() * 2;
            for (int i = negArr.size(); i < posArr.size(); i++) { // Cuz we have filled 'posArr.length' number of elements
                nums[idx++] = posArr.get(i);
            }
        }
        return nums;
    }

    public static int[] rearrangeArrayBetter(int[] nums) {
        // First off, find the first positive sign
        int N = nums.length;
        int[] res = new int[N];
        int posPtr = 0;
        int negPtr = 1;

        for (int i = 0; i < N; i++) {
            if (nums[i] < 0) {
                res[negPtr] = nums[i];
                negPtr += 2;
            } else {
                res[posPtr] = nums[i];
                posPtr += 2;
            }
        }

        return res;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 1, -2, -5, 2, -4, -9, -11, -13};
        System.out.println(Arrays.toString(rearrangeArrayUsingBruteForceWhereNegativesAndPositivesAreNotEqual(arr)));
    }

}
