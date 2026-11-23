package OneDDP;

import java.util.*;

public class WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {
        // Intuition: We have to ensure every contiguous segmented piece
        // is in dict. So at each candidate sbstring we're gonna ask if
        // it exists in dict, consume till the end of that candidate,
        // and advance accordingly.
        // Candidate substring -> Exist -> Consume -> Remaning string
        //
        // f(i) -> Representation of my recurrence function.
        // States whether the substring at idx i till the end
        // can be segmented using words in dict.

        // First off, convert the dict into hash such that lookups
        // become cheap (contant, O(1));
        Set<String> dict = new HashSet<>(wordDict);

        return helper(0, s, dict);
    }
    
    private boolean helper(int i, String s, Set<String> dict) {
        // Base case
        // If the whole string is consumed
        if (i == s.length()) return true;

        // Iterate over substring at i till end
        // See if it's there in dict and accordingly advance
        int N = s.length();
        for (int j = i; j < N; j++) {
            String cand = s.substring(i, j+1);
            if (dict.contains(cand) && helper(j+1, s, dict)) return true;
        }

        return false;

        // Time: O(2^N.N)
        // Space: O(N)
    }

    public boolean wordBreakMemo(String s, List<String> wordDict) {
        // Intuition: We have to ensure every contiguous segmented piece
        // is in dict. So at each candidate sbstring we're gonna ask if
        // it exists in dict, consume till the end of that candidate,
        // and advance accordingly.
        // Candidate substring -> Exist -> Consume -> Remaning string
        //
        // f(i) -> Representation of my recurrence function.
        // States whether the substring at idx i till the end
        // can be segmented using words in dict.

        // First off, convert the dict into hash such that lookups
        // become cheap (contant, O(1));
        Set<String> dict = new HashSet<>(wordDict);

        // Since, i can reach to same idx thru multiple paths, so I might
        // as well cache the subproblems solution
        Boolean[] dp = new Boolean[s.length()];
        Arrays.fill(dp, null);

        return helperMemo(0, s, dict, dp);

        // Time: O(N.N.N)
        // ~ States, Candidates per state, Processing each candidate (since their hashes are not available)
        // Time: O(N) + O(N) recursion stack
    }

    private boolean helperMemo(int i, String s, Set<String> dict, Boolean[] dp) {
        // Base case
        // If the whole string is consumed
        if (i == s.length()) return true;

        if(dp[i] != null) return dp[i];

        // Iterate over substring at i till end
        // See if it's there in dict and accordingly advance
        int N = s.length();
        for (int j = i; j < N; j++) {
            String cand = s.substring(i, j+1);
            if (dict.contains(cand) && helperMemo(j+1, s, dict, dp)) return dp[i] = true;
        }

        return dp[i] = false;
    }

    public boolean wordBreakTabulation(String s, List<String> wordDict) {
        // Intuition: We have to ensure every contiguous segmented piece
        // is in dict. So at each candidate sbstring we're gonna ask if
        // it exists in dict, consume till the end of that candidate,
        // and advance accordingly.
        // Candidate substring -> Exist -> Consume -> Remaning string
        //
        // f(i) -> Representation of my recurrence function.
        // States whether the substring at idx i till the end
        // can be segmented using words in dict.

        // First off, convert the dict into hash such that lookups
        // become cheap (constant, O(1));
        Set<String> dict = new HashSet<>(wordDict);

        // Since, i can reach to same idx thru multiple paths, so I might
        // as well cache the subproblems solution
        int N = s.length();
        Boolean[] dp = new Boolean[N + 1];
        Arrays.fill(dp, null);

        // Tabulation: bottom-up
        // dp[i] whether the substring ending starting at idx i till end
        // can be segmented using words in dict.
        for (int i = N; i >= 0; i--) {
            if (i == N) {
                dp[i] = true;
                continue;
            }

            // Generate every substring and try to consume
            for (int j = i; j < N; j++) {
                String cand = s.substring(i, j + 1);
                if (dict.contains(cand) && dp[j + 1]) {
                    // Solve for the substring at j+1 till end
                    dp[i] = true;
                    break;
                }
            }
            if (dp[i] == null)
                dp[i] = false;
        }

        return dp[0];

        // Time: O(N^3)
        // States, candidates per state, & computing hashes of each candidate
        // Space: O(N)
    }
}