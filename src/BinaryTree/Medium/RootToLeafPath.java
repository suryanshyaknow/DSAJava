package BinaryTree.Medium;

import BinaryTree.TreeNode;

import java.util.ArrayList;

public class RootToLeafPath {

    public static ArrayList<ArrayList<Integer>> Paths(TreeNode root) {
        // code here
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        if (root == null) return res;

        getPaths(root, temp, res);
        return res;
    }

    private static void getPaths(TreeNode node, ArrayList<Integer> temp, ArrayList<ArrayList<Integer>> res) {
        if (node == null) return;

        temp.add(node.val);
        if (isLeafNode(node)) {
            res.add(new ArrayList<>(temp));
            temp.removeLast();
            return;
        }

        getPaths(node.left, temp, res);
        getPaths(node.right, temp, res);
        temp.removeLast();
    }

    private static boolean isLeafNode(TreeNode node) {
        return node.left == null && node.right == null;
    }

}
