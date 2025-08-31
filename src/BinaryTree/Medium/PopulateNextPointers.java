package BinaryTree.Medium;

public class PopulateNextPointers {

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
        Node curr = root;
        Node next = root != null ? curr.left: null;

        while (curr != null && next != null) {
            curr.left.next = curr.right;
            // Right node might also need linkage if curr.next exists
            if (curr.next != null) {
                curr.right.next = curr.next.left;
            }

            curr = curr.next;
            if (curr == null) {
                curr = next;
                next = curr.left;
            }
        }

        return root;
    }

}
