package BinarySearch;

public class KthEleOfTwoSortedArrays {

    public int kthElementBS(int nums1[], int nums2[], int k) {
        // You cannot always assume you should binary search on nums1 from 0 to N1 when k itself may be less than N1.
        //Your range for mid1 should respect k. Otherwise mid2 = k - mid1 may become negative or exceed bounds.

        // Lower bound:
        // See, mid1 <= N1 && mid2 <= N2
        // Also, mid2 = k - mid1
        // k - mid1 <= N2
        // mid1 >= k - N2 i.e. low = min(0, k - N2)

        int N1 = nums1.length;
        int N2 = nums2.length;
        if (N1 > N2) return kthElementBS(nums2, nums1, k);

        int low = Integer.min(0, k - N2);
        int high = Integer.min(N1, k); // Makes sense. We don't wanna pick any negative idx ele from the right part.
        int left = k;

        while (low <= high) {
            int mid1 = (low + high) / 2;
            int mid2 = left - mid1;
            int l1 = Integer.MIN_VALUE;
            int l2 = Integer.MIN_VALUE;
            int r1 = Integer.MAX_VALUE;
            int r2 = Integer.MAX_VALUE;
            if (mid1 < N1) r1 = nums1[mid1]; // mid1 indeed should be lesser than the length of the first array
            if (mid2 < N2) r2 = nums2[mid2]; // The same goes for mid2
            if (mid1 - 1 >= 0) l1 = nums1[mid1 - 1];
            if (mid2 - 1 >= 0) l2 = nums2[mid2 - 1];

            if (l1 <= r2 && l2 <= r1) {
                return Integer.max(l1, l2);
            } else if (l1 > r2) high = mid1 - 1;
            else low = mid1 + 1;
        }
        return -1;
    }


    public int kthElement(int a[], int b[], int k) {
        // code here
        int N1 = a.length;
        int N2 = b.length;

        int i = 0;
        int j = 0;
        int ptr = 0;
        k = k - 1;

        while (i < N1 && j < N2) {
            if (a[i] <= b[j]) {
                if (ptr == k) return a[i];
                ptr++;
                i++;
            } else {
                if (ptr == k) return b[j];
                ptr++;
                j++;
            }
        }
        while (i < N1) {
            if (ptr == k) return a[i];
            ptr++;
            i++;
        }
        while (j < N2) {
            if (ptr == k) return b[j];
            ptr++;
            j++;
        }

        return -1;
    }

}
