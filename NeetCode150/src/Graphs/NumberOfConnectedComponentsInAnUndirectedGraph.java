package Graphs;

import java.util.*;

public class NumberOfConnectedComponentsInAnUndirectedGraph {

    int countConnected(int V, ArrayList<ArrayList<Integer>> edges) {
        // code here
        // DSU: Disjoint Set Union / Union Find ~ Useful for dynamic graphs.
        // Data Structure used to efficiently TRACK & MERGE connected components of elements (nodes/vertices).
        // Two fundamental ops -- findPar(node) & union(u, v) ~ connects the edges bw two nodes

        // UNION(U, V):
        // It takes two nodes as params and connects them. And accordingly update the parent
        // of each node based on the ranks

        // FIND(NODE):
        // What it does is it gets the root parent of the given node and in the process
        // compresses the path to the root node as well. Meaning of all the nodes having
        // subparents, it turn their parents to root parent if applicable.

        // Need to declare parent and rank array
        // Intitally everyone is parent of itself
        int[] par = new int[V];
        for (int i = 0; i < V; i++)
            par[i] = i;

        // Intitally, everyone's rank is zero
        int[] rank = new int[V]; // Represents an estimate of the height of the tree
        int cnt = V; // Initially ever node counts as separate comp

        // Now iterate over edges and union them
        // Keep count of connected components
        for (int i = 0; i < edges.size(); i++) {
            int u = edges.get(i).get(0);
            int v = edges.get(i).get(1);

            if (union(u, v, par, rank))
                cnt -= 1;
        }

        return cnt;

        // Time: O(alpha(4)) where alpha is close to 1
    }

    private int findPar(int node, int[] par) {
        // Need to return the root parent of the node
        int res = node; // Simply return node if node is parent of itself

        // Go deep into the chain till a node is a parent of itself -- root node
        while (res != par[res]) {
            // Path compression: So as not to let the nodes accumulate as linked list we connect
            // each node to its root parent instead of its immediate parent.
            par[res] = par[par[res]];

            res = par[res];
        }
        return res;
    }

    private boolean union(int n1, int n2, int[] par, int[] rank) {
        // First off, find root parents of each
        // If they're the same, simply return
        int p1 = findPar(n1, par);
        int p2 = findPar(n2, par);
        if (p1 == p2)
            return false;

        // Otherwise, connect them
        // NOTE: Ranks don't change till ranks of given nodes are equal, meaning they're contenders
        if (rank[p1] > rank[p2]) {
            // Implies n2 shall be connected as children of n1
            // Update parents
            par[p2] = p1;
        } else if (rank[p2] > rank[p1]) {
            // Implies n1 shall be connected as children of n2
            // Update parents
            par[p1] = p2;
        } else {
            par[p2] = p1;
            rank[p1]++;
        }
        return true;
    }
}
