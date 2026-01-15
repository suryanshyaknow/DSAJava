package Graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Arrays;

public class ShortestPathUG {

    public int[] shortestPath(int V, int[][] edges, int src) {
        // code here
        // Prepare an adjancency list
        List<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        int[] dist = new int[V];
        Arrays.fill(dist, -1);

        Queue<Integer> q = new LinkedList<>();
        q.offer(src);
        dist[src] = 0;

        while (!q.isEmpty()) {
            int node = q.poll();

            // process the neigbors
            for (int nei: adj.get(node)) {
                if (dist[nei] != -1) {
                    q.offer(nei);
                    dist[nei] = dist[node] + 1;

                }
            }
        }

        return dist;
    }
}
