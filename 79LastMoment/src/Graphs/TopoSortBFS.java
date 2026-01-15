package Graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TopoSortBFS {

    public ArrayList<Integer> topoSort(int V, int[][] edges) {
        // code here

        // Step i. Compute the indegree of each edge
        // Step ii. Queue w Intitial config where 0 indegrees nodes will be added
        // step iii. Pop each node out, and reduce their indegrees.
        // Step iv. If a node's indegree hits 0, add it to q as well

        // Prep indegree and adj list
        List<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        int[] indegree = new int[V];

        for (int[] edge : edges){
            indegree[edge[1]]++;

            adj.get(edge[0]).add(edge[1]);
        }

        Queue<Integer> q = new LinkedList<>();
        // Initial config
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0)
                q.offer(i);
        }

        ArrayList<Integer> res = new ArrayList<>();
        while (!q.isEmpty()) {
            int node = q.poll();
            res.add(node);

            // Fetch its neigbors and decrease their indegrees
            for (int nei : adj.get(node)) {
                indegree[nei]--;
                if (indegree[nei] == 0)
                    q.offer(nei);
            }
        }
        return res;

    }
}
