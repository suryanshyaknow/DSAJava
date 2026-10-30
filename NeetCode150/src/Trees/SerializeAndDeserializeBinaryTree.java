package Trees;

public class SerializeAndDeserializeBinaryTree {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "";

        // Intuition: Construct the string using preorder.
        // And use N for nulls. Use "," as separator
        StringBuilder sb = new StringBuilder();
        dfs(root, sb);
        return sb.toString();

        // Time: O(N)
    }

    private void dfs(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("N,");
            return;
        }

        sb.append(root.val).append(",");
        dfs(root.left, sb);
        dfs(root.right, sb);
    }

    private int i; // pointer to traverse thru the arr

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        // Mind that serialize gives back "" for an empty tree, and
        // "".split(",") hands us [""] -- not an empty array. So guard
        // here, else parseInt("") blows up down in the dfs.
        if (data.isEmpty()) return null;

        String[] dataArr = data.split(",");

        i = 0;
        return deserializeDfs(dataArr);

        // Time: O(N)
    }

    private TreeNode deserializeDfs(String[] dataArr) {
        // Don't advance i in the check itself -- we still need to read
        // the very same token below to build the node off of.
        if (dataArr[i].equals("N")) {
            i++;
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(dataArr[i]));
        i++;

        root.left = deserializeDfs(dataArr);
        root.right = deserializeDfs(dataArr);
        return root;
    }
}
