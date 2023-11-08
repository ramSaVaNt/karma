package DAALab;

import java.util.ArrayList;
import java.util.List;

public class nqueen {

    public static void main(String[] args) {
        int n = 8; // Change n to the desired board size
        int[][] board = new int[n][n];
        List<int[][]> solutions = new ArrayList<>();

        // Place the first Queen (you can change the position as needed)
        int rowOfFirstQueen = 0;
        int colOfFirstQueen = 0;
        board[rowOfFirstQueen][colOfFirstQueen] = 1;

        solveNQueens(board, 1, n, solutions);

        if (solutions.isEmpty()) {
            System.out.println("No solutions found.");
        } else {
            for (int[][] solution : solutions) {
                printBoard(solution);
                System.out.println();
            }
        }
    }

    public static void solveNQueens(int[][] board, int col, int n, List<int[][]> solutions) {
        if (col == n) {
            solutions.add(copyBoard(board));
            return;
        }

        for (int row = 0; row < n; row++) {
            if (isSafe(board, row, col, n)) {
                board[row][col] = 1;
                solveNQueens(board, col + 1, n, solutions);
                board[row][col] = 0; // Backtrack
            }
        }
    }

    public static boolean isSafe(int[][] board, int row, int col, int n) {
        // Check if there's a Queen in the same row to the left
        for (int i = 0; i < col; i++) {
            if (board[row][i] == 1) {
                return false;
            }
        }

        // Check upper diagonal on the left side
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        // Check lower diagonal on the left side
        for (int i = row, j = col; i < n && j >= 0; i++, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        return true;
    }

    public static int[][] copyBoard(int[][] board) {
        int n = board.length;
        int[][] copy = new int[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(board[i], 0, copy[i], 0, n);
        }
        return copy;
    }

    public static void printBoard(int[][] board) {
        for (int[] row : board) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}
