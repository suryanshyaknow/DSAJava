package Graphs.Medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands {

    private static class Pair {
        int first;
        int second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    public int numIslands(char[][] grid) {
        int count = 0;
        int N = grid.length;
        int M = grid[0].length;
        int[][] vis = new int[N][M];

        // The plan is to iterate over each node and mark its neighbours
        // via bfs/dfs as visited. And each time we encounter a new starting
        // node that means it indicates an island.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (vis[i][j] != 1 && grid[i][j] == '1') { // node ain't visited and it's a land
                    bfs(new Pair(i, j), vis, grid);
                    count++;
                }
            }
        }
        return count;
    }

    private static void dfs(Pair node, int[][]vis, char[][] grid) {
        // First off, mark the node as visited
        vis[node.first][node.second] = 1;

        // Grab its neighbors and mark 'em as well
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] dir: dirs) {
            int nRow = node.first + dir[0];
            int nCol = node.second + dir[1];

            if (nRow >=0  && nCol >= 0
                    && nRow < grid.length && nCol < grid[0].length
                    && vis[nRow][nCol] == 0 && grid[nRow][nCol] == '1') {
                vis[nRow][nCol] = 1;
                dfs(new Pair(nRow, nCol), vis, grid);
            }
        }

    }

    private static void bfs(Pair node, int[][] vis, char[][] grid) {
        // Initial config
        Queue<Pair> q = new LinkedList<>();
        vis[node.first][node.second] = 1; // Mark visited
        q.offer(node);

        while (!q.isEmpty()) {
            Pair pair = q.poll();
            int row = pair.first;
            int col = pair.second;

            // Only four direction for neighbors
            int[][] dirs = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};

            // Now fetch its neighbours and mark 'em visited as well
            for (int[] dir : dirs) {
                // Mark visited only if
                // - Ain't visited already
                // - And is a valid neighbour
                // - And is a land as well
                int nRow = row + dir[0];
                int nCol = col + dir[1];
                if (nRow >= 0 && nCol >= 0
                        && nRow < grid.length
                        && nCol < grid[0].length
                        && vis[nRow][nCol] != 1
                        && grid[nRow][nCol] == '1'
                ) {
                    vis[nRow][nCol] = 1;
                    q.offer(new Pair(nRow, nCol));
                }

            }
        }
//        while (!q.isEmpty()) {
//            Pair pair = q.poll();
//            int row = pair.first;
//            int col = pair.second;
//
//            // Now fetch its neighbours and mark 'em visited as well
//            for (int deltaRow = -1; deltaRow <= 1; deltaRow++) {
//                for (int deltaCol = -1; deltaCol <= 1; deltaCol++) {
//                    // Mark visited only if
//                    // - Ain't visited already
//                    // - And is a valid neighbour
//                    // - And is a land as well
//                    int nRow = row + deltaRow;
//                    int nCol = col + deltaCol;
//                    if (nRow < grid.length && nCol < grid[0].length
//                            && vis[row][col] != 1
//                            && grid[row][col] == '1'
//                    ) {
//                        vis[nRow][nCol] = 1;
//                        q.offer(new Pair(nRow, nCol));
//                    }
//                }
//            }
//        }
    }
}
