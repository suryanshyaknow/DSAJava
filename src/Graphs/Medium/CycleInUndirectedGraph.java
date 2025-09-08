package Graphs.Medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CycleInUndirectedGraph {

    private static class Pair {
        int node;
        int parent;

        Pair(int node, int parent) {
            this.node = node;
            this.parent = parent;
        }
    }

    public boolean isCycle(int V, int[][] edges) {
        // Code here
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Construct adj list from edges[][]
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        int[] vis = new int[V];
        for (int i = 0; i < V; i++) {
            if (vis[i] != 1) {
//                boolean isDetected = detectCycleBFS(i, vis, adj);
                boolean isDetected = detectCycleDFS(new Pair(i, -1), vis, adj);
                if (isDetected) return true;
            }
        }
        return false;
    }

    public boolean detectCycleDFS(Pair startingNode, int[] vis, List<List<Integer>> adj) {
        int node = startingNode.node;
        int parent = startingNode.parent;
        vis[node] = 1;

        for (int neighbor : adj.get(node)) {
            if (neighbor != parent) {
                if (vis[neighbor] == 1) return true;
                else {
                    boolean isDetected = detectCycleDFS(new Pair(neighbor, node), vis, adj);
                    if (isDetected) return true;
                }
            }
        }
        return false;
    }

    public boolean detectCycleBFS(int startingNode, int[] vis, List<List<Integer>> adj) {
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(startingNode, -1)); // Since root didn't have any parent
        vis[startingNode] = 1;

        while (!q.isEmpty()) {
            Pair pair = q.poll();
            int node = pair.node;
            int parent = pair.parent;

            // Process the neighbors
            for (int neighbor : adj.get(node)) {
                if (neighbor != parent) {
                    if (vis[neighbor] == 1)
                        return true;
                    else {
                        vis[neighbor] = 1;
                        q.offer(new Pair(neighbor, node));
                    }
                }
            }
        }
        return false;
    }

}
