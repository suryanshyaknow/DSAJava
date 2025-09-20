package MST.Medium;

import java.util.ArrayList;
import java.util.List;

public class DistinctIslandsII {

    public List<Integer> numOfIslands(int rows, int cols, int[][] operators) {
        // Your code here
        int N = rows;
        int M = cols;
        int vis[][] = new int[N][M];

        int O = operators.length;
        List<Integer> res = new ArrayList<>();
        int cnt = 0;

        DisjointSetUnionBySize disjointSet = new DisjointSetUnionBySize(N * M);
        for (int i = 0; i < O; i++) {
            int row = operators[i][0];
            int col = operators[i][1];
            // If the node has already been visited then skip and record the count
            if (vis[row][col] == 1) {
                res.add(cnt);
                continue;
            }
            // If not, mark it as visited
            vis[row][col] = 1;
            cnt++;

            // Process the neighbors
            int dirs[][] = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            for (int dir[] : dirs) {
                int nr = row + dir[0];
                int nc = col + dir[1];

                if (isValidNei(nr, nc, N, M) && vis[nr][nc] == 1) { // Expanding the islands
                    int nodeIdx = row * M + col;
                    int neiIdx = nr * M + nc;
                    // First off, see if they're already connected i.e. having same uPar
                    if (disjointSet.findUPar(nodeIdx) == disjointSet.findUPar(neiIdx)) {
                        continue;
                    } else {
                        // Merge and fix the overcount
                        cnt--;
                        disjointSet.unionBySize(nodeIdx, neiIdx);
                    }
                }
            }
            res.add(cnt);
        }
        return res;
    }

    private boolean isValidNei(int nr, int nc, int n, int m) {
        return nr >= 0 && nr < n && nc >= 0 && nc < m;
    }
}
