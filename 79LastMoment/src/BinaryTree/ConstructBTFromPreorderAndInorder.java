package BinaryTree;

import java.util.HashMap;

public class ConstructBTFromPreorderAndInorder {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Preorder would help us in figuring out nodes for each
        // subtree, and inorder would help us localize left and
        // right subtrees.

        HashMap<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }

        return recurse(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1, inMap);

        // Time complexity: O(N), each node is processed exactly once
        // Space complexity: O(N) for hashMap + O(N) for recursive call stack
    }

    private static TreeNode recurse(int[] preorder, int preStart, int preEnd,
                                    int[] inorder, int inStart, int inEnd, HashMap<Integer, Integer> inMap) {

        // Base case
        if (preStart > preEnd || inStart > inEnd)
            return null;

        // Now determine the root node
        TreeNode root = new TreeNode(preorder[preStart]);
        // Now localize the left and right subtrees w the help of inorder
        int rootIdx = inMap.get(preorder[preStart]);
        // Figure out the number of nodes in the left subtree
        int numNodes = rootIdx - inStart;

        // Attach its left
        root.left = recurse(preorder, preStart + 1, preStart + numNodes,
                inorder, inStart, rootIdx - 1, inMap);

        // Attach its right
        root.right = recurse(preorder, preStart + numNodes + 1, preEnd,
                inorder, rootIdx + 1, inEnd, inMap);

        // return the node
        return root;
    }
}
