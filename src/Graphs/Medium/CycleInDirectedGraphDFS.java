package Graphs.Medium;

import java.util.ArrayList;
import java.util.List;

public class CycleInDirectedGraphDFS {

    public boolean isCyclic(int V, int[][] edges) {
        // code here
        // Construct the adj list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
        }

        // Traverse over each connected comp and detect a cycle if there's one
        boolean vis[] = new boolean[V];
        boolean pathVis[] = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!vis[i] && detectCycleDFS(i, vis, pathVis, adj)) return true;
        }
        return false;
    }

    private boolean detectCycleDFS(int node, boolean[] vis, boolean[] pathVis, List<List<Integer>> adj) {
        // Traverse each node and mark its entry in vis and pathVis.
        // Upon reaching the end node and then while backtracking, remove their entries from a path vis.
        // Cuz you've traversed a path and there was no cycle found.
        // Note that when you reach a node that is previously visited as well as path visited then only it's a cycle.
        vis[node] = true;
        pathVis[node] = true;

        // Fetch and process its neighbors
        for (int n : adj.get(node)) {
            // We get to a neighbor, there could only be two cases:
            // If it's visited or NOT.
            // And if it is visited, then we're not gonna make recursive call,
            // but rather check if it's path visited as well.
            if (!vis[n]) {
                boolean isDetected = detectCycleDFS(n, vis, pathVis, adj);
                if (isDetected) return true;
            } else if (pathVis[n]) return true;
        }

        pathVis[node] = false; // Only after exploring every neighbor
        return false;
    }

    // Using a single vis array
    private boolean detectCycleDFSII(int node, int[] vis, List<List<Integer>> adj) {
        // Traverse each node and mark its entry in vis and pathVis.
        // Upon reaching the end node and then while backtracking, remove their entries from a path vis.
        // Cuz you've traversed a path and there was no cycle found.
        // Note that when you reach a node that is previously visited as well as path visited then only it's a cycle.
        vis[node] = 2; // visited + path visited

        // Fetch and process its neighbors
        for (int n : adj.get(node)) {
            // We get to a neighbor, there could only be two cases:
            // If it's visited or NOT.
            // And if it is visited, then we're not gonna make recursive call,
            // but rather check if it's path visited as well.
            if (vis[n] == 0) {
                boolean isDetected = detectCycleDFSII(n, vis, adj);
                if (isDetected) return true;
            } else if (vis[n] == 2) return true;
        }

        vis[node] = 1; // Only after exploring every neighbor
        return false;
    }

}
