package BinaryTree;

public class DiameterOfBinaryTree {

    private int maxLen = 0; // dia; longest path bw any two nodes

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;

        computeHeight(root);
        return maxLen;
    }

    // private void dfs(TreeNode root) {
    //     if (root == null) return;

    //     // Compute the dia passing thru each node
    //     // Preorder, since root is being processed first off
    //     int lHeight = computeHeight(root.left);
    //     int rHeight = computeHeight(root.right);
    //     int currDia = lHeight + rHeight;
    //     maxLen = Math.max(currDia, maxLen);

    //     dfs(root.left);
    //     dfs(root.right);
    // }

    private int computeHeight(TreeNode root) {
        if (root == null) return 0;

        int leftHeight = computeHeight(root.left);
        int rightHeight = computeHeight(root.right);

        // Now for this node being processed, we've its left
        // height as well as right height, it's dia could be computed
        maxLen = Math.max(maxLen, leftHeight + rightHeight);

        return 1 + Math.max(leftHeight, rightHeight);
    }

}
