package BinaryTree.Easy;

import BinaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PreorderTraversal {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> st = new Stack<>();

        if (root == null) return res;

        st.push(root);
        while (!st.isEmpty()) {
            TreeNode node = st.pop();
            res.add(node.val);

            if (node.right != null)
                st.push(node.right);

            if (node.left != null)
                st.push(node.left);
        }
        return res;
    }

    private static void inorderHelper(TreeNode node, List<Integer> res) {
        if (node == null) return;

        inorderHelper(node.left, res);
        res.add(node.val);
        inorderHelper(node.right, res);
    }

}
