package Graphs;

import java.util.*;

public class CourseScheduleII {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // Prep indegree and adj list
        int V = numCourses;
        List<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i=0; i < V; i++) adj.add(new ArrayList<>());
        int[] indegree = new int[V];

        for (int[] edge: prerequisites) {
            indegree[edge[0]]++;

            adj.get(edge[1]).add(edge[0]);
        }

        Queue<Integer> q = new LinkedList<>();
        // Initial config
        for (int i=0; i < V; i++) {
            if (indegree[i] == 0)
                q.offer(i);
        }

        int[] res= new int[V];
        int ptr = 0;
        while (!q.isEmpty()) {
            int node = q.poll();
            res[ptr++] = node;

            // Fetch its neigbors and decrease their indegrees
            for (int nei: adj.get(node)) {
                indegree[nei]--;
                if (indegree[nei] == 0)
                    q.offer(nei);
            }
        }
        if (ptr != V) return new int[0];
        return res;
    }

}
