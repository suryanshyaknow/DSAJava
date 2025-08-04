package BinaryTree.Medium;

import BinaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class RightViewOfBinaryTree {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        rightSideViewHelper(root, 0, res);
        return res;

        // Time complexity: O(N) where N -> Number of nodes
        // Space Complexity: O(H) where H -> Height of the tree
    }

    private void rightSideViewHelper(TreeNode node, int level, List<Integer> res) {
        // Preorder: Root -> Left -> Right
        // But since we want the right nodes, we'd rather go: Root -> Right -> Left
        if (node == null) return;

        if (res.size() == level)
            res.add(node.val);
        rightSideViewHelper(node.right, level+1, res);
        rightSideViewHelper(node.left, level+1, res);
    }

    private void leftSideViewHelper(TreeNode node, int level, List<Integer> res) {
        // Preorder: Root -> Left -> Right
        if (node == null) return;

        if (res.size() == level)
            res.add(node.val);
        rightSideViewHelper(node.left, level+1, res);
        rightSideViewHelper(node.right, level+1, res);
    }


}
