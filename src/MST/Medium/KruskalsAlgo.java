package MST.Medium;

import java.util.Arrays;

public class KruskalsAlgo {

    static int kruskalsMST(int V, int[][] edges) {
        // code here
        // Sort the edges in ascending order based on edges wt
        Arrays.sort(edges, (a, b) -> a[2] - b[2]);
        int mstSum = 0;

        // Iterate over the sorted edges
        DisjointSetUnionBySize disjointSet = new DisjointSetUnionBySize(V);
        for (int edge[] : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            // Check if they belong to the same uPar
            // If not, union 'em and add to mst
            // If yes, skip
            if (disjointSet.findUPar(u) != disjointSet.findUPar(v)) {
                disjointSet.unionBySize(u, v);
                mstSum += w;
            }
        }
        return mstSum;
    }

}
