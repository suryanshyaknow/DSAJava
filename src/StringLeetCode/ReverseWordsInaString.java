package StringLeetCode;

public class ReverseWordsInaString {

    public String reverseWords(String s) {
        // Trim leading and trailing white spaces and split by one or more spaces
        String[] words = s.trim().split("\\s+"); // O(N) (for trimming) + O(N) (split words based on regex)

        // Reverse the array
        StringBuilder sb = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i]);
            if (i != 0) sb.append(" ");
        } // O(N) for all appends

        return sb.toString();

        // Space Complexity: O(2N) -> for words[] and StringBuilder.
        // Time Complexity: O(3N)
        // Only way to do better space-wise would be to reverse in-place on a char array
        // — but since Java strings are immutable, this approach is as efficient as it gets.
    }

}
