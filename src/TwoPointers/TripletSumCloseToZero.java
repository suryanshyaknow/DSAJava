package TwoPointers;

import java.util.Arrays;

public class TripletSumCloseToZero {

    public static int searchTriplet(int[] arr, int targetSum) {
        // TODO: Write your code here
        Arrays.sort(arr);
        // Store and update the shortest distance in an in-place variable
        int shortestDist = Integer.MAX_VALUE;
        int closestTripletSum = Integer.MAX_VALUE; // To compare the triplet sum when two or more triplets are at the same distance

        for (int i = 0; i < arr.length - 2; i++) {
            // Skip the dupes
            if (i > 0 && arr[i] == arr[i - 1])
                continue;

            // Use the two pointers to form a triplet
            int first = i + 1;
            int last = arr.length - 1;
            while (first < last) {
                int currentTripletSum = arr[i] + arr[first] + arr[last];
                int currentDist = Math.abs(targetSum - currentTripletSum);
                // Update the shortest dist encountered till now
                if (currentDist < shortestDist) {
                    shortestDist = currentDist;
                    closestTripletSum = currentTripletSum;
                } else if (currentDist == shortestDist && currentTripletSum < closestTripletSum) { // And if it's same, then choose the smaller triplet sum
                    closestTripletSum = currentTripletSum;
                }

                // Move pointers based on sum
                if (currentTripletSum > targetSum) { // Move closer to the target sum by reducing the arr[last]
                    last--;
                } else if (currentTripletSum < targetSum) { // Move closer to the target sum by increasing the arr[first]
                    first++;
                } else {
                    return currentTripletSum;
                }
            }
        }
        return closestTripletSum;
    }

    public static void main(String[] args) {
        int[] arr = {-1, 0, 2, 3};
        System.out.println(searchTriplet(arr, 3));

        int[] arr1 = {-1, 0, 1, 2, -1, -4};
//        System.out.println(searchTriplet(arr1));
    }

}
