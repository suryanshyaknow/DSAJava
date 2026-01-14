package BinarySearchTree;

import BinaryTree.TreeNode;

public class LCAOfBST {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return root;

        // Both p and q lie on the left; move left
        // Both p and q lie on the right; move right
        // p and q have diverged; root node is lca,
        // or root is one of them simply.

        if (root.val > p.val && root.val > q.val)
            return lowestCommonAncestor(root.left, p, q);
        else if (root.val < p.val && root.val < q.val)
            return lowestCommonAncestor(root.right, p, q);
        return root;
    }

}
