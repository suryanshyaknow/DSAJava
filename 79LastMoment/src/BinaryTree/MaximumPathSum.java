package BinaryTree;

public class MaximumPathSum {

    int maxPathSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        // Traverse each node and treat it as
        // curvature of the path, and compute the
        // left path sum and right path sum and add the
        // node's own val.

        if (root == null) return 0;
        computeHeightSum(root);
        return maxPathSum;
    }

//    private void dfs(TreeNode root) {
//        if (root == null) return;
//
//        int lHeightSum = Math.max(0, computeHeightSum(root.left)); // cuz node's val could be negative as well and it's not clamped in computeHeightSum method
//        int rHeightSum = Math.max(0, computeHeightSum(root.right));
//        int pathSum = root.val + lHeightSum + rHeightSum;
//        maxPathSum = Math.max(maxPathSum, pathSum);
//
//        dfs(root.left);
//        dfs(root.right);
//    }

    private int computeHeightSum(TreeNode root) {
        if (root == null) return 0;

        // We don't want any negative contributions
        int lhSum = Math.max(0, computeHeightSum(root.left));
        int rhSum = Math.max(0, computeHeightSum(root.right));

        // Now think about it, to compute path sum we need to have lhSum and rhSum, so
        // we don't really need to compute it dedicately for each node, we'd leverage
        // recursion, i.e., the nodes being processed first in the recursion
        maxPathSum = Math.max(maxPathSum, root.val + lhSum + rhSum);

        return root.val + Math.max(lhSum, rhSum);
    }

}
