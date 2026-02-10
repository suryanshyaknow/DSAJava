package BinaryTree;

import java.util.*;

public class VerticalOrderTraversal {

    private static class Tuple {

        private TreeNode node;
        private int vertical; // y coordinate
        private int level; // x coordinate

        Tuple(TreeNode node, int vertical, int level) {
            this.node = node;
            this.vertical = vertical;
            this.level = level;
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        if (root == null) return new ArrayList<>();

        // Define a ds to store nodes vals by vertical and then
        // level-size.
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
        Queue<Tuple> q = new LinkedList<>();

        // Do the level order traversal & populate the map
        q.offer(new Tuple(root, 0, 0));
        while (!q.isEmpty()) {
            int levelSize = q.size();

            for (int i = 0; i < levelSize; i++) {
                Tuple tup = q.poll();
                TreeNode node = tup.node;
                int y = tup.vertical;
                int x = tup.level;

                // Populate the map
                // Insert the vertical if it ain't already there
                if (!map.containsKey(y)) map.put(y, new TreeMap<>());
                // Insert the level if it ain't already there
                if (!map.get(y).containsKey(x)) map.get(y).put(x, new PriorityQueue<>());
                // Insert the node at for that level and the vertical in the PQ
                map.get(y).get(x).offer(node.val);

                // Insert the node's left and right
                if (node.left != null) q.offer(new Tuple(node.left, y - 1, x + 1));
                if (node.right != null) q.offer(new Tuple(node.right, y + 1, x + 1));
            }
        }

        List<List<Integer>> res = new ArrayList<>();
        // Iterate over vertical that's the outer keys of map
        for (TreeMap<Integer, PriorityQueue<Integer>> xMap : map.values()) {
            List<Integer> level = new ArrayList<>();

            for (PriorityQueue<Integer> pq : xMap.values()) {
                while (!pq.isEmpty()) level.add(pq.poll());
            }
            res.add(level);
        }

        return res;
    }
}
