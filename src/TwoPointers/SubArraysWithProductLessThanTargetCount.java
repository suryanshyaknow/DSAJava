package TwoPointers;

public class SubArraysWithProductLessThanTargetCount {

    public static int findSubarrays(int[] arr, int target) {
        int totalCount = 0;

        // ToDo: Write Your Code Here.
        /*
         # Sliding Window Algorithm:

        - Maintain a window from left to right.
        - Expand it till the ongoing product is less than the target.
        - Shrink it from the left while product is being greater than or equal to the target,
          and divide the prod by arr[left] to maintain the prod less than the target.
         */
        int left = 0, right = 0;
        int ongoingProd = 1;

        // Handle the edge case early on
        if (target <= 1)
            return 0;

        while (right <= arr.length - 1) {
            ongoingProd *= arr[right];

            // See if it's greater than the target and reduce the window fuckin' accordingly
            while (left <= right && ongoingProd >= target) {
                ongoingProd /= arr[left];
                left++;
            }
            // Now that the prod is valid, count and add the no. of subarrays till now
            totalCount += right - left + 1;

            // Expand the fuckin' window
            right += 1;
        }
        // Return the result.
        return totalCount;

        /*
        Sliding Window Mechanism: The inner while loop advances the left pointer only when the product of the current
        window (product) is greater than or equal to the target.
        Importantly, each element is visited at most twice—once by the right pointer and once by the left pointer.
        This ensures that the total number of operations remains proportional to n.
         */
    }

    public static void main(String[] args) {

        int arr[] = {2, 5, 3, 10};
        System.out.println(findSubarrays(arr, 30));

    }

}
