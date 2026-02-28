package Graphs;

public class NumberOfIslandsDFS {

    public int numIslands(char[][] grid) {
        int N = grid.length;
        int M = grid[0].length;

        // Keep another grid to maintain visited spots
        int[][] vis = new int[N][M];

        // WE're gonna start w a spot and connect it w surrounding
        // lands till it can't further be conncected. And call it
        // an island.

        int res = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // If it's not visited and a land
                // start off the dfs
                if (vis[i][j] != 1 && grid[i][j] == '1') {
                    dfs(i, j, grid, vis);
                    res += 1;
                }
            }
        }

        return res;
    }

    private void dfs(int i, int j, char[][] grid, int[][] vis) {
        // Mark the spot as visited
        int N = grid.length;
        int M = grid[0].length;
        vis[i][j] = 1;

        // Process the neighbors
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        for (int[] dir : dirs) {
            int r = i + dir[0];
            int c = j + dir[1];

            // Go deep if the nei is a land and inside bounds
            if (r >= 0 && r < N && c >= 0 && c < M && vis[r][c] != 1 && grid[i][j] == '1') {
                dfs(r, c, grid, vis);
            }
        }
    }
}
