package Graphs;

import java.util.*;

public class CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        // Topological Sort: A characteristic of DAGs.
        // It produces a linear ordering of vertices such that
        // for every directed edge u -> v, u appears before v.

        // How can we leverage the topo sort here?
        // A valid topological ordering exists if and only if
        // the directed graph is acyclic.
        //
        // Since, topo sort mainly determines whether a given graph
        // has a cycle. We can employ it in here to confirm the
        // same.
        //
        // If there's a cycle found -- meaning certain nodes could
        // never have reached the indegree of 0, that means there's
        // a cyclic dependency between some courses.

        int N = numCourses;
        int[][] pre = prerequisites;

        if (pre == null || pre.length == 0) return true;

        // Step 1. Prep the adj list and indegrees array
        int[] indegree = new int[N];
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < pre.length; i++) {
            // Given: [a, b]
            // b being pre-requisite implies b should be done first
            // this, b -> a, b comes before a
            int u = pre[i][1];
            int v = pre[i][0];
            adj.get(u).add(v); // b -> a
            indegree[v]++; // Since a got incoming nodes
        }

        // Step 2. Insert all the nodes w indegree 0 into bfs queue
        // Because all the courses w indegree 0 doesn't depend upon
        // any other courses.
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            if (indegree[i] == 0)
                q.offer(i);
        }

        int cnt = 0; // Count of courses processed (dequeued)

        // Step 3. Traverse the queue and process the courses
        // and dequeue the courses w no further prerequisites
        // left.
        while (!q.isEmpty()) {
            int node = q.poll();
            cnt += 1;

            // Iterate over its neighbors -- dependent courses
            for (int nei : adj.get(node)) {
                indegree[nei]--;
                if (indegree[nei] == 0) q.offer(nei);
            }
        }

        return cnt == N;

        // Time: O(V + E)
        // Space: O(V + E)

    }
}
