package BinarySearch;

public class MedianOfTwoSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        // Intuition:
        // Think of the two arrays as one combined sorted array.
        // We want to partition the combined elements into a left half
        // and a right half, with roughly (N + M) / 2 elements on the left.
        //
        // We can simulate this without actually merging:
        // pick x elements from nums1, and pick the remaining
        // half - x elements from nums2.
        //
        // Since both arrays are already sorted, for a given partition
        // we only need to check the boundary elements.
        //
        // We use binary search on x to find the configuration where
        // every element on the left <= every element on the right.
        // Once that partition is found, the median is determined by
        // those boundary elements.

        // Now about the pointer movement: we've gotta compare boundary
        // elements -- to reach to the perfect config -- of the nums1left
        // w nums2 right and nums2left w nums1 right.
        // Implying:
        // nums1left <= nums2right && nums2left <= nums1right
        // Now for instance, if the first condition fails
        // then it means we have pciked too many elements from
        // nums2 gotta shift the mid backward, meaninng r = mid - 1.
        // And if second case fails, that implies, nusm2 got more elements
        // than it shoulda so we should pcik mroe elements from the nums1
        // and hence shift mid forward i.e. l = mid + 1

        int N = nums1.length;
        int M = nums2.length;

        if (M < N) {
            // to perform the BS on smaller array for optimization
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;

            N = nums1.length;
            M = nums2.length;
        }

        int l = -1;
        int r = N - 1;
        int half = (N + M) / 2;

        while (l <= r) {
            // Mid in here represents the last ele I picked from nums1
            // -1 being no elements picked from nums1
            int mid = (l + r) / 2;
            int j = half - mid - 2; // half - (mid + 1) - 1

            // Check if the every ele in the left partition
            // is smaller than every ele on the right
            int nums1left = (mid >= 0) ? nums1[mid] : Integer.MIN_VALUE;
            int nums2left = (j >= 0) ? nums2[j] : Integer.MIN_VALUE;

            // Now if we want all the elements from nums1
            int nums1right = (mid + 1 < N) ? nums1[mid + 1] : Integer.MAX_VALUE;
            // If we want all the elements from nums2
            int nums2right = (j + 1 < M) ? nums2[j + 1] : Integer.MAX_VALUE;

            if (nums1left <= nums2right && nums2left <= nums1right) {
                if ((N + M)  % 2 == 0)
                    return ((double) Math.max(nums1left, nums2left) + Math.min(nums1right, nums2right)) / 2;
                else
                    return Math.min(nums1right, nums2right);
            }

            if (nums1left > nums2right) {
                // meaning too much ele got included in left partition
                r = mid - 1;
            } else if (nums2left > nums1right) {
                // Too much ele got included in nums2
                // meaning fewer less eles in nums1
                l = mid + 1;
            }
        }

         return -1;
        // Time: O(log mis(M, N))
    }
}
