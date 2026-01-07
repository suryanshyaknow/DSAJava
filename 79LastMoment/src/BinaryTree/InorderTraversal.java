package BinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InorderTraversal {

    public List<Integer> inorderTraversalIterative(TreeNode root) {
        if (root == null) return new ArrayList<>();

        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> st = new Stack<>();
        TreeNode node = root;
        while (node != null || !st.isEmpty()) {
            // Go left.. left.. left..
            while (node != null) {
                st.push(node);
                node = node.left;
            }
            // Process
            node = st.pop();
            res.add(node.val);

            // Shift to right
            node = node.right;
        }
        return res;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        recurse(root, res);
        return res;
    }

    private static void recurse(TreeNode node, List<Integer> res) {
        if (node == null) return;

        // Inorder: Left Root Right
        recurse(node.left, res);
        res.add(node.val);
        recurse(node.right, res);
    }

}
