package Trees.traversals;

import Trees.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PreorderTraversal {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        dfs(root, res);
        return res;
    }

    private void dfs(TreeNode root, List<Integer> res) {
        if (root == null)
            return;

        // Preorder: Root Left Right
        res.add(root.val);
        dfs(root.left, res);
        dfs(root.right, res);
    }

    public List<Integer> preorderTraversalIterative(TreeNode root) {
        // Emulate the recursion
        // Now see, since we're gonna wanna move back to our
        // parent nodes like we do in recursion -- we don't
        // have any back pointers, so we'll use stack. we'll
        // before hand save the parent nodes.
        // Do note that in preorder, our root nodes are already
        // processed beforehand when we climb up the recursion,
        // so wouldn't it be better to presave the right nodes
        // rather than the parent node which is already processed.

        List<Integer> res = new ArrayList<>();
        TreeNode curr = root;
        Stack<TreeNode> st = new Stack<>();

        while (curr != null || !st.isEmpty()) {
            if (curr == null) curr = st.pop();

            // Process the root node of curr subtree
            res.add(curr.val);
            // Retain its right in the stack
            if (curr.right != null) st.push(curr.right);

            // Move left since: Root Left Right
            curr = curr.left;
        }

        return res;
    }

}
