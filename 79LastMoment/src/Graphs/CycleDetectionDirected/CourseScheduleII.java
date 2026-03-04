package Graphs.CycleDetectionDirected;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseScheduleII {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // [a, b] -> b being the prerequisite
        // Implies b -> a

        // To determine finishing all courses
        // Ensure there's not a cyclic dependency
        // Leverage topo sort for that.
        // See if the courses can be sorted in a correct
        // topological manner.

        int V = numCourses;
        int[][] edges = prerequisites;

        // Build an adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        // Also gotta build the indegrees array
        int[] indegree = new int[V];
        for (int edge[] : edges) {
            indegree[edge[0]]++;
            adj.get(edge[1]).add(edge[0]);
        }

        Queue<Integer> q = new LinkedList<>();
        // Add all nodes with indegree 0 to the queue.
        // These nodes have no prerequisites, meaning they can be processed
        // immediately.
        // They serve as the starting points since no other course is required
        // before them.
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0)
                q.offer(i);
        }

        int[] topo = new int[V];
        int ptr = 0;
        while (!q.isEmpty()) {
            int node = q.poll();
            topo[ptr++] = node;

            for (int nei : adj.get(node)) {
                indegree[nei]--;
                if (indegree[nei] == 0) q.offer(nei);
            }
        }

        if (ptr != V) return new int[0]; // Cyclic dependency encountered!

        return topo;
    }
}
