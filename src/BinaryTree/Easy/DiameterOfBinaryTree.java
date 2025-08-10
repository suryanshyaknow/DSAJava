package BinaryTree.Easy;

import BinaryTree.TreeNode;

public class DiameterOfBinaryTree {

    int dia = 0;

    public int diameterOfBinaryTreeBruteForce(TreeNode root) {
        // The idea is pretty simple.
        // Treat each node as curve and fine the left height and right height,
        // and their sum shall give the path, given the node being the curvature point.
        // And the max path shall be our diameter.
        if (root == null) return 0;

        getHeight(root);

        // Approach 2: Without static var dia
        int[] res = new int[1];
        getHeight(root, res);
        return res[0];
    }

    private int getHeight(TreeNode node) {
        if (node == null) return 0;
        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        dia = Integer.max(dia, leftHeight + rightHeight);
        return 1 + Integer.max(leftHeight, rightHeight);
    }

    private int getHeight(TreeNode node, int[] res) {
        if (node == null) return 0;
        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        res[0] = Integer.max(dia, leftHeight + rightHeight);
        return 1 + Integer.max(leftHeight, rightHeight);
    }

}
