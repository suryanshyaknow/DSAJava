package Graphs.Medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TopologicalSort {

    // Topological Sort
    /*
        Linear ordering of vertices of a Directed Acyclic Graph (DAG) in such a way that for every edge u → v,
        u comes before v in the ordering.

        Significance: Say, you've got tasks w dependencies. And you can't do task B before A.
        Topological sort is the “do-it-in-the-correct-order” blueprint.
        For eg: Package managers like npm or pip use this to figure out what to install first.

        If you’ve got a bunch of things that depend on each other, and you need a “safe order”,
        topological sort is your go-to. Without it, you’re just stabbing in the dark.
     */

    public static ArrayList<Integer> topoSort(int V, int[][] edges) {
        // code here
        // Construct the adj list
        List<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
        }

        boolean[] vis = new boolean[V];
        Stack<Integer> st = new Stack<>();
        ArrayList<Integer> res = new ArrayList<>();
        int[] idx = new int[1];
        idx[0] = V - 1;

        // Iterate over each vertex and recurse
        for (int i = 0; i < V; i++) {
            if (!vis[i]) recurseDFS(i, adj, vis, st);
        }

        for (int i = 0; i < V; i++) {
            res.add(st.pop());
        }
        return res;
    }

    private static void recurseDFS(int node, List<ArrayList<Integer>> adj, boolean[] vis, Stack<Integer> st) {
        vis[node] = true;

        // Fetch the neighbors and recurse
        for (int n : adj.get(node)) {
            if (!vis[n])
                recurseDFS(n, adj, vis, st);
        }
        st.push(node);
    }

    private static void recurseDFSII(int node, List<ArrayList<Integer>> adj, boolean[] vis, int[] idx, ArrayList<Integer> res) {
        vis[node] = true;

        // Fetch the neighbors and recurse
        for (int n : adj.get(node)) {
            if (!vis[n])
                recurseDFSII(n, adj, vis, idx, res);
        }
        res.add(idx[0], node); // Won't work since array is initially empty. Will anyway have to reverse the array, and that's no good.
        idx[0]--;
    }

}
