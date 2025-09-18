package Graphs.Medium;

import java.util.Arrays;

public class BellManFordAlgo {


    // Step i. Relax all the edges N-1 times sequentially.
    // Step ii. Relaxation: dist[u] + wt < dist[v], then update dist[v]
    // cuz we've got better (even shorter) distance for node v.

    // Why N - 1 times?
    // Since, in a graph of N nodes, in worst case, we'll take N-1 edges to reach from the first to last,
    // thereby iterating for N-1 nodes.
    // And moreover, if we go N nodes, we've revisited a node -> that's a cycle.


    public int[] bellmanFord(int V, int[][] edges, int src) {
        // code here

        int[] dist = new int[V];
        int inf = (int) 1e8;
        Arrays.fill(dist, inf);
        dist[src] = 0;

        // O (V * E)
        for (int i = 0; i < V - 1; i++) {
            // Relax all the edges
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                int wt = edge[2];

                if (dist[u] == inf) continue; // If it's inf itself, it won't help in determining dist[v]

                int newDist = dist[u] + wt;
                if (newDist < dist[v]) dist[v] = newDist;
            }
        }
        // Determine negative cycle via Vth iteration
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];

            if (dist[u] == inf) continue; // this V couldn't be reached
            int newDist = dist[u] + wt;
            if (newDist < dist[v]) {
                // Shouldn't be happening unless graph contains a negative cycle
                return new int[]{-1};
            }
        }
        return dist;
    }


}
