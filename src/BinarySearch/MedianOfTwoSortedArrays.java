package BinarySearch;

public class MedianOfTwoSortedArrays {

    public double findMedianSortedArraysBSOptimal(int[] nums1, int[] nums2) {
        int N1 = nums1.length;
        int N2 = nums2.length;
        if (N1 > N2) return findMedianSortedArrays(nums2, nums1); // We wanna perform BS on shorter array

        int low = 0;
        int high = N1; // Max number of ele we could pick from the nums1 to formulate the left half
        int leftHalf = (N1 + N2 + 1) / 2;
        int N = N1 + N2;

        while (low <= high) {
            int mid1 = (low + high) / 2;
            int mid2 = leftHalf - mid1;
            int l1 = Integer.MIN_VALUE;
            int l2 = Integer.MIN_VALUE;
            int r1 = Integer.MAX_VALUE;
            int r2 = Integer.MAX_VALUE;
            if (mid1 < N1) r1 = nums1[mid1]; // mid1 indeed should be lesser than the length of the first array
            if (mid2 < N2) r2 = nums2[mid2]; // The same goes for mid2
            if (mid1 - 1 >= 0) l1 = nums1[mid1 - 1];
            if (mid2 - 1 >= 0) l2 = nums2[mid2 - 1];

            if (l1 <= r2 && l2 <= r1) {
                if (N % 2 == 0) return (double) (Integer.max(l1, l2) + Integer.min(r1, r2)) / 2.0;
                else return Integer.max(l1, l2);
            } else if (l1 > r2) high = mid1 - 1;
            else low = mid1 + 1;
        }
        return -1;
    }

    public double findMedianSortedArraysOptimal(int[] nums1, int[] nums2) {
        int N1 = nums1.length;
        int N2 = nums2.length;
        int N = N1 + N2;
        int idx1 = N / 2 - 1;
        int idx1Ele = -1;
        int idx2 = N / 2;
        int idx2Ele = -1;

        int ptr = 0;
        int i = 0;
        int j = 0;
        while (i < N1 && i < N2) {
            if (nums1[i] <= nums2[j]) {
                if (ptr == idx1) idx1Ele = nums1[i];
                if (ptr == idx2) idx2Ele = nums1[i];
                ptr++;
                i++;
            } else {
                if (ptr == idx1) idx1Ele = nums2[j];
                if (ptr == idx2) idx2Ele = nums2[j];
                ptr++;
                j++;
            }
        }
        while (i < N1) {
            if (ptr == idx1) idx1Ele = nums1[i];
            if (ptr == idx2) idx2Ele = nums1[i];
            i++;
            ptr++;
        }
        while (j < N2) {
            if (ptr == idx1) idx1Ele = nums2[j];
            if (ptr == idx2) idx2Ele = nums2[j];
            j++;
            ptr++;
        }

        if (N % 2 == 1) return idx2Ele;
        return ((double) idx1Ele + idx2Ele) / 2.0;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int N1 = nums1.length;
        int N2 = nums2.length;

        // Merge the given arrays in a sorted manner
        int[] nums3 = new int[N1 + N2];
        int i = 0;
        int j = 0;
        int ptr = 0;
        while (i < N1 && j < N2) {
            if (nums1[i] <= nums2[j]) {
                nums3[ptr++] = nums1[i++];
            } else {
                nums3[ptr++] = nums2[j++];
            }
        }
        while (i < N1) nums3[ptr++] = nums1[i++];
        while (j < N2) nums3[ptr++] = nums2[j++];

        int N = N1 + N2;
        if (N % 2 == 0)
            return (double) ((double) nums3[N / 2] + (double) nums3[N / 2 - 1]) / 2;
        else
            return nums3[N / 2];
    }

}
