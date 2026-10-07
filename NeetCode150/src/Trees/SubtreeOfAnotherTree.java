package Trees;

public class SubtreeOfAnotherTree {

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        // If subRoot is empty, it's pretty much subtree of any tree
        if (subRoot == null) return true;
        // But the otherwise ain't true
        if (root == null) return false; // implied that subRoot ain't empty here

        // Now we're gonna compare the root of given tree
        // to subroot to see if latter is subtree of the former
        if (isSameTree(root, subRoot)) return true;

        // NOw if the above ain't true we're gonna iterate over
        // children of the root and see if any of those fulfils
        // the ask.
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);

        // Time: O(N . M)

        // NOTE: Think of this as nested for-loop approach.
        // We're iteraitng over each node of the given tree and asking
        // whether the subtree orginating from there is same as subRoot.
    }

    private boolean isSameTree(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;

        // Compare the values
        if (root1.val != root2.val) return false;

        // Now check all these for their children
        return isSameTree(root1.left, root2.left)
                && isSameTree(root1.right, root2.right);
    }
}
