package BinarySearchTree.Medium;

import BinaryTree.TreeNode;

public class DeleteNode {

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return root;

        if (root.val == key) return helper(root);

        // Search the node and process the linkages
        TreeNode curr = root;
        while (curr != null) {
            if (curr.val > key) { // Move left
                if (curr.left != null && curr.left.val == key) {
                    curr.left = helper(curr.left); // Node to be deleted is being passed on to the helper function
                    break;
                } else curr = curr.left;
            } else { // Move right
                if (curr.right != null && curr.right.val == key) {
                    curr.right = helper(curr.right);
                    break;
                } else curr = curr.right;
            }
        }
        return root;
    }

    private TreeNode helper(TreeNode node) {
        if (node.left == null) return node.right;
        if (node.right == null) return node.left;

        TreeNode rightChild = node.right; // We've gotta attach it to the rightmost node of node's left
        TreeNode leftRightmost = findRightMost(node.left);
        leftRightmost.right = rightChild;
        return node.left;
    }

    private TreeNode findRightMost(TreeNode node) {
        while (node != null && node.right != null) node = node.right;
        return node;
    }


}
