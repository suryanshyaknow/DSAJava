package BinarySearchTree;

import BinaryTree.TreeNode;

public class DeleteNode {

    public TreeNode deleteNode(TreeNode root, int key) {
        // Intuition: We'll traverse through the BT and
        // and figure out the node to be deleted, and redo
        // the linkages in such a way that the right subtree
        // would connect to the rightmost child of the left
        // subtree of the node to be deleted.

        if (root == null)
            return null;
        // If root itself is to be deleted
        if (root.val == key)
            return redoLinkages(root);

        // Otherwise traverse thru the BT
        TreeNode curr = root;
        while (curr != null) {
            // Traverse left or right via the property of BST
            if (curr.val > key) {
                if (curr.left != null && curr.left.val == key) {
                    curr.left = redoLinkages(curr.left);
                } else {
                    curr = curr.left;
                }
            } else {
                if (curr.right != null && curr.right.val == key) {
                    curr.right = redoLinkages(curr.right);
                } else {
                    curr = curr.right;
                }
            }
        }
        return root;

        // Time complexity:L Time complexity is O(h), which is O(log n) for a balanced BST and O(n) in the worst case.
    }

    private static TreeNode redoLinkages(TreeNode root) {
        // left subtree doesn't exist
        if (root.left == null)
            return root.right;
            // right subtree doesn't exist
        else if (root.right == null)
            return root.left;
        else {
            TreeNode rightSubtree = root.right;
            // We'll connect the right subtree to the rightmost
            // child of the left subtree
            // find the rightmost child of the left subtree
            TreeNode rightmostChild = findRightmostNode(root.left);
            rightmostChild.right = rightSubtree;
            return root.left;
        }
    }

    private static TreeNode findRightmostNode(TreeNode root) {
        while (root != null && root.right != null) root = root.right;
        return root;
    }
}
