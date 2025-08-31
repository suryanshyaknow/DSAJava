package BinarySearchTree.Medium;

import BinaryTree.TreeNode;

import java.util.Stack;

public class BSTIterator {

    private Stack<TreeNode> st = new Stack<>();

    public BSTIterator(TreeNode root) {
        pushAllLeft(root);
    }

    private void pushAllLeft(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            st.push(curr);
            curr = curr.left;
        }
    }

    public int next() {
        TreeNode next = st.pop();
        // But but..
        // Check if its right exists and push it
        // and the lefts that come later
        if (next.right != null)
            pushAllLeft(next.right);
        return next.val;
    }

    public boolean hasNext() {
        return !st.isEmpty();
    }

}
