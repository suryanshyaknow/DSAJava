package DynamicProgramming.LIS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PrintLIS {

    public ArrayList<Integer> getLIS(int nums[]) {
        // Code here
        int N = nums.length;
        ArrayList<Integer> res = new ArrayList<>();
        // Initialize a dp and a hash array to backtrack the LIS elements
        int dp[] = new int[N];
        int hash[] = new int[N];
        // Define the hash w its indices as values themselves
        for (int i = 0; i < N; i++) {
            dp[i] = 1;
            hash[i] = i;
        }

        // SImply implement the O(N^2) approach then backtrack
        int maxLen = 1;
        int lastIdxUpdated = 0;
        for (int i = 1; i < N; i++) {
            for (int j = 0; j <= i - 1; j++) {
                if (nums[j] < nums[i] && dp[i] < 1 + dp[j]) {
                    dp[i] = 1 + dp[j];
                    hash[i] = j;
                }
            }
            if (maxLen < dp[i]) {
                maxLen = dp[i];
                lastIdxUpdated = i;
            }
        }

        // Now, construc the LIS
        while (hash[lastIdxUpdated] != lastIdxUpdated) {
            res.add(nums[lastIdxUpdated]);
            lastIdxUpdated = hash[lastIdxUpdated];
        }
        res.add(nums[lastIdxUpdated]);
        Collections.reverse(res);

        return res;
    }
}
