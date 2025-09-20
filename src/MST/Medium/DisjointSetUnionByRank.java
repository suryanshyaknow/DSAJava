package MST.Medium;

import java.util.ArrayList;
import java.util.List;

public class DisjointSetUnionByRank {

    List<Integer> rank = new ArrayList<>();
    List<Integer> parent = new ArrayList<>();

    public DisjointSetUnionByRank(int N) {
        for (int i = 0; i < N; i++) {
            rank.add(i, 0);
            parent.add(i, i); // Initially, every node is parent of its own.
        }
    }

    public int findUPar(int node) {
        if (node == parent.get(node)) return node;

//        return findUPar(parent.get(node));
        // This woulda been logarithmic, but we also wanna do path compression for future calls to run in constant time.

        // With path compression:
        int uPar = findUPar(parent.get(node));
        parent.set(node, uPar);
        return uPar;
    }

    public void unionByRank(int u, int v) {
        // Step 1. Find the ultimate parents of both nodes
        // Step 2. Attach tree w smaller rank under the root of tree w larger rank
        // Step 3. If ranks are equal, attach one under the other and increase the rank of new uPar w +1.
        int uUPar = findUPar(u);
        int vUPar = findUPar(v);
        if (uUPar == vUPar) return; // both nodes already belongs to the same component

        int uRank = rank.get(uUPar);
        int vRank = rank.get(vUPar);
        if (uRank < vRank)
            parent.set(uUPar, vUPar); // Now, vUPar is the ultimate parent of even uUPar
        else if (uRank > vRank)
            parent.set(vUPar, uUPar);
        else {
            // Attach either one to another and increase the rank of new uPar
            parent.set(uUPar, vUPar);
            rank.set(vUPar, rank.get(vUPar) + 1);
        }
    }
}
