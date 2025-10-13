package DynamicProgramming.DP2D;

import java.util.List;

public class TriangularMinPathSum {

    public int minimumTotal(List<List<Integer>> triangle) {
        // Step i. Express the problem in terms of indices.
        // Step ii. Do what question states in terms of indices. Base case and all.
        // Step iii. Pick the min path sum ;)

        return minPathSum(0, 0, triangle);
    }

    private static int minPathSum(int i, int j, List<List<Integer>> arr) {
        // Base case
        int N = arr.size();
        int curr = arr.get(i).get(j);
        if (i == N - 1)
            return curr;

        // Since we could only move down and diagonally, we'll be in bounds that's a given.
        // Triangular structure guarantees that!
        int down = curr + minPathSum(i + 1, j, arr);
        int dg = curr + minPathSum(i + 1, j + 1, arr);
        return Integer.min(down, dg);
    }

}
