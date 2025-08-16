package BinaryTree.Hard;

import BinaryTree.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class ConstructBTFromPostorderAndInorder {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> iMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            iMap.put(inorder[i], i);
        }
        TreeNode root = buildTreeHelper(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1, iMap);
        return root;
    }

    private TreeNode buildTreeHelper(int[] postorder, int postStart, int postEnd, int[] inorder, int inStart, int inEnd, Map<Integer, Integer> iMap) {
        if (postStart > postEnd || inStart > inEnd) return null;

        // Identify the root being processed
        TreeNode root = new TreeNode(postorder[postEnd]);
        int inRootIdx = iMap.get(postorder[postEnd]);
        int numsLeft = inRootIdx - inStart;

        // postEnd: postStart + numsLeft - 1
        // Because numsleft is no. of nodes, and we don't do -1, then we'd be counting the first node from right subtree as well!
        root.left = buildTreeHelper(postorder, postStart, postStart + numsLeft - 1, inorder, inStart, inRootIdx - 1, iMap);

        // postStart: postStart + no. of nodes - 1 (cuz postStart is included) + 1
        root.right = buildTreeHelper(postorder, postStart + numsLeft, postEnd - 1, inorder, inRootIdx + 1, inEnd, iMap);

        return root;
    }
}
