package DynamicProgramming.LIS;

import java.util.Arrays;

public class LBS {

    public static int LongestBitonicSequence(int n, int[] nums) {
        // code here
        // If we could figure out the LIS from front and back
        // and simple compute the len and deteming the longest
        // length, we're golden

        int N = nums.length;
        int dp[] = new int[N]; // dp[i] denotes the LIS till idx i
        int dp2[] = new int[N]; // dp arr for LDS
        Arrays.fill(dp, 1);
        Arrays.fill(dp2, 1);

        // Compute LIS, dp[]
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= i - 1; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
        }

        // Compute LDS, dp2[]
        for (int i = N - 1; i >= 0; i--) {
            for (int j = N - 1; j >= i + 1; j--) {
                if (nums[j] < nums[i])
                    dp2[i] = Math.max(dp2[i], 1 + dp2[j]);
            }
        }

        int maxLen = 0;
        for (int i = 0; i < N; i++) {
            if (dp[i] > 1 && dp2[i] > 1) // Ensures both increasing and decreasing parts exist
                maxLen = Math.max(maxLen, dp[i] + dp2[i] - 1);
        }
        return maxLen;
    }

}
