package BinaryTree;

public class LCA {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Intuition: The moment you see one of p or q, you return that node.
        // This algo assumes both nodes exist in the tree.
        // ANd if none of the node from left and right is null, you return the
        // current node. Because that's LCA of both of 'em.

        if (root == null)
            return null; // Done passed the leaf node
        if (root == p || root == q)
            return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // If none of them is null, that means current node is lca
        if (left != null && right != null)
            return root;
        //      Otherwise, return the non-null node
        return left == null ? right : left;
    }

}
