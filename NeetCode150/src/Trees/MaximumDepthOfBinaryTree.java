package Trees;

import java.util.*;

public class MaximumDepthOfBinaryTree {

    public int maxDepth(TreeNode root) {
        // Intuition: At any give node, height of tree
        // is max outta of its left subtree height and
        // right subtree height.
        // So at each node we're gonna ask, what's the
        // max height and do that recursively.

        if (root == null) return 0;

        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));

        // Time: O(N)
        // Space: O(h), in worse case h = N
    }

    public int maxDepthBfs(TreeNode root) {
        if (root == null) return 0;

        // Intuition: Via BFS. We're gonna process the tree
        // level by level and increement the height by 1 each
        // time a level is processed. And keep on doing that till
        // all levels have been traversed.

        // Start by adding the root to the queue. To traverse the
        // next level, process each node in the current level and
        // check whether it has any children. If none do, there
        // is no next level.
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root); // init config
        int h = 0;
        while (!q.isEmpty()) {
            int level = q.size();
            for (int i = 0; i < level; i++) {
                TreeNode node = q.poll();
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            h += 1;
        }
        return h;
    }
}
