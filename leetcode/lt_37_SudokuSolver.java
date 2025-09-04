//題目：Sudoku Solver
//解數獨

public class lt_37_SudokuSolver {
    public static void main(String[] args) {
        char[][] board = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };

        System.out.println("Original Sudoku:");
        printBoard(board);

        solveSudoku(board);

        System.out.println("\nSolved Sudoku:");
        printBoard(board);
    }

    // 解數獨
    public static void solveSudoku(char[][] board) {
        solve(board);
    }

    private static boolean solve(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c;
                            if (solve(board)) {
                                return true;
                            }
                            board[i][j] = '.'; // 回溯
                        }
                    }
                    return false; // 該位置沒有可行數字
                }
            }
        }
        return true; // 已填滿
    }

    // 檢查是否合法
    private static boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            // 檢查 row
            if (board[row][i] == c) return false;
            // 檢查 col
            if (board[i][col] == c) return false;
            // 檢查 box
            int boxRow = 3 * (row / 3) + i / 3;
            int boxCol = 3 * (col / 3) + i % 3;
            if (board[boxRow][boxCol] == c) return false;
        }
        return true;
    }

    // 輔助：印棋盤
    private static void printBoard(char[][] board) {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("------+-------+------");
            }
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && j != 0) System.out.print("| ");
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
/*
解題思路：
1.遍歷整個棋盤，找到一個空格（'.'）。
2.嘗試填入 1 ~ 9：
    檢查當前數字在 row、col、box 是否可行。
3.如果可行，先填上去，再遞迴呼叫繼續解剩下的。
4.如果後面解不出來，就回溯，把該格恢復成 '.'。
5.當所有格子都填滿 → 解答完成。
*/
