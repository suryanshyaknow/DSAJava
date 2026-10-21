package Trees;

public class ValidateBinarySearchTree {

    public boolean isValidBST(TreeNode root) {

        // Inutition: See this coulda been solved by treating
        // root as an anchor, max for the left subtree and min
        // for the right subtrees.
        //
        // But the issue is that root itself cant alone be the
        // anchor for all the subtree, root plus the respecive
        // roots of follwing subtree can be.
        //
        // But instead of then keing track ogf multiple anchors,
        // it's just better to keep range as an anchor.
        // And the range is tightest two anchors.

        if (root == null) return true;

        // Cuz there are no such bounds on root
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);

        // Time: O(N)

        /**
         * Note: Sentinels given were 2^31 meaning extremes of Integer.
         *
         * We need low and high to be strictly outside every possible
         * node value, so that no valid node fails the first check.
         */
    }

    private boolean isValid(TreeNode root, long lb, long rb) {
        if (root == null) return true;

        // Now check if the currnet node validates the BST cond
        if (!(root.val > lb && root.val < rb)) return false;

        return isValid(root.left, lb, root.val)
                && isValid(root.right, root.val, rb);
    }
}
