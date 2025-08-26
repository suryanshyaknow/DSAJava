package BinarySearchTree.Medium;

import BinaryTree.TreeNode;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class ConstructBSTFromPreorder {

    public TreeNode bstFromPreorder(int[] preorder) {
        int[] inorder = Arrays.copyOf(preorder, preorder.length);
        Arrays.sort(inorder); // BST property

        Map<Integer, Integer> iMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            iMap.put(inorder[i], i);
        }

        return bstFromPreorderHelper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, iMap);
    }

    private TreeNode bstFromPreorderHelper(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, Map<Integer, Integer> iMap) {
        if (preStart > preEnd || inStart > inEnd) return null;

        TreeNode root = new TreeNode(preorder[preStart]);
        int inRootIdx = iMap.get(preorder[preStart]);
        int nodesLeft = inRootIdx - inStart;

        root.left = bstFromPreorderHelper(preorder, preStart + 1, preStart + nodesLeft, inorder, inStart, inRootIdx - 1, iMap);
        root.right = bstFromPreorderHelper(preorder, preStart + nodesLeft + 1, preEnd, inorder, inRootIdx + 1, inEnd, iMap);

        return root;
    }

}
