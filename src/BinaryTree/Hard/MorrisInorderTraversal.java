package BinaryTree.Hard;

import BinaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class MorrisInorderTraversal {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode curr = root;

        // Step 1: If no left exists, then just traverse the BT like a LL, i.e., node = node.right
        // Step 2: If it does exist, create a binding (thread) of the rightmost node on the left subtree to the root.
        while (curr != null) {
            if (curr.left == null) {
                // Step 1
                res.add(curr.val);
                curr = curr.right;
            } else {
                // Step 2
                // Find the rightmost node on the left subtree
                TreeNode temp = curr.left;
                while (temp.right != null && temp.right != curr) { // Right not null and right not pointing back to root
                    temp = temp.right;
                }

                if (temp.right == null) {
                    // Create the binding, and move the curr to left
                    temp.right = curr;
                    curr = curr.left;
                } else {
                    // Binding already exists, destroy it
                    temp.right = null;
                    res.add(curr.val);
                    // Current had already visited the left since the binding existed, thus move it to right
                    curr = curr.right;
                }
            }
        }
        return res;
    }

    public List<Integer> preOrderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode curr = root;

        // Step 1: If no left exists, then just traverse the BT like a LL, i.e., node = node.right
        // Step 2: If it does exist, create a binding (thread) of the rightmost node on the left subtree to the root.
        while (curr != null) {
            if (curr.left == null) {
                // Step 1
                res.add(curr.val);
                curr = curr.right;
            } else {
                // Step 2
                // Find the rightmost node on the left subtree
                TreeNode temp = curr.left;
                while (temp.right != null && temp.right != curr) { // Right not null and right not pointing back to root
                    temp = temp.right;
                }

                if (temp.right == null) {
                    // Create the binding, and move the curr to left
                    temp.right = curr;
                    res.add(curr.val);
                    curr = curr.left;
                } else {
                    // Binding already exists, destroy it
                    temp.right = null;
                    // Current had already visited the left since the binding existed, thus move it to right
                    curr = curr.right;
                }
            }
        }
        return res;
    }

}
