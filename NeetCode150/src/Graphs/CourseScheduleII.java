package Graphs;

import java.util.*;

public class CourseScheduleII {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int N = numCourses;
        int[][] pre = prerequisites;

        // I'm thinking of leveraging the topo sort here.
        // Topo sort is a characteristic of DAGs.
        // It produces a linear ordering of vertices (courses)
        // such that if there'a directed edge bw u -> v, then
        // it means u appears before v.
        //
        // We can transalte the same concept to the courses here.
        // Courses w no prerequsities can be attempted before.
        //
        // And if no such combination of courses is possible then
        // that means there's a cyclic dependency aomng the courses,
        // and hence it's impossible to finsih 'em.
        //
        // I'll implement topo using Kahn's algo which is BFS
        // way of traversal.

        // Step 1. Prep adj list and indegrees array for BFS
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        } // O(N)
        int[] indegree = new int[N];
        // Populate the adj list and indegree
        for (int i = 0; i < pre.length; i++) {
            // [a, b], given b is prerequiste
            // Meaning b should appear before a.
            // i.e. a <- b
            int u = pre[i][1]; // b -> a
            int v = pre[i][0];
            adj.get(u).add(v);
            indegree[v]++; // v's incoming nodes
        } // O(E)

        Queue<Integer> q = new LinkedList<>();
        int[] res = new int[N];
        // Step 2. Insert all the nodes w zero indegree into queue
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) q.offer(i);
        } // O(E)

        // Step 3. Dequeue the nodes in queue and decrease the
        // indegree of their neighbors -- dependent courses.
        // It signifies that dequeued course no longer count
        // as prerequisites for its dependent courses.
        int cnt = 0; // pointer for populating the res
        while (!q.isEmpty()) {
            int node = q.poll();
            res[cnt++] = node;

            // Now iterate over node's dependent courses
            for (int nei : adj.get(node)) {
                indegree[nei]--;
                if (indegree[nei] == 0)
                    q.offer(nei);
            }
        } // O(N + E)

        return cnt == N ? res : new int[0];

        // Time: O(N + E)
        // Space: O(N + E)
    }
}
