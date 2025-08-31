package BinarySearchTree.Medium;

import BinaryTree.TreeNode;

import java.util.Stack;

public class TwoSumBSTIterator {

    private static class BSTIterator {
        private Stack<TreeNode> st = new Stack<>();
        boolean reverse = false;

        public BSTIterator(TreeNode root, boolean reverse) {
            this.reverse = reverse;
            // Push all left/right nodes based on reverse
            pushAll(root);
        }

        public int next() {
            TreeNode nextNode = st.pop();
            if (!reverse) {
                if (nextNode.right != null)
                    pushAll(nextNode.right);
            } else {
                if (nextNode.left != null)
                    pushAll(nextNode.left);
            }
            return nextNode.val;
        }

        public boolean hasNext() {
            return !st.isEmpty();
        }

        private void pushAll(TreeNode root) {
            TreeNode curr = root;
            // Push all lefts/rights
            while (curr != null) {
                st.push(curr);
                curr = reverse ? curr.right : curr.left;
            }
        }
    }

    public boolean findTarget(TreeNode root, int k) {
        if (root == null) return false;
        BSTIterator leftIterator = new BSTIterator(root, false);
        BSTIterator rightIterator = new BSTIterator(root, true);

        Integer left = leftIterator.next();
        Integer right = rightIterator.next();

        while (left < right) {
            if (left + right == k) return true;
            if (left + right < k) left = leftIterator.next();
            else right = rightIterator.next();
        }

        return false;
    }
}
