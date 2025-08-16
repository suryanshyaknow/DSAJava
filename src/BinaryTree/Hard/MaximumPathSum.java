package BinaryTree.Hard;

import BinaryTree.TreeNode;

public class MaximumPathSum {

    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;

        int[] res = new int[1];
        res[0] = Integer.MIN_VALUE;
        maxPathSumHelper(root, res);
        return res[0];
    }

    private int maxPathSumHelper(TreeNode root, int[] res) {
        if (root == null) return 0;

        int leftGain = Integer.max(0, maxPathSumHelper(root.left, res)); // But we don't want any negative val nodes to
        int rightGain = Integer.max(0, maxPathSumHelper(root.right, res));
        res[0] = Integer.max(root.val + leftGain + rightGain, res[0]);

        return root.val + Integer.max(leftGain, rightGain);
        // Why not?
        // return root.val + leftGain + rightGain;
        // Because we're treating the current node being processed as fork,
        // thereby no further forking is allowed.
        // Standing at root node, we just want the max (straight path) leftGain and max rightGain.
    }

}
