package Graphs;

import java.util.*;

public class CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Prep indegree and adj list
        int V = numCourses;
        List<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i=0; i < V; i++) adj.add(new ArrayList<>());
        int[] indegree = new int[V];

        for (int[] edge: prerequisites) {
            int course = edge[0];
            int preReq = edge[1];

            adj.get(preReq).add(course); // prereq -> course
            indegree[course]++;
        }

        Queue<Integer> q = new LinkedList<>();
        // Initial config
        for (int i=0; i < V; i++) {
            if (indegree[i] == 0)
                q.offer(i);
        }

        ArrayList<Integer> res= new ArrayList<>();
        while (!q.isEmpty()) {
            int node = q.poll();
            res.add(node);

            // Fetch its neigbors and decrease their indegrees
            for (int nei: adj.get(node)) {
                indegree[nei]--;
                if (indegree[nei] == 0)
                    q.offer(nei);
            }
        }
        return res.size() == V; // Cycle doesn't exits, al courses could be done
    }

}
