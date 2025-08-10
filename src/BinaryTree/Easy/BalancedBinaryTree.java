package BinaryTree.Easy;

import BinaryTree.TreeNode;

public class BalancedBinaryTree {

    public boolean isBalanced(TreeNode root) {
        int height = getHeight(root);
        return height != -1;
    }

    private int getHeight(TreeNode node) {
        if (node == null) return 0;

        // Also, if at any point, leftHeight or rightHeight comes outta be -1, return -1
        int leftHeight = getHeight(node.left);
        if (leftHeight == -1) return -1;

        int rightHeight = getHeight(node.right);
        if (rightHeight == -1) return -1;

        // But if their difference is greater than 1, then the tree ain't balanced
        // Return -1. Propagate this -1 upwards to short-circuit further processing.
        if (Math.abs(rightHeight- leftHeight) > 1) return -1;

        return Integer.max(leftHeight, rightHeight) + 1;
    }
}
