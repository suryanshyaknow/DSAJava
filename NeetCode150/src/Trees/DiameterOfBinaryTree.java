package Trees;

public class DiameterOfBinaryTree {

    private int res = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        // Intuition: So the idea is to treat each node as
        // pivot and compute the length of path passing
        // through it. And do this for every node and keep
        // track of maximum.
        // Dia = max. left height + max. right height

        // Execution: Now compose a method that computes the
        // height of nodes and since it'll recursively compute
        // height of each node, that exact moment we have its
        // left height and right height, we can compute the
        // longest path thru that node and compare it against
        // the running max dia.

        dfs(root);
        return res;
    }

    private int dfs(TreeNode root) {
        // The method is designed to compute the height
        // of root node by recursively computing the
        // height of each sub node.
        if (root == null) return 0;

        int lh = dfs(root.left);
        int rh = dfs(root.right);

        // Now before returning the height of this node
        // we have both its lh and rh which can be used
        // to compute the max path going thru this node.

        res = Math.max(res, lh + rh);
        return 1 + Math.max(lh, rh);
    }
}
