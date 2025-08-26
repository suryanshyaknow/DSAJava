package BinarySearchTree.Medium;

import BinaryTree.TreeNode;

public class ValidateBST {

    public boolean isValidBST(TreeNode root) {
        // The idea is to pretty simple.
        // We've gotta maintain a range for every node as per the BST property

        return isValidBSTHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public static boolean isValidBSTHelper(TreeNode root, long minVal, long maxVal) {
        if (root == null) return true;

        if (root.val >= maxVal || root.val <= minVal) return false;

        boolean leftEnd = isValidBSTHelper(root.left, minVal, root.val);
        boolean rightEnd = isValidBSTHelper(root.right, root.val, maxVal);
        return leftEnd && rightEnd;
    }
}
