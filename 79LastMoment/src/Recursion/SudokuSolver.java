package Recursion;

public class SudokuSolver {

    public void solveSudoku(char[][] board) {

        // Intuition is pretty simple
        // Traverse the matrix, find out the empty cells,
        // try each value from 1 to 9, and place based
        // on the sudoku rules.
        // Do that recursively.

        solve(board);

        // It’s an exponential-time backtracking solution.
        // Worst-case O(9^81), but heavy constraint pruning makes it fast in practice.

        // Space is O(1) since the board is modified in-place with only recursion stack usage.
        // Max possible depth: The recursion depth is atmost the number of empty cells, which is bounded by 81 for a 9x9 sudoku.
        // Sudoku grid = 9 × 9 = 81 cells
        // Worst case: all empty
    }

    private boolean solve(char[][] board) {
        int N = board.length;
        int M = board[0].length;

        // Traverse the matrix
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] != '.') continue;

                // Empty cell found, try all possible chars
                for (char c = '1'; c <= '9'; c++) {
                    if (isValid(i, j, c, board)) {

                        board[i][j] = c;
                        boolean isSolved = solve(board);
                        if (isSolved) return true;

                        // If not solved, then back track
                        board[i][j] = '.';
                    }
                }
                return false; // Tried all numbers from 1-9, no valid number found
            }
        }

        return true; // No empty cells, flow never entered the loop
    }

    private boolean isValid(int row, int col, char c, char[][] board) {
        // Check for current row and current col
        for (int i = 0; i < 9; i++) {

            if (board[row][i] == c) return false;
            if (board[i][col] == c) return false;
        }

        // Check for the 3*3 sub-matrix
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;

        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == c) return false;
            }
        }

        return true;
    }

}
