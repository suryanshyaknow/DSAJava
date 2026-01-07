package BinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PostOrderTraversal {

    public List<Integer> postorderTraversalUsingTwoStacks(TreeNode root) {
        if (root == null) return new ArrayList<>();

        // Maintain two stacks
        // Add the root node as initial config into st1,
        // now keep popping elements from st1 and add 'em
        // to st2, but while adding, check for their left and
        // right and add them respectively into stack 1.

        Stack<TreeNode> st1 = new Stack<>();
        Stack<TreeNode> st2 = new Stack<>();

        st1.add(root);
        while (!st1.isEmpty()) {
            TreeNode node = st1.pop();

            st2.add(node);

            if (node.left != null) st1.add(node.left);
            if (node.right != null) st1.add(node.right);
        }
        List<Integer> res = new ArrayList<>();
        while (!st2.isEmpty()) {
            TreeNode node = st2.pop();
            res.add(node.val);
        }

        return res;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        recurse(root, res);
        return res;
    }

    private static void recurse(TreeNode root, List<Integer> res) {
        if (root == null) return;

        recurse(root.left, res);
        recurse(root.right, res);
        res.add(root.val);
    }

}
