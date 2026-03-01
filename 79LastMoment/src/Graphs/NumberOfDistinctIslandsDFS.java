package Graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NumberOfDistinctIslandsDFS {

    int countDistinctIslands(int[][] grid) {
        // Your Code here

        // Since we gotta account for the shape of the islands,
        // we oughta store each different shape encountered.
        int N = grid.length;
        int M = grid[0].length;

        Set<String> set = new HashSet<>();
        int vis[][] = new int[N][M];

        int[][] dirs = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
        for (int i=0; i < N; i++) {
            for (int j=0; j < M; j++) {
                if (vis[i][j] != 1 && grid[i][j] == 1) { // land && not visited
                    List<String> shape = new ArrayList<>();
                    dfs(i, j, vis, grid, shape, i, j, dirs); // secondary i, j -> base co-ordinates
                    set.add(String.join(";", shape));
                }
            }
        }
        return set.size();
    }

    private void dfs(int i, int j, int[][] vis, int[][] grid, List<String> shape,
                     int basei, int basej, int[][] dirs) {
        int N = grid.length;
        int M = grid[0].length;
        vis[i][j] = 1;
        shape.add((i - basei) + "," + (j - basej));

        // Move to unvisited neighboring lands
        for (int[] dir: dirs) {
            int nr = i + dir[0];
            int nc = j + dir[1];

            if (nr >= 0 && nr < N
                    && nc >= 0 && nc < M
                    && vis[nr][nc] != 1
                    && grid[nr][nc] == 1) {
                dfs(nr, nc, vis, grid, shape, basei, basej, dirs);
            }
        }
    }
}
