package BinaryTree;

public class MaximumPathSum {

    private int maxPathSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;

        // Intuition: Pretty simple. Traverse thru each node
        // computing the max of left and right gain. But while
        // backtracking updates the max path sum for the node
        // being treated as pivot at a recursive call.

        // Do a post-order traversal.
        // Each node returns the maximum gain of a single path going upward.
        // While backtracking, treat the current node as a pivot and
        // update the global maximum path sum using both left and right gains.
        // Negative gains are ignored since a path is optional.
        dfs(root);
        return maxPathSum;
    }

    private int dfs(TreeNode root) {
        if (root == null) return 0;

        int leftGain = Math.max(0, dfs(root.left));
        int rightGain = Math.max(0, dfs(root.right));
        maxPathSum = Math.max(maxPathSum, root.val + leftGain + rightGain);

        return root.val + Math.max(leftGain, rightGain);
    }

}
