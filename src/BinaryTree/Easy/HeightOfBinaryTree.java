package BinaryTree.Easy;

import BinaryTree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class HeightOfBinaryTree {

    public int maxDepth(TreeNode root) {
        // Idea: At a given node, height = 1 + max(leftHeight, rightHeight)
        // Intuition:
        // - At each node: "Yo, how deep is my left? How deep is my right?"
        // - Take the max of the two, and add 1 for the current node.

        if (root == null) return 0;

        int l = maxDepth(root.left);
        int r = maxDepth(root.right);
        return 1 + Integer.max(l, r);

        // Time complexity: O(N); each node gotta be traversed
        // Space complexity: O(N); Worst case: Tree is skewed.
    }

    public int maxDepthIterative(TreeNode root) {
        // Via level-order traversal
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int height = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size(); // Nodes in the current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();

                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
            }
            height += 1;
        }
        return height;
    }

}
