package Recursion;

public class WordSearch {

    public boolean exist(char[][] board, String word) {
        // Intuition:
        // - Traverse each cell in the grid treating it as the first letter of the given word.
        // - Now the problem boils down to:
        // "If I start from this letter, can I form the word by moving to adjacent letters one by one?"
        // - Use DFS.

        // Why it works?
        // Because each move only goes to valid neighbors, and we never reuse a cell.
        // And DFS + backtracking ensures you find a valid word if it exists.

        // Traverse the grid
        int N = board.length;
        int M = board[0].length;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (dfs(0, i, j, board, word)) return true;
            }
        }
        return false;

        // Time complexity: O(N * M * 4^L) where 4^L being branching factor in DFS for each char
        // Space complexity: O(L) max recursion depth
    }

    private boolean dfs(int idx, int row, int col, char[][] board, String word) {
        if (idx == word.length()) return true;

        int N = board.length;
        int M = board[0].length;

        // Check boundaries and current letter match
        if (row < 0 || row >= N
                || col < 0 || col >= M
                || board[row][col] != word.charAt(idx)
        ) return false;

        // Mark the cell as visited
        char temp = board[row][col];
        board[row][col] = '#';

        // Explore four directions to match the word
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] dir : dirs) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (dfs(idx + 1, newRow, newCol, board, word)) // idx + 1 cuz the curr char has already been matched
                return true;
        }

        // backtrack
        board[row][col] = temp;
        return false;

    }

}
