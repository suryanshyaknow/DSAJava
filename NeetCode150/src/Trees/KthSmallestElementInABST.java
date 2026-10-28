package Trees;

import java.util.*;

public class KthSmallestElementInABST {

    public int kthSmallest(TreeNode root, int k) {
        // Intuition: The inorder traversal of the BST is sorted.
        // So we're gonna leverage this property.
        if (root == null) return -1;

        // Iterate using stack simulating recursion and note that
        // the kth node to be popped is gonna be the kth smallest.

        int cnt = 0;
        TreeNode curr = root;
        Deque<TreeNode> st = new ArrayDeque<>();

        while (curr != null || !st.isEmpty()) {
            while (curr != null) {
                st.addLast(curr);
                curr = curr.left;
            }

            // Now process the sts topmost node
            curr = st.pollLast();
            cnt += 1;
            if (cnt == k) return curr.val;

            curr = curr.right;
        }

        return -1;

        // Time: O(H + k)
        // Space: O(H)
    }
}
