package Graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class DetectCycleUG {

    private static class Pair {
        int node;
        int par;

        Pair(int node, int parent) {
            this.node = node;
            this.par = parent;
        }
    }

    public boolean isCycle(int V, int[][] edges) {
        // Code here
        // prep an adj list
        List<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        // Iterate over each node cuz there might some
        // disconnected components
        int[] vis = new int[V];
        Arrays.fill(vis, -1);
        for (int i = 0; i < V; i++) {
            if (vis[i] == -1) {
                if (dfs(new Pair(i, -1), vis, adj)) return true;
            }
        }
        return false;
    }

    private static boolean dfs(Pair pair, int[] vis, List<ArrayList<Integer>> adj) {
        // Mark the curr node as visited
        int node = pair.node;
        int par = pair.par;
        vis[node] = 1;

        // Process the neighbors
        for (int nei : adj.get(node)) {
            // First off if nei is not a parent
            if (nei != par) {
                // and still it's visited
                if (vis[nei] == 1)
                    return true; // cycle confirmed
                else {
                    boolean detect = dfs(new Pair(nei, node), vis, adj);
                    if (detect) return true;
                }
            }
        }
        return false;
    }
}
