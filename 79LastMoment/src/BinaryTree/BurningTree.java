package BinaryTree;

import java.util.*;

public class BurningTree {

    // Because burning is a distance-based, simultaneous process.
    // DFS explores depth-first and doesn’t model time correctly, while BFS naturally processes
    // nodes level by level, which maps directly to time units.

    public int minTime(TreeNode root, int target) {
        // code here

        // Intuition: We'll use bfs since the burning happens simultaneously.
        // We could burn the nodes level by level. BFS models burning and time correctly.
        // Now while performing bfs, we'll need to move in all directions, so we'll be needing
        // pointers to parents as well, just left and right ain't gonna suffice.

        // Step i. Create a hashmap to have node pointing to their pointers as well.
        // Could be simply created via level order traversal
        HashMap<TreeNode, TreeNode> hashMap = new HashMap<>();
        TreeNode targetNode = populateParentHashMapAndGetTarget(root, hashMap, target);
        if (targetNode == null) return 0;

        // Step ii. Using level order traversal burn the nodes level by level, just keep track
        // already burnt nodes using a visited data structure.
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(targetNode);
        int time = 0;

        Set<TreeNode> vis = new HashSet<>(); // Already burnt nodes
        vis.add(targetNode);

        while (!q.isEmpty()) {
            int levelSize = q.size();
            int flag = 0; // To see if any were burnt

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = q.poll();

                // Process the left branch
                if (node.left != null && !vis.contains(node.left)) {
                    q.offer(node.left);
                    vis.add(node.left);
                    flag = 1;
                }

                // Process the right branch
                if (node.right != null && !vis.contains(node.right)) {
                    q.offer(node.right);
                    vis.add(node.right);
                    flag = 1;
                }

                // Process the parent branch
                TreeNode par = hashMap.get(node);
                if (par != null && !vis.contains(par)) {
                    q.offer(par);
                    vis.add(par);
                    flag = 1;
                }
            }
            if (flag == 1) time += 1;
        }

        return time;

        // Time complexity: O(N) + O(N)
        // Space complexity: O(N) for hashMap + another O(N) for hashSet + O(N) for recursive call stack
    }

    private TreeNode populateParentHashMapAndGetTarget(TreeNode root, HashMap<TreeNode, TreeNode> hashMap, int target) {
        if (root == null) return null;

        // Use level order traversal to populate parents hashmap
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode targetNode = null;

        q.offer(root);
        while (!q.isEmpty()) {
            int levelSize = q.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = q.poll();

                if (node.val == target) targetNode = node;

                if (node.left != null) {
                    hashMap.put(node.left, node);
                    q.offer(node.left);
                }
                if (node.right != null) {
                    hashMap.put(node.right, node);
                    q.offer(node.right);
                }
            }
        }

        return targetNode;
    }

}
