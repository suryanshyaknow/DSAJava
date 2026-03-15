package Graphs.ShortestPath;

import java.util.*;

public class DijkstraAlgorithmUsingSet {

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

        // Dijkstra Using Set
        // Intenally uses BST - Red-Black Tree
        TreeSet<NodeInfo> set = new TreeSet<>((a, b) -> a.wt == b.wt ? a.node - b.node : a.wt - b.wt);
        dist[src] = 0;
        set.add(new NodeInfo(src, 0));

        while (!set.isEmpty()) {
            NodeInfo nodeInfo = set.pollFirst();
            int node = nodeInfo.node;
            int d = nodeInfo.wt;

            for (NodeInfo nei : adj.get(node)) {
                int newDist = d + nei.wt;

                // Now if it's minimal than the already present
                if (newDist < dist[nei.node]) {
                    // Remove the stale entry from the set if not inf
                    if (dist[nei.node] != Integer.MAX_VALUE)
                        set.remove(new NodeInfo(nei.node, dist[nei.node]));

                    dist[nei.node] = newDist;
                    set.add(new NodeInfo(nei.node, newDist));
                }
            }

        }

        return dist;
        // Time complexity: O(E log V)
        // Space complexity: O(V + E) for adj list + O(V) for dist + O(V) for TreeSet - stores atmost one entry per node.
        // Adj list: A list for every vertex + All the edges inside those lists.
    }
}
