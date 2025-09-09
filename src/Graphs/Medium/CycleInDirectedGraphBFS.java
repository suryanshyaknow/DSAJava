package Graphs.Medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CycleInDirectedGraphBFS {

    public boolean isCyclic(int V, int[][] edges) {
        // code here
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        // Construct indegree and adj list
        int[] indegree = new int[V];
        for (int[] edge : edges) {
            indegree[edge[1]]++;

            adj.get(edge[0]).add(edge[1]);
        }

        Queue<Integer> q = new LinkedList<>();
        // init config
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) q.offer(i);
        }

        int cnt = 0;
        while (!q.isEmpty()) {
            int node = q.poll();
            cnt += 1;

            for (int n : adj.get(node)) {
                indegree[n]--;

                if (indegree[n] == 0) q.offer(n);

            }
        }
        if (cnt == V) return false;
        return true;
    }
}
