package LinkedList;

public class FindTheDuplicateNumber {

    public int findDuplicate(int[] nums) {
        // Intuition: Treat the idxes as nodes and the values
        // they represent as their pointers. And try to find the
        // start of the cycle. Cuz the start of the cycle is the node
        // where multiple nodes point to.
        //
        // Implement the floyd's cycle detection algo to find the
        // start of the cycle i.e. the duplicate value.

        // Keep two pointers -- slow and fast and move them till
        // they meet each other, and reset the fast pointer to the
        // start of LL. And then move them together till the both
        // meet at one node.
        int slow = 0;
        int fast = 0;
        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];

            if (slow == fast) break;
        }

        // Reset the fast or introduce another slow
        int slow2 = 0;
        while (slow != slow2) {
            slow = nums[slow];
            slow2 = nums[slow2];
        }

        return slow;

        // Time: O(N)
    }
}
