package BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class WidthOfBinaryTree {

    private static class Pair {

        private TreeNode node;
        private Integer idx;

        Pair(TreeNode node, Integer idx) {
            this.node = node;
            this.idx = idx;
        }

    }

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null)
            return 0;

        // The idea is pretty simple
        // Since we're just supposed to count the number of nodes
        // (including the null nodes) in a level between the
        // extremes, we shouldm just care about the idx of the
        // extremes. So to each node, we're gonna assign an idx.
        // node.left -> 2 * idx + 1
        // node.right -> 2 * idx + 2
        // But this 2x factor is gonna cause overflow,
        // so we're gonna normalize the assignment via the min idx.

        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(root, 0));
        int width = 0;

        while (!q.isEmpty()) {
            int levelSize = q.size();
            int minIdx = q.peek().idx; // first node in the level
            int lastIdx = 0;
            int firstIdx = 0;

            for (int i = 0; i < levelSize; i++) {
                // Pop the node
                Pair pair = q.poll();
                TreeNode node = pair.node;
                int idx = pair.idx - minIdx; // normalized idx

                if (node.left != null) q.offer(new Pair(node.left, 2 * idx + 1));
                if (node.right != null) q.offer(new Pair(node.right, 2 * idx + 2));

                // Update first and last
                if (i == 0) firstIdx = idx;
                if (i == levelSize - 1) lastIdx = idx;
            }
            width = Math.max(width, lastIdx - firstIdx + 1);
        }

        return width;

        // Time complexity: O(N)
        // Space complexity: O(N)
    }
}
