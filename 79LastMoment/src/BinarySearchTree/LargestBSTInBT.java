package BinarySearchTree;

import BinaryTree.TreeNode;

public class LargestBSTInBT {

    // A DS to track the min and max range for a node, and the size.
    private static class NodeVal {
        int min;
        int max;
        int size;

        NodeVal(int max, int min, int size) {
            this.max = max;
            this.min = min;
            this.size = size;
        }
    }

    // Return the size of the largest sub-tree which is also a BST
    static int largestBst(TreeNode root) {
        // Write your code here
        // Intuition: For a BT to be valid BST, it's gotta be
        // greater than the largest from the left subtree, and
        // smaller than the smallest from the right subtree.

        // We're gonna use post order traversal cuz we're starting
        // from the bottom and building the tree up and validating.
        return largestBstHelper(root).size;

        // Time complexity: O(N) for postorder traversal
        // Space complexity: O(N) for recursive call stack
    }

    static NodeVal largestBstHelper(TreeNode root) {
        // An empty tree is a BST of size 0
        // Passing max as INT.MIN so that subtrees further up could be validated
        if (root == null) return new NodeVal(Integer.MIN_VALUE, Integer.MAX_VALUE, 0);

        NodeVal left = largestBstHelper(root.left);
        NodeVal right = largestBstHelper(root.right);

        // If we found a valid BST
        if (root.val > left.max && root.val < right.min) {
            return new NodeVal(
                    Math.max(root.val, right.max), Math.min(root.val, left.min), 1 + left.size + right.size);
        }
        // Otherwise, return such values in max and min it won't let any subtree further
        // up get validated
        return new NodeVal(Integer.MAX_VALUE, Integer.MIN_VALUE, Math.max(left.size, right.size));

    }
}
