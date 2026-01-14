package BinarySearchTree;

import BinaryTree.TreeNode;

import java.util.Stack;

public class BSTIterator {

    Stack<TreeNode> st = new Stack<>();

    public BSTIterator(TreeNode root) {
        // So, we'll maintain a stack, and start off w
        // an initial config of pushing the root and all
        // its lefts in the stack.
        // We're basically inserting the inorder traversal into the stack.
        // And at every next call, the top of the stack will
        // be the next. And we'll pop it off and return plus
        // insert its right's left if its exists.

        pushAllLeft(root);
    }

    private void pushAllLeft(TreeNode root) {
        if (root == null) return;

        TreeNode curr = root;
        while (curr != null) {
            st.add(curr);
            curr = curr.left;
        }
    }

    public int next() {
        // Return the top
        int res = st.peek().val;
        TreeNode node = st.pop();
        pushAllLeft(node.right);
        return res;
    }

    public boolean hasNext() {
        return !st.isEmpty();
    }

}
