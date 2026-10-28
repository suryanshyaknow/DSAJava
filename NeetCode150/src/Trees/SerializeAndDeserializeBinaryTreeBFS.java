package Trees;

import java.util.*;

public class SerializeAndDeserializeBinaryTreeBFS {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "";

        // Intuition: Traverse level order and append char
        // for each node encountered, and simply append N
        // for nulls.

        // Init config
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        StringBuilder sb = new StringBuilder(); // manges a mutable, dynamic resizing array

        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node == null) {
                sb.append("N,");
                continue;
            }
            sb.append(node.val).append(",");

            q.offer(node.left);
            q.offer(node.right);
        }

        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty())
            return null;
        String[] dataArr = data.split(",");

        // Node polling will help in determining the node at each level

        Queue<TreeNode> q = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(dataArr[0]));
        q.offer(root);
        int i = 1;

        while (!q.isEmpty()) {
            TreeNode node = q.poll();

            // Left subtree
            if (!dataArr[i].equals("N")) {
                node.left = new TreeNode(Integer.parseInt(dataArr[i]));
                q.offer(node.left);
            }
            i++;

            // Right subtree
            if (!dataArr[i].equals("N")) {
                node.right = new TreeNode(Integer.parseInt(dataArr[i]));
                q.offer(node.right);
            }
            i++;
        }

        return root;
    }
}
