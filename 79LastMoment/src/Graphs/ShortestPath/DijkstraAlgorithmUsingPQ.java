package Graphs.ShortestPath;

import java.util.*;

public class DijkstraAlgorithmUsingPQ {

    private static class NodeInfo {

        int node;
        int wt;

        public NodeInfo(int node, int wt) {
            this.node = node;
            this.wt = wt;
        }

    }

    public int[] dijkstra(int V, int[][] edges, int src) {
        // code here
        // Build the adj list
        List<List<NodeInfo>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(new NodeInfo(edge[1], edge[2]));
            adj.get(edge[1]).add(new NodeInfo(edge[0], edge[2]));
        }

        // Dist arr
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);

        // Use PQ instead for relaxing the edges instead of traditional Queue
        PriorityQueue<NodeInfo> pq = new PriorityQueue<>((a, b) -> a.wt - b.wt);
        pq.offer(new NodeInfo(0, 0));
        dist[0] = 0;

        while (!pq.isEmpty()) { // Gonna run for V (#vertices) times
            NodeInfo nodeInfo = pq.poll(); // Would take log (heapSize)
            int node = nodeInfo.node;
            int d = nodeInfo.wt; // Where d is the dist of this node from the src node

            if (d > dist[node]) continue; // Ignore this entry because it's outdated
            // Because a better entry w smaller distance (because of PQ) has already been processed

            for (NodeInfo neiInfo : adj.get(node)) { // Gonna run for number of edges, i.e., V-1 for dense graph
                int nei = neiInfo.node;
                int neiDist = neiInfo.wt;

                int newDist = d + neiDist;
                if (newDist < dist[nei]) {
                    dist[nei] = newDist;
                    // And then only we offer the node to the PQ
                    pq.offer(new NodeInfo(nei, newDist)); // Also gonna take log (heapSize)
                }
            }
        }

        return dist;

        // Time complexity: O(E log V)

        // Also, why PQ over traditional Queue?
        // Because if we use queue we'll have to encounter too many iterations even for the same nodes.
        // However, using PQ, we're being greedy, and we only process the minimal distances first.
    }
}
