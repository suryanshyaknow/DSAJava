package Recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueens {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        char[][] board = new char[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }

        // Rules of thumb:
        // - Each column should have a queen,
        // - Each row should have a queen,
        // - and a given queen must not be in attacking range of another.

        // Tryna iterate over columns,
        // and then deciding at which row Q is to be placed
        solve(0, n, board, res);
        return res;

        // Time complexity: O(N * N!) = O(N!) ~ Column by column and checking for rows plus 'isSafe()' checks.
        // Space complexity: O(N^2) ~ board storage plus max depth of N (recursion stack).
    }

    private void solve(int col, int n, char[][] board, List<List<String>> res) {
        if (col == n) {
            res.add(constructBoard(board));
            return;
        }

        // Iterate over each row and see if it's safe to place the queen
        for (int i = 0; i < n; i++) {
            if (isSafe(board, col, i)) {
                board[i][col] = 'Q';
                solve(col + 1, n, board, res);
                board[i][col] = '.'; // Backtrack step: while moving up the recursive call
            }
        }
    }

    private boolean isSafe(char[][] board, int col, int row) {
        // Ideally we would check for all 8 directions but think about it,
        // since we're moving from left to right, we just oughta take care of
        // the left: left upward diagonal, left-column, and left lower diagonal.
        // We don't even gotta check the same col cuz we're allowed to place
        // only Q per col.

        int n = board.length;
        // left upward diagonal
        int r = row;
        int c = col;
        while (r >= 0 && c >= 0) {
            if (board[r][c] == 'Q') return false;
            r--;
            c--;
        }

        // left column
        c = col;
        r = row;
        while (c >= 0) {
            if (board[r][c] == 'Q') return false;
            c--;
        }

        // left downward column
        c = col;
        r = row;
        while (c >= 0 && r < n) {
            if (board[r][c] == 'Q') return false;
            r++;
            c--;
        }

        return true;
    }

    private List<String> constructBoard(char[][] board) {
        List<String> ans = new ArrayList<>();
        for (char[] row : board) {
            ans.add(new String(row));
        }
        return ans;
    }
}
