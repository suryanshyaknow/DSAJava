package Trees;

public class InvertBinaryTree {

    public TreeNode invertTree(TreeNode root) {
        // Pretty simple, just start inverting the
        // tree from root node and keep doing it
        // till you process every node of the tree.
        // Can be done either via preorder -- swap
        // first and then recurse, or postorder --
        // recurse and then swap.

        if (root == null) return null;

        // Swap the left and right child
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree(root.left);
        invertTree(root.right);

        return root;
    }
}
