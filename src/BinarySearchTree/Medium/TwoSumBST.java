package BinarySearchTree.Medium;

import BinaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class TwoSumBST {

    public boolean findTarget(TreeNode root, int k) {
        List<Integer> inorder = new ArrayList<>();
        inorderHelper(root, inorder);

        // Since inorder of BST is already sorted, we could leverage it
        // Now use two pointers to determine what's asked
        int leftPtr = 0;
        int rightPtr = inorder.size() - 1;

        while (leftPtr < rightPtr) {
            int sum = leftPtr + rightPtr;
            if (sum == k) return true;
            else if (sum < k) leftPtr++;
            else rightPtr--;
        }

        return false;
    }

    private static void inorderHelper(TreeNode root, List<Integer> res) {
        if (root == null) return;

        inorderHelper(root.left, res);
        res.add(root.val);
        inorderHelper(root.right, res);
    }
}
