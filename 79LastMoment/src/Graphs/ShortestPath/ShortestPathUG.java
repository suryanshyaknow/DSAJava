package Graphs.ShortestPath;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Arrays;

public class ShortestPathUG {

    public int[] shortestPath(int V, int[][] edges, int src) {
        // code here
        // Build amn adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        // Note: DFS cannot guarantee the shortest path
        // because it explores depth-wise and may reach
        // a node through a longer path before a shorter one.
        int[] dist = new int[V];
        Arrays.fill(dist, -1);
        Queue<Integer> q = new LinkedList<>();
        q.offer(src);
        dist[src] = 0;

        while (!q.isEmpty()) {
            int node = q.poll();

            for (int nei : adj.get(node)) {
                if (dist[nei] == -1) {
                    q.offer(nei);
                    dist[nei] = dist[node] + 1;
                }
            }

        }
        // Note: The first time we hit a node, it’s guaranteed to be the shortest distance.
        // No need to update later, because BFS won’t find a shorter path — it only finds longer or equal ones afterward.
        // However, the same aint the case in weighted graphs.
        return dist;
    }
}
