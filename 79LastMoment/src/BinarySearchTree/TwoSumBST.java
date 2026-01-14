package BinarySearchTree;

import BinaryTree.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TwoSumBST {

    public boolean findTarget(TreeNode root, int k) {
        // Intiution: Use the property of BST that states
        // inorder traversal of BST is always sorted.
        // So we'll generate the inorder traveral of given
        // BST and then determine the 2sum using two pointers.

        if (root == null) return false;
        List<Integer> inorder = new ArrayList<>();
        dfsInorder(root, inorder);

        // Since inorder is sorted, use two pointers
        int leftPtr = 0;
        int rightPtr = inorder.size() - 1;
        List<Integer> arr = inorder;
        while (leftPtr < rightPtr) {
            int sum = arr.get(leftPtr) + arr.get(rightPtr);
            if (sum == k) return true;
            if (sum < k) leftPtr++;
            else rightPtr--;
        }

        // Time complexity: O(N) + O(N) for inorder and two pointers traversal
        // Space complexity: O(N) for inorder + another O(N) recursive call stack
        return false;
    }

    private static void dfsInorder(TreeNode root, List<Integer> res) {
        if (root == null) return;

        dfsInorder(root.left, res);
        res.add(root.val);
        dfsInorder(root.right, res);
    }

    public boolean findTargetUsingHashing(TreeNode root, int k) {
        if (root == null) return false;

        // Iterate thru BST and use hashing
        HashSet<Integer> set = new HashSet<>();

        return recurseBST(root, set, k);

        // Time complexity: O(N)
        // Space complexity: O(N) for hashset + another O(N) for recursive call stack
    }

    private static boolean recurseBST(TreeNode root, HashSet<Integer> set, int k) {
        if (root == null) return false;

        int val = root.val;
        // If set contains its complement to form sum k, then return true
        if (set.contains(k - val)) return true;
        set.add(val);

        return recurseBST(root.left, set, k) || recurseBST(root.right, set, k);
    }
}
