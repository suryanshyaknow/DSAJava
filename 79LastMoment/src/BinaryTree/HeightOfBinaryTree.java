package BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class HeightOfBinaryTree {

    public int maxDepth(TreeNode root) {
        // At any given node, the height would
        // be the max of left depth and right
        // depth.

        if (root == null) return 0;

        int lh = maxDepth(root.left);
        int rh = maxDepth(root.right);
        return 1 + Math.max(lh, rh);
    }

    public int maxDepthUsingQueue(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        int h = 0;
        while (!q.isEmpty()) {
            int levelSize = q.size();

            // Traverse the level and add
            // each node into queue
            for (int i=0; i < levelSize; i++) {
                TreeNode node = q.poll();

                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            h += 1;
        }
        return h;
    }

}
