package DynamicProgramming.LIS;

import java.util.Arrays;

public class LongestIncreasingSubsequenceDP {

    public int lengthOfLISOptimalII(int[] nums) {
        int N = nums.length;

        int[] dp = new int[N]; // ith ele in dp represents the LIS for ith ele of nums
        Arrays.fill(dp, 1);
        int maxLen = 1;
        for (int i = 1; i < N; i++) {
            for (int j = 0; j <= i - 1; j++) {
                if (nums[j] < nums[i]) {
                    int lis = 1 + dp[j];
                    // But we want the max
                    dp[i] = Math.max(lis, dp[i]);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

//        int maxLen = 1;
//        for (int i = 0; i < N; i++) {
//            maxLen = Math.max(maxLen, dp[i]);
//        }
        return maxLen;
    }

    public int lengthOfLISOptimal(int[] nums) {
        int N = nums.length;

        int ahead[] = new int[N + 1];
        int curr[] = new int[N + 1];
        for (int i = N - 1; i >= 0; i--) { // Base case handled implicitly since we started off from N-1
            for (int prev = i - 1; prev >= -1; prev--) {
                // Remember to offset prev by +1 while storing values in dp
                int notTake = ahead[prev + 1]; // prev remains the same
                int take = 0;
                if (prev == -1 || nums[prev] < nums[i])
                    take = 1 + ahead[i + 1]; // i becomes prev cuz we took the ele. But we'll use i+1 because of offset

                curr[prev + 1] = Math.max(take, notTake);
            }
            ahead = curr.clone();
        }

        return ahead[0];
    }

    public int lengthOfLISTabulate(int[] nums) {
        int N = nums.length;
        int dp[][] = new int[N + 1][N + 1]; // prev ranges from -1 to N-1 -> 0 to N (+1 offset)

//        for (int i = N; i >= 0; i--) {
//            for (int j = N; j >= 0; j--) {
//                int prev = j - 1;
//                if (i == N) {
//                    dp[i][j] = 0;
//                    continue;
//                }
//
//                int notTake = dp[i + 1][j]; // prev remains the same
//                int take = 0;
//                if (prev == -1 || nums[prev] < nums[i])
//                    take = 1 + dp[i + 1][i + 1]; // Remember, dp is offset by +1 for prev.
//
//                dp[i][j] = Math.max(take, notTake);
//            }
//        }

        for (int i = N - 1; i >= 0; i--) { // Base case handled implicitly since we started off from N-1
            for (int prev = i - 1; prev >= -1; prev--) {
                // Remember to offset prev by +1 while storing values in dp
                int notTake = dp[i + 1][prev + 1]; // prev remains the same
                int take = 0;
                if (prev == -1 || nums[prev] < nums[i])
                    take = 1 + dp[i + 1][i + 1]; // i becomes prev cuz we took the ele. But we'll use i+1 because of offset

                dp[i][prev + 1] = Math.max(take, notTake);
            }
        }

        return dp[0][0];
    }

    public int lengthOfLISMemoize(int[] nums) {
        int N = nums.length;
        int dp[][] = new int[N][N + 1]; // prev ranges from -1 to N-1 -> 0 to N
        // So we gotta 1 up the prev
        for (int i = 0; i < N; i++) Arrays.fill(dp[i], -1);

        return lengthOfLISHelper(0, nums, -1, dp);
    }

    private int lengthOfLISHelper(int i, int[] nums, int prev, int[][] dp) {
        if (i >= nums.length)
            return 0;

        if (dp[i][prev + 1] != -1) return dp[i][prev + 1]; // Offset by -1

        // Do NOT take the ele
        int notTake = lengthOfLISHelper(i + 1, nums, prev, dp); // prev remains the same
        // Take the ele
        int take = 0;
        // We could only take an ele if it's prev idx is -1
        // Or prev ele is less than the curr
        if (prev == 0 || nums[prev - 1] < nums[i])
            take = lengthOfLISHelper(i + 1, nums, i, dp);
        return dp[i][prev + 1] = Math.max(take, notTake);
    }

}
