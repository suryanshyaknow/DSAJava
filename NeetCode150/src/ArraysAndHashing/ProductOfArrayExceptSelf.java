package ArraysAndHashing;

public class ProductOfArrayExceptSelf {

    public int[] productExceptSelfSpaceOptimal(int[] nums) {
        int N = nums.length;

        int[] res = new int[N];

        // The optimal approach to reach to O(1) solution is to use
        // res array itself for both prefix and sufix computations.
        int prod = 1;
        for (int i=0; i < N; i++) {
            res[i] = prod;
            prod *= nums[i];
        }

        // Populate the res w pref * suf
        prod = 1;
        for (int i=N-1; i >= 0; i--) {
            res[i] = prod * res[i];
            prod *= nums[i];
        }

        return res;

        // Time: O(N)
        // Space: O(1)
    }

    public int[] productExceptSelf(int[] nums) {
        int N = nums.length;

        int[] pref = new int[N];
        int [] suf = new int[N];

        // Populate the pref w pref products
        int prod = 1;
        for (int i=0; i < N; i++) {
            pref[i] = prod;
            prod *= nums[i];
        }

        // Populate the suf w suffix products
        prod = 1;
        for (int i=N-1; i >= 0; i--) {
            suf[i] = prod;
            prod *= nums[i];
        }

        // Now poulate the res array
        int[] res = new int[N];
        for (int i=0; i < N; i++) {
            res[i] = pref[i] * suf[i];
        }

        return res;

        // Time: O(N)
        // Space: 2 * O(N)
    }
}
