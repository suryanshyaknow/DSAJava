package BinaryTree.Medium;

import BinaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BoundaryTraversal {

    public List<Integer> boundaryTraversal(TreeNode root) {
        if (root == null) return new ArrayList<>();

        List<Integer> res = new ArrayList<>();
        if (!isLeafNode(root)) res.add(root.val);

        addLeftBoundary(root, res); // Excluding leaf nodes
        addLeafNodes(root, res);
        addRightBoundary(root, res); // Excluding leaf nodes

        return res;
    }

    private void addRightBoundary(TreeNode root, List<Integer> res) {
        TreeNode node = root.right;
        List<Integer> temp = new ArrayList<>();
        while (node != null) {
            if (!isLeafNode(node)) temp.add(node.val);

            if (node.right != null) node = node.right;
            else node = node.left;
        }

        for (int i = 0; i < temp.size(); i++) {
            res.add(temp.get(temp.size() - 1 - i));
        }
    }

    private void addLeafNodes(TreeNode node, List<Integer> res) {
        if (isLeafNode(node)) {
            res.add(node.val);
            return;
        }

        addLeafNodes(node.left, res);
        addLeafNodes(node.right, res);
    }

    private void addLeftBoundary(TreeNode root, List<Integer> res) {
        TreeNode node = root.left;
        while (node != null) {
            if (!isLeafNode(node)) res.add(node.val);
            if (node.left != null) node = node.left;
            else node = node.right;
        }
    }

    private boolean isLeafNode(TreeNode node) {
        return node.left == null && node.right == null;
    }


}
