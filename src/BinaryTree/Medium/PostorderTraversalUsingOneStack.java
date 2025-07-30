package BinaryTree.Medium;

import BinaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PostorderTraversalUsingOneStack {

    public List<Integer> postorderTraversal(TreeNode root) {
        // Intuition:
        // - Go left till null
        // - Then check right
        // - Go deep into right
        // - When both left and right are done - process root
        // But the tricky part is knowing when a node's right subtree has already been processed. For that we have prev.

        /*
        - Dive left first
        - Then peek if right exists and hasn’t been processed
        - Only then touch the node
         */

        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> st = new Stack<>();
        TreeNode curr = root;
        TreeNode prev = null; // To keep track of already processed right nodes

        while (curr != null || !st.isEmpty()) {
            // Go deep into left
            while (curr != null) {
                st.push(curr);
                curr = curr.left;
            }

            // Peek the top node
            TreeNode peekNode = st.peek();

            // If right exists, process
            if (peekNode.right != null && prev != peekNode.right) {
                curr = peekNode.right;
            } else {
                res.add(peekNode.val);
                prev = st.pop();
            }
        }
        return res;
    }
}
