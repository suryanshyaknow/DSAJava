package Graphs.CycleDetectionDirected;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TopoSort {

    public ArrayList<Integer> topoSort(int V, int[][] edges) {
        // code here
        // Build an adj list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
        }

        int[] vis = new int[V];
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < V; i++) {
            if (vis[i] != 1)
                dfs(i, vis, st, adj);
        }

        // Add all the elements from the st into res list
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            res.add(st.pop());
        }
        return res;

        // NOTE: Cycles can't be detected reliably using DFS Topo (this approach)
        // because In DFS-based topo sort, all nodes get visited and pushed to the
        // stack even if a cycle exists, so the resulting list size is always V and cannot indicate a cycle.
    }

    private void dfs(int node, int[] vis, Stack<Integer> st, List<List<Integer>> adj) {
        vis[node] = 1;

        for (int nei : adj.get(node)) {
            if (vis[nei] != 1) {
                dfs(nei, vis, st, adj);
                vis[nei] = 1;
            }
        }
        st.add(node);
    }

}
