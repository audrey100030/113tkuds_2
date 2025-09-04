//題目：Valid Sudoku
//棋盤

class lt_36_ValidSudoku {
    public static void main(String[] args) {
        // 0 代表空格
        int[][] board = {
            {5,3,0,0,7,0,0,0,0},
            {6,0,0,1,9,5,0,0,0},
            {0,9,8,0,0,0,0,6,0},
            {8,0,0,0,6,0,0,0,3},
            {4,0,0,8,0,3,0,0,1},
            {7,0,0,0,2,0,0,0,6},
            {0,6,0,0,0,0,2,8,0},
            {0,0,0,4,1,9,0,0,5},
            {0,0,0,0,8,0,0,7,9}
        };

        // 印出棋盤
        System.out.println("Sudoku board:");
        printBoard(board);

        // 驗證結果
        boolean result = isValidSudoku(board);
        System.out.println("Is the Sudoku board valid? " + result);
    }

    // 驗證 Sudoku
    public static boolean isValidSudoku(int[][] board) {
        // 檢查列、行、3x3
        for (int i = 0; i < 9; i++) {
            boolean[] row = new boolean[10];
            boolean[] col = new boolean[10];
            boolean[] box = new boolean[10];

            for (int j = 0; j < 9; j++) {
                // 檢查 row
                if (board[i][j] != 0) {
                    if (row[board[i][j]]) return false;
                    row[board[i][j]] = true;
                }

                // 檢查 col
                if (board[j][i] != 0) {
                    if (col[board[j][i]]) return false;
                    col[board[j][i]] = true;
                }

                // 檢查 box
                int rowIndex = 3 * (i / 3) + j / 3;
                int colIndex = 3 * (i % 3) + j % 3;
                if (board[rowIndex][colIndex] != 0) {
                    if (box[board[rowIndex][colIndex]]) return false;
                    box[board[rowIndex][colIndex]] = true;
                }
            }
        }
        return true;
    }

    // 輔助函式：印棋盤
    public static void printBoard(int[][] board) {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("------+-------+------");
            }
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && j != 0) System.out.print("| ");
                System.out.print(board[i][j] == 0 ? ". " : board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
/*
解題思路：
1.Sudoku 規則檢查三個面向：
    每一列不能有重複數字。
    每一行不能有重複數字。
    每一個 3×3 區塊不能有重複數字。
2.遍歷整個 9×9 棋盤：
    如果當前 cell 是 '.'，略過。
    檢查該數字是否已經出現在該列、該行或該區塊。
    如果有重複 → 回傳 false。
    否則，將它標記為已出現。
3.如果整個棋盤都檢查完沒問題 → 回傳 true。
*/