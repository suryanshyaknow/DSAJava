package BinaryTree.Hard;

import BinaryTree.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class ConstructBTFromInorderAndPreorder {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> iMap = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            iMap.put(inorder[i], i);
        }
        TreeNode root = buildTreeHelper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, iMap);
        return root;
    }

    private TreeNode buildTreeHelper(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, Map<Integer, Integer> iMap) {
        if (preStart > preEnd || inStart > inEnd) return null;

        // Identify the root from preorder
        TreeNode root = new TreeNode(preorder[preStart]);
        int inRootIdx = iMap.get(preorder[preStart]);
        int numsLeft = inRootIdx - inStart; // No. of nodes in left subtree

        // Attach left and right
        root.left = buildTreeHelper(preorder, preStart + 1, preStart + numsLeft, inorder, inStart, inRootIdx - 1, iMap);
        root.right = buildTreeHelper(preorder, preStart + numsLeft + 1, preEnd, inorder, inRootIdx + 1, inEnd, iMap);

        return root;
    }

}
