package Array.Hard;

public class GridUniquePaths {

    public int uniquePathsRecursive(int m, int n) {
        return countPaths(0, 0, m, n);

        // Exponential time complexity
        // Exponential space complexity because of recursive stack space
    }

    private static int countPaths(int i, int j, int M, int N) {
        if (i >= M || j >= N) return 0;
        else if (i == M - 1 && j == N - 1) return 1;
        else return countPaths(i + 1, j, M, N) + countPaths(i, j + 1, M, N);
    }

}
