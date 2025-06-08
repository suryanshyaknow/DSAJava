package Recursion;

import java.util.ArrayList;
import java.util.List;

public class KthPermutation {

    public String getPermutation(int n, int k) {
        List<Integer> nums = new ArrayList<>();
        int fact = 1;
        for (int i = 1; i < n; i++) {
            nums.add(i);
            fact *= i;
        }
        nums.add(n);

        StringBuilder res = new StringBuilder();
        k = k - 1;

        while (true) {
            int idx = k / fact; // fact being the (n - 1)! when we started off w one less factorial to compute block size
            res.append(nums.get(idx));
            // Remove the number chosen from the arr
            nums.remove(idx);

            if (nums.isEmpty()) {
                break;
            }

            k = k % fact;
            fact = fact / nums.size();
        }
        return res.toString();
    }

}
