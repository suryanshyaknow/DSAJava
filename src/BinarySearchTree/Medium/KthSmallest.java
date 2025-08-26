package BinarySearchTree.Medium;

import BinaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class KthSmallest {

    public int kthSmallest(TreeNode root, int k) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return -1;

        Stack<TreeNode> st = new Stack<>();
        TreeNode curr = root;
        int counter = 0;

        while (curr != null || !st.isEmpty()) {
            // Go as left as possible
            while (curr != null) {
                st.push(curr);
                curr = curr.left;
            }

            // Pop, add to res
            // Now, either curr is null or the leaf node which ain't got no left
            curr = st.pop();
            // res.add(curr.val);
            counter += 1;
            if (counter == k) return curr.val;

            // Move right
            curr = curr.right;
        }
        return -1;
    }


    public int kthSmallestRecursive(TreeNode root, int k) {
        int counter[] = new int[1];
        counter[0] = 0;
        return inorder(root, counter, k);
    }

    private static int inorder(TreeNode root, int[] counter, int k) {
        if (root == null)
            return -1;

        int left = inorder(root.left, counter, k);
        if (left != -1) return left;

        counter[0] += 1;
        if (counter[0] == k)
            return root.val;
        return inorder(root.right, counter, k);
    }
}
