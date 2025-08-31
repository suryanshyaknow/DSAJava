package BinarySearchTree.Hard;

import BinaryTree.TreeNode;

public class MaxBSTInBt {

    static class NodeVal {

        private int maxNode;
        private int minNode;
        private int size;

        NodeVal(int size, int maxNode, int minNode) {
            this.size = size;
            this.maxNode = maxNode;
            this.minNode = minNode;
        }
    }

    public int largestBst(TreeNode root) {
        // Write your code here
        // The idea is pretty simple: A BT is tree given root's val is greater than the largest val from the left,
        // and smallest than the smallest from the right.

        return largestBstHelper(root).size;
    }

    private static NodeVal largestBstHelper(TreeNode root) {
        // An empty tree is a BST of size 0
        if (root == null) return new NodeVal(0, Integer.MIN_VALUE, Integer.MAX_VALUE);

        // Since we wanna process the tree from the bottom: First Left, then Right, and at last
        // deal with the Root, so we're gonna go by the Postorder.
        NodeVal left = largestBstHelper(root.left);
        NodeVal right = largestBstHelper(root.right);

        if (root.val > left.maxNode && root.val < right.minNode)
            return new NodeVal(left.size + right.size + 1, Integer.max(right.maxNode, root.val), Integer.min(left.minNode, root.val));
        else
            return new NodeVal(Integer.max(left.size, right.size), Integer.MAX_VALUE, Integer.MIN_VALUE);

    }

}
