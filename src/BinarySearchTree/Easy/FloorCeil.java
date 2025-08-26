package BinarySearchTree.Easy;

import BinaryTree.TreeNode;

public class FloorCeil {

    int findCeil(TreeNode root, int key) {
        // code here
        int res = -1;

        while (root != null ) {
            if (root.val == key) {
                return root.val;
            } else if (root.val > key) {
                res = root.val;
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return res;
    }

    public static int floor(TreeNode root, int x) {
        // Code here
        int floor = -1;

        while (root != null) {
            if (root.val == x) return x;
            else if (root.val < x) {
                floor = root.val;
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return floor;
    }

}
