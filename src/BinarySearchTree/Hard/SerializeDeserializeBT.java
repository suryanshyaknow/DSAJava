package BinarySearchTree.Hard;

import BinaryTree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class SerializeDeserializeBT {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        // Using level order traversal
        if (root == null) return "";

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode node = q.poll();

            if (node == null) {
                sb.append("# ");
                continue;
            }
            // Otherwise..
            sb.append(node.val).append(" ");
            q.offer(node.left);
            q.offer(node.right);
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.length() == 0) return null;

        Queue<TreeNode> q = new LinkedList<>();
        String[] vals = data.split(" ");
        TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
        q.offer(root);

        int i = 1;
        while (!q.isEmpty() && i < vals.length) {
            TreeNode parent = q.poll();

            // Process left child if it ain't null
            if (!vals[i].equals("#")) {
                TreeNode left = new TreeNode(Integer.parseInt(vals[i]));
                parent.left = left;
                q.offer(left);
            }
            i++;

            // Process right child
            if (i < vals.length && !vals[i].equals("#")) {
                TreeNode right = new TreeNode(Integer.parseInt(vals[i]));
                parent.right = right;
                q.offer(right);
            }
            i++;
        }
        return root;
    }
}
