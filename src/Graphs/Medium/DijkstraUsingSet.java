package Graphs.Medium;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class DijkstraUsingSet {

    private static class Pair implements Comparable<Pair> {
        private int node;
        private int dist;

        Pair(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(@NotNull Pair o) {
            if (this.dist != o.dist)
                return Integer.compare(this.dist, o.dist);
            return Integer.compare(this.node, o.node);
        }
        // The whole point of the data structure in Dijkstra is:
        // “Always give me the node with the smallest known distance so far.”
    }

    public int[] dijkstra(int V, int[][] edges, int src) {
        // code here
        // Construct adj list from edges
        List<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int d = edge[2];
            adj.get(u).add(new Pair(v, d));
        }

        // Init configuration
        TreeSet<Pair> set = new TreeSet<>();
        set.add(new Pair(src, 0));
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        while (!set.isEmpty()) {
            Pair pair = set.pollFirst(); // Pops the smallest ele in one shot
            Integer node = pair.node;
            Integer d = pair.dist;

            for (Pair nei : adj.get(node)) {
                // Check if the node's already there in the set and remove it
                int newDist = d + nei.dist;
                if (newDist < dist[nei.node]) {
                    set.remove(new Pair(nei.node, dist[nei.node])); // Takes log(V)
                    dist[nei.node] = newDist;
                    set.add(new Pair(nei.node, newDist));
                }
            }
        }


        for (int i = 0; i < V; i++) {
            if (dist[i] == Integer.MAX_VALUE) dist[i] = -1;
        }
        return dist;
    }

}
