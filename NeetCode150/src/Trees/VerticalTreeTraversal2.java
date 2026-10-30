package Trees;

import java.util.*;

public class VerticalTreeTraversal2 {

    private static class NodeInfo {

        private TreeNode node;
        private int col;

        NodeInfo(TreeNode node, int col) {
            this.node = node;
            this.col = col;
        }

    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        if (root == null)
            return new ArrayList<>();

        // Intuition: Since we're to traverse from the leftmost
        // col to the rightmost, we have to group the nodes by
        // cols somehow and traverse the grouping in a sorted
        // fashion.
        // For overlapping nodes, we're gonna sort the nodes in a
        // given col by the node's values.

        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, List<Integer>> cols = new HashMap<>();

        // I'm gonna create the grouping via BFS traversal
        Queue<NodeInfo> q = new LinkedList<>();
        q.offer(new NodeInfo(root, 0));

        int colMin = Integer.MAX_VALUE;
        int colMax = Integer.MIN_VALUE;

        while (!q.isEmpty()) {
            // I'll store all the nodes of a level in an array
            // And at last I'll sort it by node values, so that
            // overlapping nodes gets in desired order.
            int levelSize = q.size();
            List<NodeInfo> level = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                NodeInfo nodeInfo = q.poll();
                TreeNode curr = nodeInfo.node;
                int col = nodeInfo.col;

                // Update the col range
                colMin = Math.min(colMin, col);
                colMax = Math.max(colMax, col);

                // Insert node along w col into level
                level.add(nodeInfo);

                // Insert its children into queue
                if (curr.left != null)
                    q.offer(new NodeInfo(curr.left, col - 1));
                if (curr.right != null)
                    q.offer(new NodeInfo(curr.right, col + 1));
            }

            // Sort the level by node's values
            level.sort(
                    (a, b) -> Integer.compare(a.node.val, b.node.val));
            // k log k, k being the nodes in a level

            // Now do the grouping of nodes by col
            for (NodeInfo ni : level) {
                cols.computeIfAbsent(ni.col, k -> new ArrayList<>())
                        .add(ni.node.val);
            }
        }

        // Iterate over the groups by col and prep res
        for (int i = colMin; i <= colMax; i++) {
            res.add(cols.get(i));
        }

        return res;

        // Time: O(k log k) where k <= N
        // Space: O(N)
    }

}
