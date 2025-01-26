package DesignGurusEasy;

import java.util.Arrays;
import java.util.HashSet;

public class ContainsDuplicate {

    public static boolean containsDuplicate(int[] nums) {
        // TODO: Write your code here
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j])
                    return true;
            }
        }
        return false;
    }

    public static boolean containsDuplicateUsingHashSet(int[] nums) {
        // TODO: Write your code here
        HashSet<Integer> uniqueSet = new HashSet<>(); // Created to store unique elements

        // Iterating through the input array and checking their existence once at a time
        for (int num : nums) {
//            if (uniqueSet.contains(num)) {
//                return true;
//            } else {
//                uniqueSet.add(num);
//            }
            if (!uniqueSet.add(num)) // If the set already contains the current element, return true
                return true;
        }

        return false;
        // On average, adding or checking elements in a HashSet has a time complexity of  due to its underlying hash table structure.
        // HashSet storage: The algorithm uses a HashSet to store unique elements. In the worst case, when all elements are unique, the HashSet will contain N elements.
    }

    public static boolean containsDuplicateUsingSorting(int[] nums) {
        // TODO: Write your code here
        // Sort the array
        Arrays.sort(nums); // Has a time complexity of O(N log N)

        for (int i = 1; i < nums.length; i++) {
            // Compare each ele to its next
            if (nums[i - 1] == nums[i])
                return true;
        }
        return false;
        // Space Complexity: Depends on the impl. In the case of primitive types like int[], it uses a variant of the quicksort algorithm,
        // which has a space complexity of O(log N) due to the recursion stack for in-place sorting.
    }

    public static void main(String[] args) {
        int nums[] = {1, 2, 3, 4, 6, 7, 10, 4, 13};
        System.out.println("Does nums contain dupes: " + containsDuplicate(nums));
        System.out.println("Does nums contain dupes: " + containsDuplicateUsingHashSet(nums));
        System.out.println("Does nums contain dupes: " + containsDuplicateUsingSorting(nums));
    }

}
