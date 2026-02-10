package BinaryTree;

import java.util.*;

public class BottomViewOfBinaryTree {

    private static class Tuple {
        TreeNode node;
        Integer vertical;

        Tuple(TreeNode node, Integer vertical) {
            this.node = node;
            this.vertical = vertical;
        }
    }

    public ArrayList<Integer> bottomView(TreeNode root) {
        // code here
        // Use the concept of vertical order traversal
        // where you traverse column wise from top to bottom.
        // Maintain a treeMap (order matters) and insert the
        // latestly visited node for a given vertical column.

        if (root == null) new ArrayList<>();

        TreeMap<Integer, Integer> tMap = new TreeMap<>();
        Queue<Tuple> q = new LinkedList<>();
        q.offer(new Tuple(root, 0));
        while (!q.isEmpty()) {
            int levelSize = q.size();

            for (int i = 0; i < levelSize; i++) {
                Tuple tup = q.poll();
                TreeNode node = tup.node;
                int y = tup.vertical;

                // Populate the map
                // For each vertical column, pick the last (deepest) node visible when viewed from bottom.
                tMap.put(y, node.val); // at last the most recent node will be in tmap

                if (node.left != null) q.offer(new Tuple(node.left, y - 1));
                if (node.right != null) q.offer(new Tuple(node.right, y + 1));
            }

        }

        ArrayList<Integer> res = new ArrayList<>();
        for (Integer val : tMap.values()) {
            res.add(val);
        }
        return res;
    }
}
