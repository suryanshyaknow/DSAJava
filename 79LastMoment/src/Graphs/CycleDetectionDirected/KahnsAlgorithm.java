package Graphs.CycleDetectionDirected;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class KahnsAlgorithm {

    public ArrayList<Integer> topoSort(int V, int[][] edges) {
        // code here

        // u -> v implies v depends upon v.
        // Meaning you gotta go thru u to get v.

        // Step i. Create an indegrees array
        // Step ii. Add all the nodes w zero indegrees to queue.
        // Step iii. Poll each node at a time, and reduce the indegree of their
        // neihbors.

        // Build adj list and indegree array
        List<List<Integer>> adj = new ArrayList<>();
        for (int i=0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        int[] indegree = new int[V];
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];


            // u -> v; indegree of v is 1.
            indegree[v]++;
            adj.get(u).add(v);
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i =0; i < V; i++) {
            if (indegree[i] == 0)
                q.offer(i);
        }

        ArrayList<Integer> res = new ArrayList<>();
        while(!q.isEmpty()) {
            int node = q.poll();
            res.add(node);

            for (int nei : adj.get(node)) {
//                if (indegree[nei] != 0) { // Redundant check
                    indegree[nei]--;

                    if (indegree[nei] == 0)
                        q.offer(nei);
//                }
            }
        }
        return res;
    }

}
