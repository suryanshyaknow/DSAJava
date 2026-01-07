package BinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PreorderTraversal {

    public List<Integer> preorderTraversalIterative(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<Integer> res = new ArrayList<>();

        Stack<TreeNode> st = new Stack<>();
        st.add(root); // Initial config

        while (!st.isEmpty()) {
            // Pop the node and insert its right then left
            TreeNode node = st.pop();
            res.add(node.val);

            if (node.right != null) st.add(node.right);
            if (node.left != null) st.add(node.left);
        }
        return res;
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res  = new ArrayList<>();
        recurse(root, res);
        return res;
    }

    private static void recurse(TreeNode root, List<Integer> res) {
        if (root == null) return;

        res.add(root.val);
        recurse(root.left, res);
        recurse(root.right, res);
    }

}
