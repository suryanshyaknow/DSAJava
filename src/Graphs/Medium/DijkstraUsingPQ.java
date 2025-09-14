package Graphs.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstraUsingPQ {

    private static class Pair {
        private int node;
        private int dist;

        Pair(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
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

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.dist - b.dist);
        pq.offer(new Pair(src, 0));
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        while (!pq.isEmpty()) { // O(V)
            Pair pair = pq.poll(); // logarithmic
            int node = pair.node;
            int d = pair.dist;

            if (d > dist[node]) // We don't wanna process that candidate. Cuz for the first iteration, it was 1e9.
                continue;

            // Process the neighbors
            for (Pair nei : adj.get(node)) { // O(V - 1) in the worst case where every node is connected to every another.
                int newDist = d + nei.dist;
                if (newDist < dist[nei.node]) {
                    dist[nei.node] = newDist;
                    pq.offer(new Pair(nei.node, dist[nei.node])); // logarithmic
                }
            }
        }

        for (int i = 0; i < V; i++) {
            if (dist[i] == Integer.MAX_VALUE) dist[i] = -1;
        }
        return dist;

        // Time complexity: O(E log V)
        // O(V * (pop vertex from min heap + no. of edges on each vertex * push into PQ))
        // O(V * (log(heap size) + ne * log (heap size) ))
        // O(V * log (heap Size) * (ne + 1))
        // O(V * log heapSize * V)
        // O(V^2 * log heapSize)
        // O(V^2 * log V^2)
        // O (2 * V^2 * log V)
        // O(E log V)
        // Dijkestra's algo won't work graph w any negative node or w negative cycle cuz it'll fall in infinte loop.
    }
}
