package BinaryTree.Hard;

import BinaryTree.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class ConstructBTFromInorderAndPreorder {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Basically, we'll recurse and kinda generate inorder
        // and preorder for each subtree

        // First off, we're gonna need the idx of root node
        // in inorder identified by preorder
        HashMap<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i); // Node: Node's idx in inorder
        }

        return construct(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1, inMap);
    }

    private static TreeNode construct(int[] preorder, int preStart, int preEnd,
                                      int[] inorder, int inStart, int inEnd, HashMap<Integer, Integer> inMap) {
        if (preStart > preEnd || inStart > inEnd) return null;

        // Create root
        TreeNode root = new TreeNode(preorder[preStart]);
        int rootIdx = inMap.get(preorder[preStart]);
        int nodesLeft = rootIdx - inStart; // Num of nodes to the left

        root.left = construct(
                preorder, preStart + 1, preStart + nodesLeft,
                inorder, inStart, rootIdx - 1, inMap
        );
        root.right = construct(
                preorder, preStart + nodesLeft + 1, preEnd,
                inorder, rootIdx + 1, inEnd, inMap
        );
        return root;
    }

}
