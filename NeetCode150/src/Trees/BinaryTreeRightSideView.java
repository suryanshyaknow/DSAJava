package Trees;

import java.util.*;

public class BinaryTreeRightSideView {

    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return new ArrayList<>();

        // Intuition: Can't be simpler than this. Traverse
        // level order and the last node is what one sees from
        // the right side view.

        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int levelSize = q.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = q.poll();
                if (i == levelSize - 1)
                    res.add(node.val);

                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
        }
        return res;
    }

    public List<Integer> rightSideViewDfs(TreeNode root) {

        // Intuition: See, think of preorder and observe
        // that it always process the node and then traverses
        // the left. We're gonna leverage that we're gonna kinda
        // go in reverse direction of left order: Root Right Left

        // Each time you a visit a level for the first time, store
        // the first node encountered.

        List<Integer> res = new ArrayList<>();
        dfs(root, 0, res);
        return res;
    }

    private void dfs(TreeNode root, int level, List<Integer> res) {
        if (root == null) return;

        if (level == res.size()) res.add(root.val);
        dfs(root.right, level + 1, res);
        dfs(root.left, level + 1, res);
    }
}
