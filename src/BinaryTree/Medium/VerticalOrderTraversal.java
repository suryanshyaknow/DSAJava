package BinaryTree.Medium;

import BinaryTree.TreeNode;

import java.util.*;

public class VerticalOrderTraversal {

    private static class Tuple {
        TreeNode node;
        int vertical;
        int level;

        Tuple(TreeNode node, int vertical, int level) {
            this.node = node;
            this.vertical = vertical;
            this.level = level;
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        if (root == null) return new ArrayList<>();

        // For the actual traversal: Inserting the node and popping it off and then inserting its left and right provide they exist.
        Queue<Tuple> queue = new LinkedList<>();
        queue.offer(new Tuple(root, 0, 0));

        // To store the nodes in a structured fashion
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> treeMap = new TreeMap<>();

        while (!queue.isEmpty()) {
            Tuple tuple = queue.poll();
            TreeNode node = tuple.node;
            int y = tuple.vertical;
            int x = tuple.level;

            if (!treeMap.containsKey(y))
                treeMap.put(y, new TreeMap<>());
            if (!treeMap.get(y).containsKey(x))
                treeMap.get(y).put(x, new PriorityQueue<>());
            treeMap.get(y).get(x).offer(node.val);

            if (node.left != null)
                queue.offer(new Tuple(node.left, y-1, x+1));
            if (node.right != null)
                queue.offer(new Tuple(node.right, y+1, x+1));
        }

        List<List<Integer>> res = new ArrayList<>();
        for (TreeMap<Integer, PriorityQueue<Integer>> xMap: treeMap.values()) {
            List<Integer> list = new ArrayList<>();

            for (PriorityQueue<Integer> priorityQueue: xMap.values()) {
                while (!priorityQueue.isEmpty()) {
                    list.add(priorityQueue.poll());
                }
            }
            res.add(list);
        }

        return res;
    }

}
