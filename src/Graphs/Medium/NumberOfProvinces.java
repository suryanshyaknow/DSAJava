package Graphs.Medium;

import java.util.ArrayList;

public class NumberOfProvinces {

    public int findCircleNum(int[][] isConnected) {
        // First off, convert Matrix into adj list
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        int N = isConnected.length;
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (isConnected[i][j] == 1 && i != j) {
                    adj.get(i).add(j);
                    adj.get(j).add(i);
                }
            }
        }

        int[] vis = new int[N];
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (vis[i] != 1) {
                dfs(i, vis, adj);
                count += 1;
            }
        }
        return count;

        // Time complexity: O(N) dfs is iterating over each node + O(V + 2E)
        // Space complexity: O(N) for visited arr + O(N) for recursive stack space worst case
    }

    private void dfs(int node, int[] vis, ArrayList<ArrayList<Integer>> adj) {
        // First off, mark the node as visited
        vis[node] = 1;

        for (int neighbour : adj.get(node)) {
            if (vis[neighbour] != 1)
                dfs(neighbour, vis, adj);
        }
    }


}
