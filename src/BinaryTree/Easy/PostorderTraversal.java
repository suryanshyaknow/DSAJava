package BinaryTree.Easy;

import BinaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PostorderTraversal {

    public List<Integer> postorderTraversalIterative(TreeNode root) {
        // Idea is to maintain two stacks. Initialize stack 1 w root node.
        // From stack1, one at a time, pop the node and put it into stack2,
        // but at the same time check for its left and right and put 'em into stack1.
        // Keep on doing that till stack1 gets empty.
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> st1 = new Stack<>();
        Stack<TreeNode> st2 = new Stack<>();
        if (root == null) return res;

        st1.push(root);

        while (!st1.isEmpty()) {
            TreeNode node = st1.pop();
            st2.push(node);

            if (node.left != null)
                st1.push(node.left);

            if (node.right != null)
                st1.push(node.right);
        }

        while (!st2.isEmpty())
            res.add(st2.pop().val);
        return res;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postorderHelper(root, res);
        return res;
    }

    private void postorderHelper(TreeNode node, List<Integer> res) {
        if (node == null) return;

        // Postorder: Left -> Right -> Root
        postorderHelper(node.left, res);
        postorderHelper(node.right, res);
        res.add(node.val);
    }
}
