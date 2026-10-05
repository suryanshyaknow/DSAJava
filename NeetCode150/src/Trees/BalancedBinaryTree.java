package Trees;

public class BalancedBinaryTree {

    public boolean isBalanced(TreeNode root) {

        // Intuition: Same drill, we're gonna start from
        // leaf nodes and ask for each node if it's height
        // balanced. And if it ain't we're gonna propagate that
        // upwards the recursion to simple return false w/o
        // comparing heights of left and right subtrees anymore.

        return dfs(root) != -1;
    }

    private int dfs(TreeNode root) {
        if (root ==  null) return 0;

        // If the tree ain't balanced return -1 to
        // convey the same, otherwise simply return the
        // height of the tree

        int lh = dfs(root.left);
        if (lh == -1) return -1;

        int rh = dfs(root.right);
        if (rh == -1) return -1;

        // See, if the left and right heights differ by not
        // more than one
        int diff = Math.abs(lh - rh);
        if (diff > 1) return -1;

        return 1 + Math.max(lh, rh);
    }

    private static class BalanceInfo {
        private int height;
        private boolean isBalanced;

        public BalanceInfo(int height, boolean isBalanced) {
            this.height = height;
            this.isBalanced = isBalanced;
        }
    }

    public boolean isBalanced2(TreeNode root) {
        // Intuition: Same drill, we're gonna start from
        // leaf nodes and ask for each node if it's height
        // balanced. And if it ain't we're gonna propagate that
        // upwards the recursion to simple return false w/o
        // comparing heights of left and right subtrees anymore.

        return dfs2(root).isBalanced;
    }

    private BalanceInfo dfs2(TreeNode root) {
        if (root == null) return new BalanceInfo(0, true);

        BalanceInfo lbi = dfs2(root.left);
        if (!lbi.isBalanced) return new BalanceInfo(0, false);

        BalanceInfo rbi = dfs2(root.right);
        if (!rbi.isBalanced) return new BalanceInfo(0, false);

        int diff = Math.abs(lbi.height - rbi.height);
        if (diff > 1) return new BalanceInfo(0, false);

        return new BalanceInfo(1 + Math.max(lbi.height, rbi.height), true);
    }
}
