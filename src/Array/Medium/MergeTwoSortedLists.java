package Array.Medium;

import java.util.Arrays;

public class MergeTwoSortedLists {

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) return;
        // The idea is to get all the small elements on the left
        // and the large one on the right, and then sort 'em individually
        int left = m - 1;
        int right = 0;
        while (left >= 0 && right < n) {
            if (nums1[left] > nums2[right]) {
                swapEle(nums1, left, nums2, right);
                left--;
                right++;
            } else {
                break;
            }
        }

        if (m > 0)
            Arrays.sort(nums1, 0, m);
        if (n > 0)
            Arrays.sort(nums2, 0, n);

        right = 0;
        for (int i = m; i < nums1.length; i++) {
            nums1[i] = nums2[right++];
        }
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 2, 4, 5, 6, 0};
        int[] arr2 = new int[]{3};
        merge(arr1, 5, arr2, 1);
        System.out.println(Arrays.toString(arr1));
    }

    private static void swapEle(int[] arr1, int idx1, int[] arr2, int idx2) {
        int temp = arr1[idx1];
        arr1[idx1] = arr2[idx2];
        arr2[idx2] = temp;
    }
}
