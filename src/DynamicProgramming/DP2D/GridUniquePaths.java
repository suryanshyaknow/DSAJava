package DynamicProgramming.DP2D;

public class GridUniquePaths {

    public int uniquePaths(int m, int n) {
        // Step i. Express the prob in terms of indices
        // Step ii. Do what problem has stated on those indices
        // Step iii. Count or sum whatever the problem has stated

        return countPaths(m-1, n-1);
        // If we've started off from (0,0), directions: down and right
        // now that we've started from (m-1, n-1), directions: up and left

        // Time complexity: O(2^m*n)
        // Space complexity: O(path length)
    }

    private static int countPaths(int i, int j) {
        // Base case
        // We reached our destination
        if (i == 0 && j == 0)
            return 1;
        // We're outta bounds
        if (i < 0 || j < 0) return 0;

        int left = countPaths(i-1, j);
        int up = countPaths(i, j-1);
        return left + up;
    }
}
