package BinaryTree;

public class DiameterOfBinaryTree {

    int dia = 0; // If you declare it static it'll track the max from prev instances

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;

        // The idea is pretty simple: Treat each node as
        // a curve point and compute the lh and the rh,
        // and their summation gives us the distance passing
        // thru that node.
        // you've actually gotta compute the left max height
        // and the right max height

        recurseAndCompute(root);
        return dia;

        // Note: Some problems are not about “computing for each node”,
        // they’re about “updating the answer while traversing once”.

        // Because approach 1 (brute force) -> Process each node starting off w root,
        // and compute the dia for each and keep updating the max.

        // Optimal (O(N) approach) -> Not about the order of execution of nodes. Just
        // keep updating max dia starting off from leaf nodes. This lets you compute the max
        // by traversing only once.
    }

    private int recurseAndCompute(TreeNode root) {
        if (root == null) return 0;

        int lh = recurseAndCompute(root.left);
        int rh = recurseAndCompute(root.right);

        int path = lh + rh;
        // Update the dia
        dia = Math.max(dia, path);

        // return the height
        return 1 + Math.max(lh, rh);
    }

}
