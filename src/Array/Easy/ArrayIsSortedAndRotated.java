package Array.Easy;

public class ArrayIsSortedAndRotated {

    public boolean check(int[] arr) {

        // Just check for a single drop
        // If there are 0 drops, the array is already sorted (return true).
        int drop = 0;
        for (int i = 1; i <= arr.length - 1; i++) {
            // The moment drop exceeds one return false, no point in further evaluations
            if (arr[i] < arr[i - 1])
                drop++;
            if (drop > 1)
                return false;
        }

        // If there is exactly 1 drop, ensure the last element doesn’t break the order by checking
        // nums[n-1] > nums[0] (return true if valid).
        // This should only happen if there was exactly one drop.
        return drop == 0 || arr[arr.length - 1] <= arr[0];
    }

}
