package Graphs;

public class NumberOfProvinces {

    public int findCircleNum(int[][] isConnected) {
        // I'll do this w Disjoint Set Union
        // Disjoint set is a data structure used to efficiently
        // TRACK and MERGE connected components of elements (nodes/vertices).
        // It's got two fundamental ops: find(n), union(n1, n2)

        int N = isConnected.length;

        // In disjoint set we have two arrays
        // parent array -- tracks the parent of each node
        // rank array -- estimate of size of each node

        // Initially, every node is a parent of itself
        int[] par = new int[N];
        for (int i = 0; i < N; i++) par[i] = i;

        // Rank array
        // Initially, every node's rank is 0 since none
        // of them are connected.
        int[] rank = new int[N];

        // Initially since none of the nodes is connected, we
        // start off w that many number of disconnected comps.
        int cnt = N;

        // Iterate over the edges given and union the nodes
        // here, adj matrix
        // Everytime a union bw two nodes is successful, we decrement
        // the numer of disconnected comps
        for (int i = 0; i < N; i++) { // nodes
            for (int j = i + 1; j < N; j++) { // nodes 'connections
                // NOTE: j = i + 1 because in undirected graph matrix is symmetrical
                // if isConnected(i, j) == 1
                // Implies there's an edge bw i and j
                if (i == j) continue; // Node is indeed a parent of itself

                if (isConnected[i][j] == 1 && union(i, j, par, rank)) cnt -= 1;
            }
        }

        return cnt;

        // Time: O(N ^ 2 . alpha(N)) ~ amortized
        // Space: O(N)
    }

    /**
     * Gets the root parent of the given node.
     * Also does the path compression while it's at it.
     * <p>
     * Path compression: It directly connects the nodes to the root nodes
     * of their parent, rpeventing the graph to take a linkedlist like form.
     */
    private int find(int n, int[] par) {
        // If n is a parent of itself
        if (par[n] == n) return n;

        // If not, iterate till you find a node that's parent
        // of itself, i.e., root parent.
        int curr = n;
        while (curr != par[curr]) {
            par[curr] = par[par[curr]]; // Path compression
            curr = par[curr];
        }
        return curr;
    }

    /**
     * Connects the given node n1 and n2 based on their ranks.
     * If their ranks are the same, then the rank of the parent
     * node increases.
     * If not, the node w smaller rank gets attached to the one w
     * the larger rank. And none of their ranks change.
     */
    private boolean union(int n1, int n2, int[] par, int[] rank) {
        // First off, get their parent
        int par1 = find(n1, par);
        int par2 = find(n2, par);

        // If they're same, that means they're both connected
        if (par1 == par2) return false;

        // Connect them comparing their ranks -- Union by Rank
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
