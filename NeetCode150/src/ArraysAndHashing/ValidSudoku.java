package ArraysAndHashing;

import java.util.*;

public class ValidSudoku {

    public boolean isValidSudoku(char[][] board) {

        // 9 row, 9 cols, & 9 boxes to validate

        Set<Character>[] rows = new HashSet[9]; // 9 rows
        Set<Character>[] cols = new HashSet[9]; // 9 cols
        Set<Character>[] boxes = new HashSet[9]; // 9 boxes

        for (int i=0; i < 9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }

        // Iterate and validate
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char ch = board[i][j];
                if (ch == '.') continue;

                // Check for row
                Set<Character> row = rows[i];
                if (row.contains(ch)) return false;
                else row.add(ch);

                // Check for col
                Set<Character> col = cols[j];
                if (col.contains(ch)) return false;
                else col.add(ch);

                // Check for box
                // First off compute the box
                int nr = i / 3; // normalized row
                int nc = j / 3;
                int boxIdx = nr * 3 + nc;
                Set<Character> box = boxes[boxIdx];
                if (box.contains(ch)) return false;
                else box.add(ch);
            }
        }

        return true;

        // Time: O(9^2) ~ O(1)
        // Space: O(9^2) ~ O(1)
    }

}
