package Graphs;

import java.util.ArrayList;
import java.util.List;

public class CycleDetectionUndirectedDFS {

    public boolean isCycle(int V, int[][] edges) {
        // Code here

        // We're given edges list
        // Formulate the adj list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            // Since it's an undirected graph
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        // Now, since it's given a graph could have multiple comps
        // Also, maintain a visited array
        int[] vis = new int[V];

        for (int i = 0; i < V; i++) {
            if (vis[i] != 1) {
                boolean cycleDetected = dfs(i, -1, vis, adj);
                if (cycleDetected) return true;
            }
        }

        return false;

        // Time complexity: O(V + E)
        // Space complexity: O(V) for vis + O(V) recursion call stack
    }

    private boolean dfs(int node, int par, int[] vis, List<List<Integer>> adj) {
        vis[node] = 1;

        // Fetch the neighbors and go into depth
        for (int nei : adj.get(node)) {
            // Cycle detection = encountering a non-parent who's already visited
            if (vis[nei] != 1) {
                boolean cycleDetected = dfs(nei, node, vis, adj);
                if (cycleDetected) return true;
            } else if (nei != par) return true; // visited but non-parent
        }

        return false;
    }
}
