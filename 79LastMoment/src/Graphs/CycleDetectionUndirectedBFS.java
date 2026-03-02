package Graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CycleDetectionUndirectedBFS {

    private static class Pair {
        private int node;
        private int par;

        public Pair(int node, int par) {
            this.node = node;
            this.par = par;
        }
    }

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
                boolean cycleDetected = bfs(i, -1, vis, adj);
                if (cycleDetected) return true;
            }
        }

        return false;

        // Time complexity: O(V + E)
        // Space complexity: O(V)
    }

    private boolean bfs(int node, int par, int[] vis, List<List<Integer>> adj) {
        int N = adj.size();

        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(node, par));
        vis[node] = 1;

        while (!q.isEmpty()) {
            Pair pair = q.poll();
            int node1 = pair.node;
            int par1 = pair.par;

            // Iterate over unvisited neighbors
            for (int nei : adj.get(node1)) {
                if (vis[nei] != 1) {
                    q.offer(new Pair(nei, node1));
                    vis[nei] = 1;
                } else if (nei != par1) return true;
            }
        }

        return false;
    }
}
