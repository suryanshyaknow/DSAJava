package Graphs;

public class NumberOfProvincesDFS {

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
                dfs(i, vis, isConnected);
                cnt += 1;
            }
        }
        return cnt;


        // Time complexity: O(N^2) each node visit plus each nei visit
        // Space complexity: O(N) for recursion call stack + O(N) for visited
    }

    private void dfs(int node, int[] vis, int[][] adj) {
        int N = adj.length;
        vis[node] = 1;

        // Iterate over connected & unvisited neighbors
        for (int nei = 0; nei < N; nei++) {
            if (adj[node][nei] == 1 && vis[nei] != 1) {
                dfs(nei, vis, adj);
            }
        }
    }
}
