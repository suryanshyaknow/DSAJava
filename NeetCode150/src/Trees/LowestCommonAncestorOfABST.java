package Trees;

public class LowestCommonAncestorOfABST {

    public TreeNode lowestCommonAncestorItertaive(TreeNode root, TreeNode p, TreeNode q) {

        TreeNode curr = root;

        while (curr != null) {
            if (curr.val > p.val && curr.val > q.val)
                curr = curr.left;
            else if (curr.val < p.val && curr.val < q.val)
                curr = curr.right;
            else
                return curr;
        }

        return null;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Intuition: Since this one's a BST, it saves us some work
        // eliminates almost half the iteration we had to do in BT.
        // 1. Based on root node's value we're gonna traverse left
        // or right.
        // 2. If root happens to be either of p or q, then root itself
        // is the ancestor.
        // 3. Similarly, if root lies bw p and q, meaning p and q both
        // diverges on both sides of root, that only determines that
        // root is the ancestor.

        if (root == null) return null; // Not required since both
        // nodes are meant to be in the tree

        // // We're at the node where both p and q lies at the either
        // // side of this node.
        // if (root.val >= Math.min(p.val, q.val) && root.val <= Math.max(p.val, q.val)) return root;

        // Root is smaller than p & q: Move right
        if (root.val < p.val && root.val < q.val)
            return lowestCommonAncestor(root.right, p, q);

        // Root is greater than p & q: Move left
        if (root.val > p.val && root.val > q.val)
            return lowestCommonAncestor(root.left, p, q);

        return root;
    }
}
