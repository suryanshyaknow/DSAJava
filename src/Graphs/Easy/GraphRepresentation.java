package Graphs.Easy;

import java.util.ArrayList;
import java.util.Arrays;

public class GraphRepresentation {

    // ========================== GRAPH BASICS ==========================
    // Graph = Nodes (vertices) + Edges (connections)

    // Representations:
    // 1. Adjacency List (most common, space-efficient).
    // 2. Adjacency Matrix (good for dense graphs, O(V^2) space this not appreciable).

    // Graph Types:
    // - Directed vs Undirected
    // - Weighted vs Unweighted
    // - Cyclic vs Acyclic
    // - Connected vs Disconnected

    // ========================== TRAVERSALS ============================
    // BFS (Breadth First Search):
    // - Queue based, level by level traversal.
    // - Use for shortest path in unweighted graphs.

    // DFS (Depth First Search):
    // - Stack/recursion based, goes deep first.
    // - Good for connected components, cycle detection, topo sort.

    // ========================== CORE PROBLEMS =========================
    // Connected Components:
    // - Count # of disconnected "islands" (run BFS/DFS on unvisited nodes).

    // Cycle Detection:
    // - Undirected: DFS with parent check OR Union-Find.
    // - Directed: DFS with recursion stack OR Kahn’s algorithm (BFS topo sort).

    // Bipartite Check:
    // - BFS/DFS with coloring (2 colors).
    // - If conflict in coloring → not bipartite.

    // Topological Sort (DAG only):
    // - DFS-based OR Kahn’s Algorithm (BFS + indegree).
    // - Applications: task scheduling, course prerequisites.

    // ========================== SHORTEST PATH =========================
    // BFS: Unweighted graphs → O(V+E).
    // Dijkstra: Weighted (no negative weights) → O((V+E) log V).
    // Bellman-Ford: Handles negative weights → O(V*E).
    // Floyd-Warshall: All-pairs shortest path → O(V^3).

    // ========================== DISJOINT SET (DSU/Union-Find) =========
    // - Used for cycle detection, MST (Kruskal).
    // - Optimizations: Path Compression + Union by Rank/Size.

    // ========================== MINIMUM SPANNING TREE (MST) ===========
    // Kruskal’s Algo:
    // - Sort edges, pick smallest if it doesn’t form a cycle (use DSU).
    // Prim’s Algo:
    // - Grow MST from a node, pick min edge to outside node (PQ/Heap).

    // ========================== ADVANCED CONCEPTS ====================
    // Bridges & Articulation Points (Tarjan’s Algo):
    // - Critical edges/nodes, if removed, increase disconnected components.
    // - Used in network reliability problems.

    // Strongly Connected Components (Kosaraju/Tarjan):
    // - Subgraphs where every node reachable from every other.

    // ========================== REAL-LIFE APPS ========================
    // - Social networks: connected components, BFS/DFS.
    // - Maps: shortest path (Dijkstra).
    // - Scheduling: Topological sort.
    // - Networks: MST, bridges/articulation points.

    public static void adjacencyMatrixRepresentation(String[] args) {
        // Representation via Adjacency Matrix
        int N = 3; // Nodes
        int M = 3; // Edges

        // For 1 based indexing we'll define a matrix adj[N+1][N+1]
        int[][] adj = new int[N + 1][N + 1];

        // Edge bw nodes u & v
//        adj[u][v] = 1;
//        adj[v][u] = 1; // Since it's an undirected graph

        // Edge bw nodes 1 & 2
        adj[1][2] = 1;
        adj[2][1] = 1; // Since it's an undirected graph

        // Edge bw nodes 2 & 3
        adj[2][3] = 1;
        adj[3][2] = 1; // Since it's an undirected graph

        // Edge bw nodes 1 & 3
        adj[1][3] = 1;
        adj[3][1] = 1; // Since it's an undirected graph
    }

    public static void adjacencyListRepresentation(String[] args) {
        int N = 3, M = 3;
        // Create an array list and store N+1 arrays list within it.
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            adj.add(new ArrayList<>());
        }

        // For each idx (node), we'll store its neighbouring nodes
        // Thus, for nodes u and v, store u in vth array and v in uth array
        // adj.get(u).add(v)
        // adj.get(v).add(u)

        // Note: If weights are to be taken into consideration, then we oughta store Pair(edge, weight) in the list.

    }


}
