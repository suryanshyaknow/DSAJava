package StringLeetCode;

public class CountAndSay {

    public String countAndSay(int n) {
        if (n == 1) return "1";

        // Look at the prev number and say it
        String prev = "1";

        for (int i = 2; i <= n; i++) {
            StringBuilder curr = new StringBuilder();
            int count = 1;

            // Process previous string
            for (int j = 1; j < prev.length(); j++) {
                if (prev.charAt(j) == prev.charAt(j - 1)) count++;
                else {
                    // New char encountered
                    curr.append(count).append(prev.charAt(j - 1));
                    count = 1;
                }
            }
            // Append the last group
            curr.append(count).append(prev.charAt(prev.length() - 1));

            prev = curr.toString();
        }
        return prev;

        // Note: The length grows roughly exponentially, though not explosively.
        // We have two main moving parts:
        // - A loop that runs n - 1 times (from 2 to n).
        // - Inside that, we process and build a new string from the previous one.

        // Time Complexity: O(2^n), because each term can be up to twice as long as the previous, and you build each from scratch.

        // At any point, the largest string held in memory is the current term being built.
        // Space Complexity: O(2^n), since the longest string you need to store is the final one, and it can be exponential in size.
    }

}
