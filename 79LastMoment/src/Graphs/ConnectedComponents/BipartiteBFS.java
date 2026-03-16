package Graphs.ConnectedComponents;

import java.util.*;

public class BipartiteBFS {

    public boolean isBipartite(int[][] graph) {
        int V = graph.length;
        // We're already given the adj list as graph

        // Bipartite: A graph is said to be bipartite given it
        // could be colored w exactly two colors such that no
        // two adjacent nodes have the same color.
        // Also, a grpah w an odd cycle can't be Bipartite.

        int[] color = new int[V];
        Arrays.fill(color, -1);

        for (int i = 0; i < V; i++) {
            if (color[i] == -1) {
                if (bfs(i, 0, color, graph) == false)
                    return false;
            }
        }

        return true;
    }

    private boolean bfs(int node, int col, int[] color, int[][] graph) {
        int V = color.length;

        Queue<Integer> q = new LinkedList<>();
        q.offer(node);
        color[node] = col;

        while (!q.isEmpty()) {
            int node1 = q.poll();
            int col1 = color[node1];

            for (int nei : graph[node1]) {
                if (color[nei] == -1) {
                    color[nei] = 1 - col1;
                    q.offer(nei);
                } else if (color[nei] == col1)
                    return false;
            }
        }
        return true;
    }

}
