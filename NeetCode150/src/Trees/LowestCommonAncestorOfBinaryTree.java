package Trees;

public class LowestCommonAncestorOfBinaryTree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Intuition: We're gonna traverse the tree via post order.
        // 1. Return null when there's no tree left to traverse.
        // 2. Return either of node p and q whenever they're encountered.
        // NOTE: Note that traversal ain't post order innnately, as
        // we're not further traversing down whenever we encounter
        // node w p or q.
        //
        // 3. Return the node when recursive results of both the nodes
        // are both non-nu i.e. further down we indeed encoutner p and
        // (or) q.
        // 4. If only one side is non-null, pass it upwards.
        //
        // NOTE2: Also note that we can make this purely postorder
        // traversal but that way we'll lose the early pruning which //
        // the hybrid apporach provides us.

        if (root == null) return null;
        if (root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // If both non-null, return root
        if (left != null && right != null) return root;

        // If either is non-null, return the non-null
        return (left != null) ? left : right;
    }
}
