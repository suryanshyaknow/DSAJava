package BinaryTree.Easy;

import BinaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InorderTraversal {

    public List<Integer> inorderIterativeTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Stack<TreeNode> st = new Stack<>();
        st.push(root);
        TreeNode curr = root;

        while (!st.isEmpty()) {
            // Go as left as possible
            while (curr != null && curr.left != null) {
                st.push(curr.left);
                curr = curr.left;
            }

            // Pop, add to res
            // Now, either curr is null or the leaf node which ain't got no left
            curr = st.pop();
            res.add(curr.val);

            // If right exists, process it and continue left from there
            if (curr.right != null) {
                curr = curr.right;
                st.push(curr);
            }
        }
        return res;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        // Left -> Root -> Right
        inorderHelper(root, res);
        return res;
    }

    private static void inorderHelper(TreeNode node, List<Integer> res) {
        if (node == null) return;

        inorderHelper(node.left, res);
        res.add(node.val);
        inorderHelper(node.right, res);
    }

}
