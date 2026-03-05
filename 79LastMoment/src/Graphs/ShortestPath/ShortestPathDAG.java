package Graphs.ShortestPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ShortestPathDAG {

    private static class Tuple {

        int node;
        int wt;

        public Tuple(int node, int wt) {
            this.node = node;
            this.wt = wt;
        }
    }

    public int[] shortestPath(int V, int E, int[][] edges) {
        // Code here

        // Step i. Generate topo sort
        // Step ii. Use the topo sort order to relax the edges
        // Step iii. At last traverse the dist arr and replace the inf
        // w -1 since they're unreachable nodes.

        // Note: Topological sort is used for shortest path in a DAG
        // because it gives you the correct order to relax edges exactly once.

        // Build an adj list
        List<List<Tuple>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(new Tuple(edge[1], edge[2]));
        }

        // Step i
        Stack<Integer> st = getTopoSort(adj);

        // Step ii
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        while (!st.isEmpty()) {
            int node = st.pop();

            if (dist[node] == Integer.MAX_VALUE) continue;

            for (Tuple tup : adj.get(node)) {
                int nei = tup.node;
                int wt = tup.wt;

                dist[nei] = Math.min(dist[nei], dist[node] + wt);
            }
        }

        for (int i = 0; i < V; i++) {
            if (dist[i] == Integer.MAX_VALUE)
                dist[i] = -1;
        }
        return dist;
    }

    private Stack<Integer> getTopoSort(List<List<Tuple>> adj) {
        int V = adj.size();

        int[] vis = new int[V];
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < V; i++) {
            if (vis[i] != 1)
                dfs(i, vis, adj, st);
        }

        return st;
    }

    private void dfs(int node, int[] vis, List<List<Tuple>> adj, Stack<Integer> st) {
        vis[node] = 1;

        for (Tuple tup : adj.get(node)) {
            int nei = tup.node;
            if (vis[nei] != 1)
                dfs(nei, vis, adj, st);
        }

        st.add(node);
    }
}
