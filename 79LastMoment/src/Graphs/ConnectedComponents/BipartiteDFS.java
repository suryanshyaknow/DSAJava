package Graphs.ConnectedComponents;

import java.util.*;

public class BipartiteDFS {

    public boolean isBipartite(int[][] graph) {
        int V = graph.length;
        // We're already given the adj list as graph

        // Bipartite: A graph is said to be bipartite given it
        // could be colored w exactly two colors such that no
        // two adjacent nodes have the same color.
        // Also, a grpah w an odd cycle can't be Bipartite.
        // Also, a linear graph w any number of nodes is also Bipartite.

        int[] color = new int[V];
        Arrays.fill(color, -1);

        for (int i = 0; i < V; i++) {
            if (color[i] == -1) {
                if (!dfs(i, 0, color, graph)) return false;
            }
        }

        return true;
    }

    private boolean dfs(int node, int col, int[] color, int[][] graph) {
        color[node] = col;

        // Explore and color the neighbors
        for (int nei : graph[node]) {
            // It should not be colored
            if (color[nei] == -1) {
                if (!dfs(nei, 1 - col, color, graph)) return false;
                color[nei] = 1 - col;
            } else if (color[nei] == col) {
                // And if it is and the color is same
                return false;
            }
        }

        return true;
    }
}
