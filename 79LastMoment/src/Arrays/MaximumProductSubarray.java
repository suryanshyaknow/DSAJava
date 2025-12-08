package Arrays;

public class MaximumProductSubarray {

    public int maxProduct(int[] nums) {
        int N = nums.length;
        int pref = 1, suf = 1;
        int maxProd = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            pref *= nums[i];
            suf *= nums[N - i - 1];

            maxProd = Math.max(maxProd, Math.max(pref, suf));

            // Reset after multiplication
            if (nums[i] == 0) pref = 1;
            if (nums[N - i - 1] == 0) suf = 1;
        }

        return maxProd;
    }

}
