package BinarySearchTree;

import BinaryTree.TreeNode;

import java.util.Stack;

public class TwoSumBSTIterator {

    private static class BSTIterator {

        Stack<TreeNode> st = new Stack<>();
        boolean reverse = false;

        public BSTIterator(TreeNode root, boolean reverse) {
            this.reverse = reverse;
            pushAll(root);
        }

        private void pushAll(TreeNode root) {
            if (!reverse) {
                // Implement iterator in inorder fashion: Left Root Right'
                // i.e. push all left
                while (root != null) {
                    st.push(root);
                    root = root.left;
                }
            } else {
                // Push all rights
                while (root != null) {
                    st.push(root);
                    root = root.right;
                }
            }
        }

        private int next() {
            int res = st.peek().val;
            TreeNode node = st.pop();

            if (!reverse) {
                pushAll(node.right);
            } else {
                pushAll(node.left);
            }
            return res;
        }

    }
    public boolean findTarget(TreeNode root, int k) {
        // Intuition: Implement two BST iterators approach,
        // and then two pointers.
        if (root == null) return false;

        BSTIterator leftIterator = new BSTIterator(root, false);
        BSTIterator rightIterator = new BSTIterator(root, true);

        int left = leftIterator.next();
        int right = rightIterator.next();

        while (left < right) {
            int sum = left + right;
            if (sum == k) return true;
            else if (sum < k) left = leftIterator.next();
            else right = rightIterator.next();
        }
        return false;

        // Time complexity: O(N) because we traverse each node at most once using the two iterators
        // Space complexity: O(H) where H is the height of the BST, due to the stacks used by the two iterators.
//        For a balanced BST, this becomes O(log N).
    }
}
