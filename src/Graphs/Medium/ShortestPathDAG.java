package Graphs.Medium;

import com.sun.security.jgss.InquireType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ShortestPathDAG {

    private static class Pair {
        private int node;
        private int weight;

        Pair(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    public int[] shortestPath(int V, int E, int[][] edges) {
        // Code here
        // Construct the adj list
        List<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        // Iterate over edges and populate adj list
        for (int edge[] : edges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            adj.get(u).add(new Pair(v, wt));
        }

        // Step i. Apply top sort algo
        // Step ii. Remove each node from the stack and relax their edges in the dist array;
        // O(V + E)
        boolean vis[] = new boolean[V];
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < V; i++) {
            if (!vis[i])
                dfsTopoSort(i, vis, adj, st);
        }

        // Step ii.
        int dist[] = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0; // src node

        // O(V + E)
        while (!st.isEmpty()) {
            Integer node = st.pop();
            int d = dist[node];

            if (d == Integer.MAX_VALUE) continue; // 🚨 skip unreachable nodes

            for (Pair nei : adj.get(node)) {
                int dNei = d + nei.weight;
                dist[nei.node] = Integer.min(dist[nei.node], dNei);
            }
        }

        // Replace rest of infinities w -1
        for (int i = 0; i < V; i++) {
            if (dist[i] == Integer.MAX_VALUE) dist[i] = -1;
        }
        return dist;
    }

    private void dfsTopoSort(int node, boolean[] vis, List<ArrayList<Pair>> adj, Stack<Integer> st) {
        vis[node] = true;

        // Process the neighbors
        for (Pair nei : adj.get(node)) {
            if (!vis[nei.node])
                dfsTopoSort(nei.node, vis, adj, st);
        }
        st.push(node);
    }

}
