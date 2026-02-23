package BinarySearchTree;

import BinaryTree.TreeNode;

public class LargestBSTInBT {

    // A DS to track the min and max range for a node, and the size.
    private static class NodeInfo {
        boolean isBST;
        int size;
        int min;
        int max;

        public NodeInfo(boolean isBST, int size, int min, int max) {
            this.isBST = isBST;
            this.size = size;
            this.min = min;
            this.max = max;
        }
    }

    // Return the size of the largest sub-tree which is also a BST
    static int largestBst(TreeNode root) {
        // Write your code here
        // Intuition: For a BT to be valid BST, it's gotta be
        // greater than the largest from the left subtree, and
        // smaller than the smallest from the right subtree.

        // We're gonna use post order traversal cuz we're starting
        // from the bottom and building the tree up and validating.
        return largestBSTHelper(root).size;

        // Time complexity: O(N) for postorder traversal
        // Space complexity: O(N) for recursive call stack
    }

    private static NodeInfo largestBSTHelper(TreeNode root) {
        if (root == null) return new NodeInfo(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);

        NodeInfo left = largestBSTHelper(root.left);
        NodeInfo right = largestBSTHelper(root.right);

        // BST condition
        if (left.isBST && right.isBST && (root.val > left.max) && (root.val < right.min)) {
            int size = 1 + left.size + right.size;
            // If this subtree is a BST, the smallest value must be:
            // - Either somewhere in the left subtree
            // - Or the current node itself (if left is empty)
            int min = Math.min(root.val, left.min);
            int max = Math.max(root.val, right.max);

            return new NodeInfo(true, size, min, max);
        }

        // Otherwise not a BST
        return new NodeInfo(false, Math.max(left.size, right.size), -1, -1);

    }
}
