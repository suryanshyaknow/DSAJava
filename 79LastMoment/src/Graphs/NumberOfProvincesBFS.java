package Graphs;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfProvincesBFS {

    public int findCircleNum(int[][] isConnected) {
        int N = isConnected.length;

        // We're give adj matrix

        // Maintain a visited array
        int[] vis = new int[N];

        // Iterate each node separately given it's
        // not already visited
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (vis[i] != 1) {
                bfs(i, vis, isConnected);
                cnt += 1;
            }
        }
        return cnt;

        // Time complexity: O(N^2) each node visit plus each nei visit
        // Space complexity: O(N) for visited
    }

    private void bfs(int node, int[] vis, int[][] adj) {
        int N = adj.length;

        Queue<Integer> q = new LinkedList<>();
        q.offer(node);
        vis[node] = 1;

        while (!q.isEmpty()) {
            int node1 = q.poll();

            // Iterate over neighbors -- connected & unvisited
            for (int i = 0; i < N; i++) {
                if (vis[i] != 1 && adj[node1][i] == 1) {
                    q.offer(i);
                    vis[i] = 1;
                }
            }
        }
    }

}
