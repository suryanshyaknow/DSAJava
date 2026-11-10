package Graphs;

public class RedundantConnection {

    public int[] findRedundantConnection(int[][] edges) {
        // First off, tree -> Undirected and no cycles
        // Given: Given graph was originally a tree, meaning it has no cycles.
        // Now: A new edge and a cycle.
        // Find: Redundant edge that has formed the cycle.
        //
        // Also, not given the number of nodes.
        // But we know that a tree has N nodes and N - 1 edges.
        // And since a new edge has been added to the tree, we can confirm
        // there are edges.length nodes.

        int N = edges.length;

        // Intuition: Use DSU to connect the nodes one by one vai union by rank.
        // And the moment we encounter the nodes whose root parents are the same,
        // it implies that edge is the one that introduced the cycle -- not necessarily
        // the originally added edge but still.

        // Initialize parent and rank array to implement DSU
        int[] par = new int[N + 1]; // 1-based indexed nodes
        for (int i = 1; i <= N; i++) par[i] = i;
        int[] rank = new int[N + 1]; // Initially every node starts off w rank 0.

        // Iterate voer edges and connect the nodes
        for (int[] edge : edges) {
            if (!union(edge[0], edge[1], par, rank)) return edge;
            // Meaing if two nodes are already connected directly/indirectly,
            // they form a cycle.
        }

        return new int[] { -1, -1 };
    }

    /**
     * Finds the root parent of a given node.
     * Also does the path compression.
     *
     * Path compression implies that every node w subparents and root parent
     * are connected directly w their root parents in the process.
     * So that we don't have to deal w lls but graphs.
     */
    private int find(int n, int[] par) {
        if (n == par[n]) return n;

        int curr = n;
        while (curr != par[curr]) {
            // Path compression
            par[curr] = par[par[curr]];
            curr = par[curr];
        }
        return curr;
    }

    /**
     * Returns true if the union/connection was successful.
     * False if the given nodes were already connected.
     */
    private boolean union(int n1, int n2, int[] par, int[] rank) {
        int par1 = find(n1, par);
        int par2 = find(n2, par);

        if (par1 == par2) return false; // already connected; forms a cycle

        if (rank[par1] > rank[par2]) {
            par[par2] = par1;
        } else if (rank[par2] > rank[par1]) {
            par[par1] = par2;
        } else {
            par[par2] = par1;
            rank[par1] += 1;
        }
        return true;
    }
}
