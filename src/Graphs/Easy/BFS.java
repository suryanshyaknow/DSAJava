package Graphs.Easy;

import java.util.*;

public class BFS {

    public ArrayList<Integer> bfs(ArrayList<ArrayList<Integer>> adj) {
        // Perform BFS traversal starting from node 0

        int N = adj.size();
        int[] vis = new int[N];  // visited array to track visited nodes
        Queue<Integer> q = new LinkedList<>();  // queue for BFS
        q.offer(0);  // start BFS from node 0
        vis[0] = 1;  // mark starting node as visited

        ArrayList<Integer> res = new ArrayList<>();  // result list to store BFS order

        while (!q.isEmpty()) {
            Integer curr = q.poll();  // dequeue current node
            res.add(curr);  // add it to result

            // process all neighbours of current node
            for (Integer i : adj.get(curr)) {
                if (vis[i] != 1) {  // if neighbour not visited
                    q.offer(i);     // enqueue neighbour
                    vis[i] = 1;     // mark neighbour as visited
                }
            }
        }
        return res;  // return BFS traversal order
    }


}
