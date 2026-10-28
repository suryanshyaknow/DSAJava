package Trees;

import java.util.*;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null)
            return null;

        // Intuition: We're gonna identify the nodes by leveraging
        // the property of preorder since every first node in it
        // is the root.
        // Then we're gonna locate that node in inorder and
        // identify the left and right nodes from inorder.
        // We're gonna do this recursively.
        //
        // To leverage the property of preorder, we're gonna
        // prep a hash map which will give us the location of
        // nodes in order.
        int N = inorder.length;
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            inMap.put(inorder[i], i);
        }

        return dfs(preorder, 0, N - 1, inorder, 0, N - 1, inMap);
    }

    private TreeNode dfs(
            int[] preorder, int preStart, int preEnd,
            int[] inorder, int inStart, int inEnd,
            Map<Integer, Integer> inMap) {
        if (preStart > preEnd || inStart > inEnd)
            return null;

        // Preorder: Root Left Right
        TreeNode root = new TreeNode(preorder[preStart]);
        int rootIdx = inMap.get(preorder[preStart]);
        int numLeftNodes = rootIdx - inStart;

        root.left = dfs(
                preorder, preStart + 1, preStart + numLeftNodes,
                inorder, inStart, rootIdx - 1,
                inMap
        );
        root.right = dfs(
                preorder, preStart + numLeftNodes + 1, preEnd,
                inorder, rootIdx + 1, inEnd,
                inMap
        );

        return root;
    }
}
