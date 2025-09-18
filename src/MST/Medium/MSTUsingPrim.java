package MST.Medium;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class MSTUsingPrim {

    private static class Pair {
        private int node;
        private int wt;

        Pair(int node, int wt) {
            this.node = node;
            this.wt = wt;
        }
    }

    public int spanningTree(int V, int[][] edges) {
        // code here
        // Construct the adjacency list
        List<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int edge[] : edges) {
            adj.get(edge[0]).add(new Pair(edge[1], edge[2]));
        }

        // Step i. Pop the minimum weight edge from the min heap (priority queue).
        // Step ii. If the edge connects to a new/unvisited node, include it in MST and push its edges into the heap.
        // Step iii. If it connects to an already visited node, skip it (ignore and don’t add it to MST).
        // Intuition: Greedy. We always pop the node w min wt and mark it as visited and never process it again.

        // Init config
        boolean[] vis = new boolean[V];
        int sum = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> (a.wt - b.wt));
        pq.offer(new Pair(0, 0));

        while (!pq.isEmpty()) {
            Pair pair = pq.poll(); // Node w min wt
            int node = pair.node;
            int wt = pair.wt;

            // Also add the edge to mst if asked
            vis[node] = true;
            sum += wt;

            // Process the neighbors
            for (Pair n : adj.get(node)) {
                int nei = n.node;
                int neiWt = n.wt;

                if (vis[nei]) continue;
                pq.offer(new Pair(nei, neiWt));
            }
        }

        return sum;
    }

}
