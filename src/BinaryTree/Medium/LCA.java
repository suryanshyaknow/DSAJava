package BinaryTree.Medium;

import BinaryTree.TreeNode;

public class LCA {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Process each node
        // If the flow reaches at null, return null
        // If the flow reached at one of the nodes we're looking for, return that node
        // If a node returns both the nodes we're looking at, return the node.

        if (root == null) return root;

        // If the curr node is one of the targets, return it upwards
        if (root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) return root;

        if (left != null) return left;
        else if (right != null) return right;
        else return null;

//        if (left == null) return right;
//        else if (right == null) return left;
//        else return root;
    }

}
