package Trees;

public class SameTree {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        // The idea is pretty simple, iterate both trees
        // simultaneously and compare left's and right's
        // tree nodes recursively.
        //
        // Also, preorder would make sense in here cuz we're
        // comparing from top-down.

        if (p == null && q == null) return true;
        if ((p == null || q == null)
                || (p.val != q.val)
        ) return false;

        // Now since root node of both the trees
        // are same, we can traverse the other nodes
        // recursively.
        boolean isLeftSame = isSameTree(p.left, q.left);
        boolean isRightSame = isSameTree(p.right, q.right);
        return isLeftSame && isRightSame;

        // Time: O(p + q)
    }
}
