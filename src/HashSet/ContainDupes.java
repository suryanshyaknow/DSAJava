package HashSet;

import java.util.HashSet;
import java.util.Set;

public class ContainDupes {

    public boolean containsDuplicate(int[] nums) {
        // TODO: Write your code here
        Set<Integer> hashSet = new HashSet<>(nums.length);
        for (int ele : nums) {
            if (!hashSet.contains(ele))
                hashSet.add(ele);
            else
                return true;
        }
        return false;
    }

    public boolean containsDuplicateUsingBruteForce(int[] nums) {
        // TODO: Write your code here
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j])
                    return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ContainDupes containDupes = new ContainDupes();
        int[] nums1 = {1999, 1972, 1999, 1976, 2024};
        System.out.println("Nums contain dupes? " + containDupes.containsDuplicate(nums1));
        System.out.println("Nums contain dupes? " + containDupes.containsDuplicateUsingBruteForce(nums1));

        int[] nums2 = {1999, 1972, 1976, 2024, 2011};
        System.out.println("Nums contain dupes? " + containDupes.containsDuplicate(nums2));
        System.out.println("Nums contain dupes? " + containDupes.containsDuplicateUsingBruteForce(nums2));
    }

}
