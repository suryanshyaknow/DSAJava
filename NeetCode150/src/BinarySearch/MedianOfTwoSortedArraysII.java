package BinarySearch;

public class MedianOfTwoSortedArraysII {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int N = nums1.length;
        int M = nums2.length;

        // Intuition: Keep two pointers for each array and iterate
        // and compare each ele of both the arrays and move the ptrs
        // accordingly.

        int i = 0;
        int j = 0;
        int L = N + M;
        int[] res = new int[L];
        int ptr = 0;

        while (i < N && j < M) {
            if (nums1[i] <= nums2[j]) {
                res[ptr++] = nums1[i];
                i++;
            } else {
                res[ptr++] = nums2[j];
                j++;
            }
        }

        // Now if some eles of nums1 left
        // Attach the remaining ele w/o any comparison
        while (i < N) {
            res[ptr++] = nums1[i++];
        }

        // Similarly if some eles of nums2 left
        while (j < M) {
            res[ptr++] = nums2[j++];
        }

        // Now that we've combined sorted array
        // determine the median
        if (L % 2 == 0) {
            return (double) (res[(L / 2) - 1] + res[L / 2]) / 2;
        }
        return res[L / 2];

        // Time: O(N + M)
        // Space: O(N + M)
    }

}
