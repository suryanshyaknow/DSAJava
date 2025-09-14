package Graphs.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class ShortestPathDAGDijkstra {

    private static class Pair {
        private int node;
        private int weight;

        Pair(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    public int[] shortestPath(int V, int E, int[][] edges) {
        // Construct the adj matrix
        List<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            adj.get(u).add(new Pair(v, wt));
        }

        // Init config
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> (a.weight - b.weight));
        pq.offer(new Pair(0, 0));
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        while (!pq.isEmpty()) {
            Pair pair = pq.poll(); // Guaranteed to be node w shortest dist on the first time
            int node = pair.node;
            int d = pair.weight;
            if (d > dist[node]) continue;

            for (Pair p : adj.get(node)) {
                int nei = p.node;
                int dNei = p.weight;
                int newDist = dNei + d;
                if (newDist < dist[nei]) {
                    dist[nei] = newDist;
                    pq.offer(new Pair(nei, newDist));
                }
            }
        }
        for (int i = 0; i < V; i++) {
            if (dist[i] == Integer.MAX_VALUE) dist[i] = -1;
        }
        return dist;
    }
}
