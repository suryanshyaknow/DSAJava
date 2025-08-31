package BinarySearchTree.Medium;

import BinaryTree.TreeNode;

import java.util.ArrayList;

public class InorderPredecessorSuccessor {

    public ArrayList<TreeNode> findPreSuc(TreeNode root, int key) {
        // code here
        TreeNode curr = root;
        ArrayList<TreeNode> res = new ArrayList<>();

        TreeNode succ = null; // Node immediately greater than the key
        while (curr != null) {
            if (curr.val <= key) {
                // Move right to find the greater val
                curr = curr.right;
            } else {
                // curr is candiate but there might be smaller in left
                succ = curr;
                curr = curr.left;
            }
        }

        TreeNode pre = null;
        curr = root;
        while (curr != null) {
            if (curr.val < key) {
                pre = curr;
                curr = curr.right;
            } else {
                curr = curr.left;
            }
        }
        res.add(pre);
        res.add(succ);
        return res;
    }

}
