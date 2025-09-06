package Graphs.Medium;

import java.util.LinkedList;
import java.util.Queue;

public class FloodFill {

    private static class Pair {
        int first;
        int second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int N = image.length;
        int M = image[0].length;
        int[][] vis = new int[N][M];

        // Create a deep copy
        int[][] res = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                res[i][j] = image[i][j];
            }
        }

        int initColor = image[sr][sc];
        if (initColor != color)
            dfs(new Pair(sr, sc), vis, image, res, color, initColor);
        return res;
    }

    private static void dfs(Pair pix, int[][] vis, int[][] image, int[][] res, int color, int initColor) {
        // First off mark the node as visited
        // And assign the given color
        int r = pix.first;
        int c = pix.second;
        vis[r][c] = 1;
        res[r][c] = color;

        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int dir[] : dirs) {
            int nRow = r + dir[0];
            int nCol = c + dir[1];
            if (nRow >= 0 && nCol >= 0 && nRow < image.length && nCol < image[0].length
                    && vis[nRow][nCol] != 1 && image[nRow][nCol] == initColor
            ) {
                // res[nRow][nCol] = color;
                dfs(new Pair(nRow, nCol), vis, image, res, color, initColor);
            }
        }
    }

    private static void bfs(Pair pix, int[][] vis, int[][] image, int[][] res, int color, int initColor) {
        Queue<Pair> q = new LinkedList<>();
        q.offer(pix);
        // Assign color and mark visited
        vis[pix.first][pix.second] = 1;
        res[pix.first][pix.second] = color;

        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while (!q.isEmpty()) {
            Pair pair = q.poll();
            int r = pair.first;
            int c = pair.second;

            for (int[] dir : dirs) {
                int nRow = r + dir[0];
                int nCol = c + dir[1];
                if (nRow >= 0 && nCol >= 0
                        && nRow < image.length && nCol < image[0].length
                        && vis[nRow][nCol] != 1 && image[nRow][nCol] != initColor) {
                    res[nRow][nCol] = color;
                    vis[nRow][nCol] = 1;
                    q.offer(new Pair(nRow, nCol));
                }
            }
        }

    }
}
