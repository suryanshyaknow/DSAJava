package Recursion;

import java.util.ArrayList;
import java.util.List;

public class MColoring {

    boolean graphColoring(int V, int[][] edges, int m) {
        // code here
        // Prep an adjacency list first off
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        // An array to main colors for each node
        int[] color = new int[V];

        // We're tryna go over each node and try every possible color
        return solve(0, V, adjList, color, m);

        // Time complexity: O(M^V)
        // Space complexity: O(N) + O(N) for recursive call stack and color array
    }

    private boolean solve(int idx, int N, List<List<Integer>> adjList, int[] color, int m) {
        // Base case
        if (idx == N) return true; // Meaning up to this point all nodes have been colored w possible colors

        // Try each color
        for (int col = 1; col <= m; col++) {
            if (isPossible(idx, col, adjList, color)) {
                color[idx] = col;
                // Move to the next node
                boolean isSolved = solve(idx + 1, N, adjList, color, m);
                if (isSolved) return true;

                // Backtrack: Remove the color going upwards in recursive call
                color[idx] = 0;
            }
        }
        return false;
    }

    private boolean isPossible(int idx, int col, List<List<Integer>> adjList, int[] color) {
        // Iterate over the adjacent nodes
        List<Integer> adjNodes = adjList.get(idx);

        for (int i = 0; i < adjNodes.size(); i++) {
            if (color[adjNodes.get(i)] == col) return false;
        }
        return true;
    }

}
