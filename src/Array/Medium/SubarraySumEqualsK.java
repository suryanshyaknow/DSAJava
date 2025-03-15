package Array.Medium;

public class SubarraySumEqualsK {

    public static int subarraySum(int[] arr, int k) { // Shall only work if it's ensured that array contains only positive numbers
        int count = 0;
        int N = arr.length;

        int leftPtr = 0; // this ptr is for shrinking the window size
        int rightPtr = 0; // for expanding the window
        int currentSum = 0;
        while (rightPtr < N) {
            currentSum += arr[rightPtr];
            // Now, what if the currentSum is greater than the k, we gotta shrink the window from the left
            while (leftPtr <= rightPtr && currentSum > k) {
                currentSum -= arr[leftPtr++];
            }

            if (currentSum == k) { // We gotta handle zeroes explicitly
                count++;
                int temp = leftPtr;
                while (temp < rightPtr && arr[temp] == 0) {
                    count++;
                    temp++;
                }
            }
            rightPtr++;
        }

        // Special Case: If k = 0, check if array even contains 0
        if (k == 0) {
            boolean hasZero = false;
            for (int num : arr) {
                if (num == 0) {
                    hasZero = true;
                    break;
                }
            }
            return hasZero ? count : 0;  // If no zero exists, return 0.
        }

        return count;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {1};
        subarraySum(arr, 0);
    }

}
