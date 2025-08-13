package BinaryTree.Easy;

import BinaryTree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ZigZagTraversalOfBinaryTree {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        q.offer(root);
        boolean isLeftToRight = true;
        while (!q.isEmpty()) {
            int levelSize = q.size();
            List<Integer> level = new ArrayList();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = q.poll();

                if (isLeftToRight) {
                    level.addLast(node.val);
                } else {
                    level.addFirst(node.val);
                }

                if (node.left != null)
                    q.offer(node.left);
                if (node.right != null)
                    q.offer(node.right);
            }
            res.add(level);
            isLeftToRight = !isLeftToRight;
        }
        return res;
    }


}
