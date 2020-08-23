import java.util.Scanner;

class Sudoku {
    private int n;
    private int[][] mboard;
    Sudoku (int n, int[][] board) {
        this.n = n;
        this.mboard = board;
    }
    public static boolean isSolution(int n, int[][] board) {
        boolean is_solution = true;
        if (checkSubMatrix(n, board) && checkRowAndColumn(n, board)){
            return true;
        }
        return false;
    }
    public static boolean checkAnSubMatrix(int n, int[][] board, int x_off, int y_off) {
        int[] count = new int[n*n+1];
        int begin_i = x_off * n;
        int begin_j = y_off * n;
        for (int i = begin_i; i < begin_i + n; i++) {
            for (int j = begin_j; j < begin_j + n; j++) {
                if (count[board[i][j]] > 0) {
                    return false;
                }
                count[board[i][j]] ++;
            }
        }
        return true;
    }
    public static boolean checkAnRow(int n, int[][] board, int r) {
        int[] count = new int[n*n+1];
        for (int c = 0; c < n*n; c++) {
            if (count[board[r][c]] > 0) {
                return false;
            }
            count[board[r][c]] ++;
        }
        return true;
    }
    public static boolean checkAnColumn(int n, int[][] board, int c) {
        int[] count = new int[n*n+1];
        for (int r = 0; r < n*n; r++) {
            if (count[board[r][c]] > 0) {
                return false;
            }
            count[board[r][c]] ++;
        }
        return true;
    }
    public static boolean checkSubMatrix(int n, int[][] board) {
        for (int i = 0; i < n; i++) { 
            for (int j = 0; j < n; j++) {
                if (checkAnSubMatrix(n, board, i, j) == false) {
                    return false;
                }
            }
        }
        return true;
    }
    public static boolean checkRowAndColumn(int n, int[][] board) {
        for (int i = 0; i < n*n; i++) {
            if ((checkAnRow(n, board, i) && checkAnColumn(n, board, i)) == false) {
                return false;
            }
        }
        return true;
    }
}


class Main {
    public static void main(final String... arg) {
        int n;
        int[][] board;
        try (final Scanner cin = new Scanner(System.in)) {
            n = cin.nextInt();
            int m = n*n;
            board = new int[m][m];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < m; j++) {
                    board[i][j] = cin.nextInt();
                }
            }
        }
        if (Sudoku.isSolution(n, board)) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }
}
