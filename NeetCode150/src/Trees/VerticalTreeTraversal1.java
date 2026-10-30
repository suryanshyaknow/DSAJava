package Trees;

import java.util.*;

public class VerticalTreeTraversal1 {

    /**
     * Variant 1: Here we have to return the overlapping nodes for the same
     * col in the order of their response.
     */

    private static class NodeInfo {
        private TreeNode node;
        private int col;

        public NodeInfo(TreeNode node, int col) {
            this.node = node;
            this.col = col;
        }

    }

    public ArrayList<ArrayList<Integer>> verticalOrder(TreeNode root) {
        // code here
        if (root == null) return new ArrayList<>();

        // Intuition: Since the desired order is vertical from leftmost
        // to the rightmost, it gives an idea to group the nodes by col
        // and then iterate over each col in increasing order and put them
        // in the res array.
        // Traverse the tree level wise for the aforementioned -- Via BFS

        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        Map<Integer, ArrayList<Integer>> cols = new HashMap<>(); // for grouping nodes by cols

        Queue<NodeInfo> q = new LinkedList<>();
        // Init config
        q.offer(new NodeInfo(root, 0));

        int minCol = Integer.MAX_VALUE;
        int maxCol = Integer.MIN_VALUE;

        while (!q.isEmpty()) {
            NodeInfo nodeInfo = q.poll();
            TreeNode node = nodeInfo.node;
            int col = nodeInfo.col;

            cols.computeIfAbsent(col, k -> new ArrayList<>()).add(node.val);
            minCol = Math.min(col, minCol);
            maxCol = Math.max(col, maxCol);

            // Now insert its children into queue
            if (node.left != null) q.offer(new NodeInfo(node.left, col - 1));
            if (node.right != null) q.offer(new NodeInfo(node.right, col + 1));
        }

        // Now iterate over the col range
        for (int i = minCol; i <= maxCol; i++) {
            res.add(cols.get(i));
        }

        return res;

        // Time: O(N + len(cols))
        // Space: O(N + len(cols))
    }
}
