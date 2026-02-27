package Graphs.Easy;

import java.util.ArrayList;

public class DFS {

    public ArrayList<Integer> dfs(ArrayList<ArrayList<Integer>> adj) {
        // Code here
        ArrayList<Integer> res = new ArrayList<>();
        int[] vis = new int[adj.size()];

        int startingNode = 0;
        dfsHelper(startingNode, res, vis, adj);
        return res;

        // Space complexity: O(V) for visited array + O(V) for recursion stack space.

        // Time complexity: O(V + 2E), i.e. the traversal + indegrees of the nodes being traversed, i.e., 2E
        // i. You visit each vertex once → that’s your O(V)
        // ii. For each vertex, you iterate over its adjacency list.
        //  - If the neighbor is unvisited → recurse.
        //  - If the neighbor is visited → skip.
        // That loop is the “edges” cost.
    }

    private void dfsHelper(int node, ArrayList<Integer> res, int[] vis, ArrayList<ArrayList<Integer>> adj) {
        // Mark the current node as visited
        vis[node] = 1;
        res.add(node);

        // Fetch neighbours and go in depth
        for (Integer n : adj.get(node)) {
            if (vis[n] != 1)
                dfsHelper(n, res, vis, adj);
        }
    }


}
