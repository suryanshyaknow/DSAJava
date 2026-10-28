package Trees;

public class BinaryTreeMaximumPathSum {

    private int res;

    public int maxPathSum(TreeNode root) {
        if (root == null)
            return 0;

        // Intuition: The idea is to sum the nodes
        // up from bottom-up and pass the max branch
        // sum upwards the recursion. But at the same
        // time mind that the curr branch itself could
        // be the max sum so keep updating the sum of
        // children and root node as well.
        res = Integer.MIN_VALUE;
        dfs(root);
        return res;
    }

    private int dfs(TreeNode root) {
        if (root == null) return 0;

        int ls = Math.max(0, dfs(root.left));
        int rs = Math.max(0, dfs(root.right));

        int cs = ls + rs + root.val; // candidate sum including root's; including root is must
        res = Math.max(res, cs);

        return root.val + Math.max(ls, rs);
    }
}
