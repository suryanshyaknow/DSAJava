package BinaryTree.Medium;

import BinaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class AllTraversalsInSingleTraversal {

    private static class Pair {
        TreeNode node;
        int val;

        Pair(TreeNode node, int val) {
            this.node = node;
            this.val = val;
        }
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<Pair> st = new Stack<>();
        List<Integer> pre = new ArrayList<>();
        List<Integer> post = new ArrayList<>();
        List<Integer> in = new ArrayList<>();
        if (root == null) return new ArrayList<>();

        st.push(new Pair(root, 1));

        while (!st.isEmpty()) {
            Pair pair = st.pop();

            if (pair.val == 1) {
                // Goes to pre
                pre.add(pair.node.val);
                // val++
                pair.val++;
                st.push(pair);

                // Check node's left and push
                if (pair.node.left != null) st.push(new Pair(pair.node.left, 1));
            } else if (pair.val == 2) {
                // Goes to in
                in.add(pair.node.val);
                // val++
                pair.val++;
                st.push(pair);

                // Check node's right and push
                if (pair.node.right != null) st.push(new Pair(pair.node.right, 1));
            } else {
                // Goes in post
                // Pair remains popped
                post.add(pair.node.val);
            }
        }
        return post;
    }
}

