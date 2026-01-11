package BinaryTree.Medium;

import BinaryTree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class MaxWidth {

    static class Pair {

        private TreeNode node;
        private int idx;

        Pair(TreeNode node, int idx) {
            this.node = node;
            this.idx = idx;
        }
    }

    public int widthOfBinaryTree(TreeNode root) {
        // Of course, level order traversal is gon' be in the picture.
        // Now, if we could index every node in way that to calculate the width of a
        // given level, we just gotta: last node idx - first node idx + 1
        // then we're golden.
        // Root node: i (0-based), left child: 2*i + 1, right child: 2*i + 2

        // But wait, w each level we're doubling the idx, and at one point we're gonna overflow the int value.
        // So to tackle this, at each level:
        // - Record min_index (index of first node).
        // - For every node in that level:
        //      -> Use curr_index - min_index instead of curr_index.
        //      -> Assign children:
        //                          left child → (curr_index - min_index) * 2
        //                          right child → (curr_index - min_index) * 2 + 1

        if (root == null) return 0;
        int maxH = 0;

        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(root, 0));

        while (!q.isEmpty()) {
            int levelSize = q.size();
            int first = 0;
            int last = 0;
            int minIdx = q.peek().idx; // first node of a level

            // We've gotta record the first and the last to compute the width of level
            for (int i = 0; i < levelSize; i++) {
                Pair pair = q.poll();
                TreeNode node = pair.node;
                int currIdx = pair.idx - minIdx;

                if (i == 0) first = currIdx;
                if (i == levelSize - 1) last = currIdx;

                if (node.left != null) q.offer(new Pair(node.left, 2 * currIdx + 1));
                if (node.right != null) q.offer(new Pair(node.right, 2 * currIdx + 2));
            }

            maxH = Integer.max(last - first + 1, maxH);
        }

        return maxH;
    }

}
