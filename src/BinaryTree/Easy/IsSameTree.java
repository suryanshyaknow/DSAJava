package BinaryTree.Easy;

import BinaryTree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class IsSameTree {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        // Via Pre-order Traversal
        if (p == null || q == null) return (p == q);

        return (p.val == q.val) && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public boolean isSameTree2(TreeNode p, TreeNode q) {
        // Via Pre-order Traversal
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;

        if (p.val != q.val) return false;

        boolean isLeftSame = isSameTree2(p.left, q.left);
        boolean isRightSame = isSameTree2(p.right, q.right);

        return isLeftSame && isRightSame;
    }

    public boolean isSameTreeIterative(TreeNode p, TreeNode q) {
        Queue<TreeNode[]> queue = new LinkedList<>();
        queue.offer(new TreeNode[]{p, q});

        while (!queue.isEmpty()) {
            TreeNode[] treeNodes = queue.poll();
            TreeNode node1 = treeNodes[0];
            TreeNode node2 = treeNodes[1];

            // Both null, continue
            if (node1 == null && node2 == null) continue;

            // One null, return false
            if (node1 == null || node2 == null) return false;
            // Value differs, false
            if (node1.val != node2.val) return false;

            // Push their children in queue
            queue.offer(new TreeNode[]{node1.left, node2.left});
            queue.offer(new TreeNode[]{node1.right, node2.right});
        }

        return true;
    }

}
