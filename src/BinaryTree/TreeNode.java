package BinaryTree;

public class TreeNode {

    /*
        Binary Tree: Hierarchical DS where each node has at most two children.

        Types of Binary Trees:

        - Full Binary Tree: Given node shall either have 0 or two children.
        - Complete Binary Tree: All levels full except possibly the last, filled left to right. Emphasis on "filled left to right".
        - Perfect Binary Tree: All leaf nodes are at the same level. All internal nodes have two children.
        - Balanced Binary Tree: Height of tree is at max log N, N being no. of nodes.
        - Degenerate Binary Tree: Each node as single child, essentially a LL.

        Traversals:

        - Inorder Traversal: Left Root Right (In -> Root in mid)
        - Pre-order Traversal: Root Left Right (Pre -> Root first off)
        - Post-order Traversal: Left Right Root (Post -> Root at last)

     */

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

}
