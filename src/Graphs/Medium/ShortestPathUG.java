package Graphs.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathUG {

    public int[] shortestPath(ArrayList<ArrayList<Integer>> adj, int src) {
        // code here
        int V = adj.size();

        int[] dist = new int[V];
        Arrays.fill(dist, -1); // -1: not visited yet

        Queue<Integer> q = new LinkedList<>();
        q.offer(src);
        dist[src] = 0;

        while (!q.isEmpty()) {
            Integer node = q.poll();

            // Process the neighbors
            for (int nei: adj.get(node)) {
                if (dist[nei] == -1) {
                    q.offer(nei);
                    dist[nei] = dist[node] + 1;
                }

                // Note: We don't need to do this because the first time we hit a node, it’s guaranteed to be the shortest distance.
                // No need to update later, because BFS won’t find a shorter path — it only finds longer or equal ones afterward.
                // However, in weighted graphs, you might encounter a shorter path later
                // (since one edge could be cheaper than another), so you must keep updating dist[nei]. That’s why Dijkstra uses a priority queue.
                // else dist[nei] = Integer.min(dist[nei], dist[node] + 1);
            }
        }

        return dist;
    }

}
