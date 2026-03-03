package Graphs.CycleDetectionInDirectedGraphs;

import java.util.ArrayList;
import java.util.List;

public class CycleDetectionDirectedDFS {

    public boolean isCyclic(int V, int[][] edges) {
        // Code here
        // Build an adj list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            // edge: u -> v
            adj.get(u).add(v);
        }

        // Cycle detection = encountering a node that is visited as well as path visited
        int[] vis = new int[V];
        int[] pathVis = new int[V];

        // Iterate over each comp and detect a cycle
        for (int i = 0; i < V; i++) {
            if (vis[i] != 1) {
                if (dfs(i, vis, pathVis, adj)) return true;
            }
        }
        return false;
    }

    private boolean dfs(int node, int[] vis, int[] pathVis, List<List<Integer>> adj) {
        int V = adj.size();
        vis[node] = 1;
        pathVis[node] = 1;

        for (int nei : adj.get(node)) {
            // Cycle detection = encountering a node visited as well as pathVisited
            if (vis[nei] != 1) {
                if (dfs(nei, vis, pathVis, adj)) return true;
            } else if (pathVis[nei] == 1) return true;
        }

        // Backtracking
        pathVis[node] = 0;
        return false;
    }
}
