package BinarySearch;

public class MedianOfTwoSortedArrays {

    public double findMedianSortedArraysOptimalBS(int[] nums1, int[] nums2) {

        // Step 1: Instead of merging the arrays, I'll think of splitting both the arrays into
        // a left half and a right half such that left half contains exactly half of the total
        // elements.
        // Now there must exist one correct config where the split is actually the merged sorted
        // array. We oughta use BS to find that correct split.

        // Step 2: How the split works: I choose a split point in the first array. Based on that,
        // the split point in the second array is fixed.
        // What makes a split correct? The split is correct if every ele on the left side is smaller
        // than or equal to every ele on the right.
        // If the split is wrong, I could tell which direction to move because the array is sorted.
        // Once the split is correct, the median is either the largest ele on the left side or the
        // average of the middle two eles.

        // One-liner: the binary search approach finds a correct split between the two sorted arrays
        // instead of merging them.

        return -1;
    }

    public double findMedianSortedArraysBetter(int[] nums1, int[] nums2) {
        // Simply merge the give two sorted arrays
        // and then compute the median

        // If we really think we don't really need to form the merged
        // array, we could simply makedo w the pointers and get our
        // median candidates.

        int N1 = nums1.length;
        int N2 = nums2.length;
        int N = N1 + N2;

        if (N == 0) return 0; // Edge case where both arrays are empty

        int left = 0;
        int right = 0;
        // int[] temp = new int[N1 + N2];
        int ptr = 0;

        // Median candidates ele and idx
        int idx1 = N/2 - 1; // N/2 - 1
        int idx2 = N/2; // N/2
        int idx1Ele = Integer.MIN_VALUE;
        int idx2Ele = Integer.MAX_VALUE;

        while (left < N1 && right < N2) {
            if (nums1[left] <= nums2[right]) {
                // temp[ptr++] = nums1[left++];
                if (ptr == idx1) idx1Ele = nums1[left];
                if (ptr == idx2) idx2Ele = nums1[left];
                left++;
                ptr++;
            } else {
                // temp[ptr++] = nums2[right++];
                if (ptr == idx1) idx1Ele = nums2[right];
                if (ptr == idx2) idx2Ele = nums2[right];
                right++;
                ptr++;
            }
        }

        // If left's got elements
        while (left < N1) {
            if (ptr == idx1) idx1Ele = nums1[left];
            if (ptr == idx2) idx2Ele = nums1[left];
            ptr++;
            left++;
        }

        // If right's got elements
        while (right < N2) {
            if (ptr == idx1) idx1Ele = nums2[right];
            if (ptr == idx2) idx2Ele = nums2[right];
            ptr++;
            right++;
        }

        // Compute the median
        if (N % 2 == 0) {
            return (double) (idx1Ele + idx2Ele) / 2;
        }
        return idx2Ele;

        // Time complexity: O(N) where N is N1 + N2
        // Space complexity: O(1) for temp
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Simply merge the give two sorted arrays
        // and then compute the median

        int N1 = nums1.length;
        int N2 = nums2.length;

        int left = 0;
        int right = 0;
        int[] temp = new int[N1 + N2];
        int ptr = 0;
        while (left < N1 && right < N2) {
            if (nums1[left] <= nums2[right])
                temp[ptr++] = nums1[left++];
            else
                temp[ptr++] = nums2[right++];
        }

        // If left's got elements
        while (left < N1) {
            temp[ptr++] = nums1[left++];
        }

        // If right's got elements
        while (right < N2) {
            temp[ptr++] = nums2[right++];
        }

        // Compute the median
        int N = N1 + N2;
        if ((double) N % 2 == 0) {
            return (double) (temp[N / 2] + temp[N / 2 - 1]) / 2;
        }
        return temp[N / 2];

        // Time complexity: O(N) where N is N1 + N2
        // Space complexity: O(N) for temp
    }
}
