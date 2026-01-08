package BinaryTree;

public class MaximumProductOfSplittedBinaryTree {

    public int maxProduct(TreeNode root) {
        if (root == null) return 0;

        // Compute the sum of all nodes
        long S = nodesSum(root); // O(N)

        int MOD = 1_000_000_007;
        long maxProd[] = new long[1];
        maxProd[0] = 0;

        getSubtreeSum(root, maxProd, S); // O(N)
        return (int) (maxProd[0]%MOD);

        // Time complexity: O(2N)
        // Space complexity: O(H) recursive call stack, where H being the height of the tree
    }

    private static long getSubtreeSum(TreeNode root, long[] maxProd, long S) {
        if (root == null) return 0;

        // We're using post order traversal because to compute
        // a subtree's sum, we need to process its children
        // before its node

        long lSum = getSubtreeSum(root.left, maxProd, S);
        long rSum = getSubtreeSum(root.right, maxProd, S);

        long subtreeSum = root.val + lSum + rSum;
        long remSum = S - subtreeSum;

        // Update the prod
        maxProd[0] = Math.max(maxProd[0], subtreeSum * remSum);

        return subtreeSum;
    }

    private static long nodesSum(TreeNode root) {
        if (root == null) return 0;

        long lSum = nodesSum(root.left);
        long rSum = nodesSum(root.right);
        return root.val + lSum + rSum;
    }

}
