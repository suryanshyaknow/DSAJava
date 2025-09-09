package Graphs.Medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class KahnsAlgo {

    public static ArrayList<Integer> topoSort(int V, int[][] edges) {
        // code here
        // Construct adj list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        int[] indegree = new int[V];
        for (int[] edge : edges) {
            indegree[edge[1]]++;

            // Populate adj list as well
            adj.get(edge[0]).add(edge[1]);
        }

        Queue<Integer> queue = new LinkedList<>();
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) queue.offer(i);
        }

        // Kahn's algo:
        // i. Init config: Add nodes w zero indegree to queue
        // ii. Then process each node from the queue and decrement their edges degree by 1.
        while (!queue.isEmpty()) {
            int node = queue.poll();
            res.add(node);

            for (int n : adj.get(node)) {
                indegree[n]--;
                if (indegree[n] == 0)
                    queue.offer(n);
            }
        }
        return res;
    }




}
