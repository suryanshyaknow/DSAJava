package Trees.traversals;

import Trees.TreeNode;
import java.util.*;
import java.util.stream.Collectors;

public class ZigZagLevelOrderTraversal {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();

        List<List<Integer>> res = new ArrayList<>();

        // Gonna process the nodes level by level via BFS
        // I'm just gonna make sure while im processing a level from the queue
        // I traverse it from the end.
        Deque<TreeNode> dq = new ArrayDeque<>();
        dq.offer(root); // init config

        boolean reverse = false;
        while (!dq.isEmpty()) {
            int levelSize = dq.size();

            int[] level = new int[levelSize];
            for (int i=0; i < levelSize; i++) {
                TreeNode node = dq.pollFirst();
                if (reverse)
                    level[levelSize - 1 - i] = node.val;
                else
                    level[i] = node.val;

                if (node.left != null) dq.offer(node.left);
                if (node.right != null) dq.offer(node.right);
            }
            res.add(
                    Arrays.stream(level).boxed().collect(Collectors.toList())
            );
            reverse = !reverse;
        }

        return res;
    }
}