package BinaryTree.Medium;

import BinaryTree.TreeNode;

import java.util.*;

public class TopViewOfBinaryTree {

    private static class Tuple {
        TreeNode node;
        Integer vertical;

        Tuple(TreeNode node, Integer vertical) {
            this.node = node;
            this.vertical = vertical;
        }
    }

    public ArrayList<Integer> topView(TreeNode root) {
        // code here
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Queue<Tuple> queue = new LinkedList<>();
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        queue.offer(new Tuple(root, 0));

        while (!queue.isEmpty()) {
            Tuple tuple = queue.poll();
            TreeNode node = tuple.node;
            Integer vertical = tuple.vertical;

            if (!treeMap.containsKey(vertical))
                treeMap.put(vertical, node.val);
            // Need not do anything if key already exists, cuz it's already holding off to the top node.

            if (node.left != null)
                queue.offer(new Tuple(node.left, vertical - 1));
            if (node.right != null)
                queue.offer(new Tuple(node.right, vertical + 1));
        }

        // Just iterate over the map and store the vals
        res.addAll(treeMap.values());

        return res;
    }

    public ArrayList<Integer> bottomView(TreeNode root) {
        // code here
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Queue<Tuple> queue = new LinkedList<>();
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        queue.offer(new Tuple(root, 0));

        while (!queue.isEmpty()) {
            Tuple tuple = queue.poll();
            TreeNode node = tuple.node;
            Integer vertical = tuple.vertical;

            treeMap.put(vertical, node.val); // Keep on updating the val for the same vertical, cuz the latest val is gon be the bottom view node.

            if (node.left != null)
                queue.offer(new Tuple(node.left, vertical - 1));
            if (node.right != null)
                queue.offer(new Tuple(node.right, vertical + 1));
        }

        // Just iterate over the map and store the vals
        res.addAll(treeMap.values());
        return res;
    }

}
