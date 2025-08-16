package BinaryTree.Hard;

import BinaryTree.TreeNode;

public class FlattenBTToLinkedList {

    public void flatten(TreeNode root) {
        if (root == null) return;
        dfs(root);
    }

    private TreeNode dfs(TreeNode root) {
        // We want this function to return the tail of the tree,
        // so that the asked linkage could be done.
        if (root == null) return null;

        TreeNode leftTail = dfs(root.left);
        TreeNode rightTail = dfs(root.right);

        if (leftTail != null) {
            leftTail.right = root.right; // tail of left → right subtree
            root.right = root.left; // root → left subtree
            root.left = null;
        }

        if (rightTail != null) return rightTail;
        else if (leftTail != null) return leftTail;
        else return root;
    }

}
