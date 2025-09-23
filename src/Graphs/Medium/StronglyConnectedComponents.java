package Graphs.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class StronglyConnectedComponents {

    public int kosaraju(ArrayList<ArrayList<Integer>> adj) {
        // code here

        // Step 1. Do a DFS to record the finishing times of nodes.
        //
        // Step 2. Transpose the graph, i.e. reverse all the edges.
        //
        // Step 3. Do a DFS again but in order of decreasing finishing times.
        // Note: Now, the idea behind recording their finishing times is to
        // prevent spillage of one SCC into others.

        // Step 1. O(E + V)
        int V = adj.size();
        int[] vis = new int[V];
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < V; i++) {
            if (vis[i] != 1)
                dfs(i, vis, adj, st);
        }

        // Step 2. O(E + V)
        ArrayList<ArrayList<Integer>> adjT = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjT.add(new ArrayList<>());
            // Mark all nodes as unvisited as well
            vis[i] = 0;
        }
        for (int i = 0; i < V; i++) {
            for (int v : adj.get(i)) {
                // u (adj[i]) -> v
                // v -> u
                adjT.get(v).add(i);
            }
        }

        // Step 3. O (E + V)
        int cnt = 0;
        while (!st.isEmpty()) {
            int node = st.pop();
            if (vis[node] != 1) {
                dfsII(node, vis, adjT);
                cnt++;
            }
        }
        return cnt;

        // Time complexity: 3 * O(E+ V)
        // Space complexity: 2 * V
    }

    private void dfsII(int node, int[] vis, ArrayList<ArrayList<Integer>> adj) {
        vis[node] = 1;

        // Process its neighbors
        for (int nei : adj.get(node)) {
            if (vis[nei] != 1)
                dfsII(nei, vis, adj);
        }
    }

    private void dfs(int node, int[] vis, ArrayList<ArrayList<Integer>> adj, Stack<Integer> st) {
        vis[node] = 1;

        // Process its neighbors
        for (int nei : adj.get(node)) {
            if (vis[nei] != 1)
                dfs(nei, vis, adj, st);
        }
        st.push(node);
    }

}
