package BinaryTree.Easy;

import BinaryTree.TreeNode;

public class SymmetricBinaryTree {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;

        return isSameTree(root.left, root.right);
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q== null) return true;
        if (p == null || q ==  null) return false;

        if (p.val != q.val) return false;

        boolean isLeftSame = isSameTree(p.left, q.right);
        boolean isRightSame = isSameTree(p.right, q.left);
        return isLeftSame && isRightSame;
    }

}
