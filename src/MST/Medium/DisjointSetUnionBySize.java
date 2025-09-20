package MST.Medium;

import java.util.ArrayList;
import java.util.List;

public class DisjointSetUnionBySize {

    List<Integer> size = new ArrayList<>();
    List<Integer> parent = new ArrayList<>();

    public DisjointSetUnionBySize(int N) {
        for (int i = 0; i < N; i++) {
            size.add(i, 1); // Initially, everyone's size is 1
            parent.add(i, i); // Initially, every node is parent of its own.
        }
    }

    public int findUPar(int node) {
        if (node == parent.get(node)) return node;

        // Path compression:
        int uPar = findUPar(parent.get(node));
        parent.set(node, uPar);
        return uPar;
    }

    public void unionBySize(int u, int v) {
        int uUPar = findUPar(u);
        int vUPar = findUPar(v);
        if (uUPar == vUPar) return; // both nodes already belongs to the same component

        int uSize = size.get(uUPar);
        int vSize = size.get(vUPar);
        if (uSize < vSize) {
            parent.set(uUPar, vUPar); // Now, vUPar is the ultimate parent of even uUPar
            size.set(vUPar, size.get(vUPar) + 1);
        } else {
            // Attach either one to another and increase the size of new uPar
            parent.set(vUPar, uUPar);
            size.set(uUPar, size.get(uUPar) + 1);
        }
    }

}
