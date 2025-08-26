package BinarySearchTree.Medium;

import BinaryTree.TreeNode;

public class LCAInBST {

    public TreeNode lowestCommonAncestorRecursive(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return root;

        // Move left if it's sure that both p and q lie on the left
        if (root.val > p.val && root.val > q.val)
            return lowestCommonAncestorRecursive(root.left, p, q);
        else if (root.val < p.val && root.val < q.val)
            return lowestCommonAncestorRecursive(root.right, p, q);

        // Either p and q lie on different sides (one left, one right).
        // Or root itself is equal to p or q.
        return root;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode curr = root;

        while (curr != null) {
            if (curr.val < p.val && curr.val < q.val)
                curr = curr.right;
            else if (curr.val > p.val && curr.val > q.val)
                curr = curr.left;
            else
                return curr;
        }
        return null;
    }

}
