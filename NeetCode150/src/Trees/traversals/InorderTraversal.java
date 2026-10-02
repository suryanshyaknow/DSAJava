package Trees.traversals;

import Trees.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InorderTraversal {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        dfs(root, res);
        return res;
    }

    private void dfs(TreeNode root, List<Integer> res) {
        if (root == null) return;

        // Inorder: Left Root Right
        dfs(root.left, res);
        res.add(root.val);
        dfs(root.right, res);
    }

    /**
     * Iterative Inorder Traversal
     */
    public List<Integer> inorderTraversalIterative(TreeNode root) {
        // Intuition: We're gonna simulate the recursive approach
        // via stack.
        // Retain each node in the stack until its left subtree
        // is fully done, then visit it, then repeat the same
        // process on its right subtree.
        //
        // Point curr to root and go left till you can't
        // anymore, then process the node, and process its
        // right.

        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> st = new Stack<>();

        TreeNode curr = root;
        while (curr != null || !st.isEmpty()) {
            while (curr != null) {
                st.push(curr);
                curr = curr.left;
            }

            // Now since curr is null, process the root node
            curr = st.pop();
            res.add(curr.val);

            // Now process this root node's right
            curr = curr.right;
        }

        return res;
    }

}
