package DynamicProgramming.DPOnLIS;

import java.util.*;

public class LongestIncreasingSubsequence {

    public int lengthOfLISTabulation(int[] nums) {
        int N = nums.length;

        // So the idea is to pick and not pick the elements
        // like we been doing all along. However, while picking
        // elements we're to make sure the subsequence is increasing.
        // And to do that, we gotta track the priorly picked element.

        int[][] dp = new int[N + 1][N + 1]; // prevIdx can range from -1 to N-1, i.e. N + 1
        // Since -1 is in the picture, gotta use offset

        for (int i=N; i >= 0; i--) {
            for (int j=N - 1; j >= -1; j--) { // but while storing we're gonna save in offset slots
                // Base case
                if (i == N) {
                    dp[i][j + 1] = 0;
                    continue;
                }

                // Not pick
                int notPick = dp[i + 1][j + 1];

                // pick
                int pick = 0;
                if (j == -1 || nums[i] > nums[j]) {
                    pick = 1 + dp[i + 1][i + 1];
                }

                dp[i][j + 1] =  Math.max(pick, notPick);
            }

        }

        return dp[0][0];

        // Time: O(N * N)
        // Space: O(N * N)
    }

    private int helper(int idx, int[] nums, int prevIdx, int[][] dp) {
        // Base
        int N = nums.length;
        if (idx == N) return 0;

        if (dp[idx][prevIdx + 1] != -1) return dp[idx][prevIdx + 1];

        // Explore the possibilities
        // Not pick
        int notPick = 0 + helper(idx + 1, nums, prevIdx, dp);

        // Pick
        // Note that you could only pick the ele if it's the
        // first ele of the sequence or it's greater than its prev.
        int pick = 0;
        if (prevIdx == -1 || nums[idx] > nums[prevIdx])
            pick = 1 + helper(idx + 1, nums, idx, dp);

        // Maximize the lenght of the subsequence
        return dp[idx][prevIdx + 1] = Math.max(pick, notPick);
    }

    public int lengthOfLISMemo(int[] nums) {
        int N = nums.length;

        // So the idea is to pick and not pick the elements
        // like we been doing all along. However, while picking
        // elements we're to make sure the subsequence is increasing.
        // And to do that, we gotta track the priorly picked element.

        int[][] dp = new int[N][N + 1]; // prevIdx can range from -1 to N-1, i.e. N + 1
        // Since -1 is in the picture, gotta use offset
        for (int i = 0; i < N; i++) Arrays.fill(dp[i], -1);

        return helperMemo(0, nums, -1, dp);

        // Time: O(N * N)
        // Space: O(N * N) + O(N)
    }

    private int helperMemo(int idx, int[] nums, int prevIdx, int[][] dp) {
        // Base
        int N = nums.length;
        if (idx == N) return 0;

        if (dp[idx][prevIdx + 1] != -1) return dp[idx][prevIdx + 1];

        // Explore the possibilities
        // Not pick
        int notPick = 0 + helperMemo(idx + 1, nums, prevIdx, dp);

        // Pick
        // Note that you could only pick the ele if it's the
        // first ele of the sequence or it's greater than its prev.
        int pick = 0;
        if (prevIdx == -1 || nums[idx] > nums[prevIdx])
            pick = 1 + helperMemo(idx + 1, nums, idx, dp);

        // Maximize the lenght of the subsequence
        return dp[idx][prevIdx + 1] = Math.max(pick, notPick);
    }

    public int lengthOfLIS(int[] nums) {
        int N = nums.length;

        // So the idea is to pick and not pick the elements
        // like we been doing all along. However, while picking
        // elements we're to make sure the subsequence is increasing.
        // And to do that, we gotta track the priorly picked element,

        return helper(0, nums, -1);

        // Time: 2^N
        // Space: O(N)
    }

    private int helper(int idx, int[] nums, int prevIdx) {
        // Base
        int N = nums.length;
        if (idx == N) return 0;

        // Explore the possibilities
        // Not pick
        int notPick = 0 + helper(idx + 1, nums, prevIdx);

        // Pick
        // Note that you could only pick the ele if it's the
        // first ele of the sequence or it's greater than its prev.
        int pick = 0;
        if (prevIdx == -1 || nums[idx] > nums[prevIdx])
            pick = 1 + helper(idx + 1, nums, idx);

        // Maximize the lenght of the subsequence
        return Math.max(pick, notPick);
    }

}
