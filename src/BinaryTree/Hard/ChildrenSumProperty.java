package BinaryTree.Hard;

import BinaryTree.TreeNode;

public class ChildrenSumProperty {

    public boolean isSumProperty(TreeNode root) {
        // Base case: Null or leaf node automatically satisfies CSP
        if (root == null || (root.left == null && root.right == null)) return true;

        int leftVal = root.left != null ? root.left.val : 0;
        int rightVal = root.right != null ? root.right.val : 0;

        if (leftVal + rightVal != root.val) return false;

        return isSumProperty(root.left) && isSumProperty(root.right);
    }

    public void changeTree(TreeNode root) {
        if (root == null) return;

        int childSum = 0;
        if (root.left != null) childSum += root.left.val;
        if (root.right != null) childSum += root.right.val;

        if (childSum < root.val) { // If child sum falls short, assign 'em the root's val
            if (root.left != null) root.left.val = root.val;
            if (root.right != null) root.right.val = root.val;
        } else {
            root.val = childSum;
        }

        changeTree(root.left);
        changeTree(root.right);

        // Pull up
        int total = 0;
        if (root.left != null) total += root.left.val;
        if (root.right != null) total += root.right.val;
        // Shouldn't be a leaf node
        if (root.left != null || root.right != null) root.val = total;
    }


}
