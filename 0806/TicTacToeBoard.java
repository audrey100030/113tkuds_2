/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author audre
 */
import java.util.Scanner;

public class TicTacToeBoard {
    public static void main(String[] args){
        int[][] board = new int[3][3];
        int currentPlayer = 1;
        boolean gameOver = false;
        Scanner s = new Scanner(System.in);
        while (!gameOver){
            printBoard(board);
            System.out.println("玩家 " + currentPlayer + " 請輸入 row（0~2）和 col（0~2）：");
            int row = s.nextInt();
            int col = s.nextInt();
            if (isValid(board, row, col)) {
                board[row][col] = currentPlayer;
                if (checkWin(board, currentPlayer)) {
                    printBoard(board);
                    System.out.println("玩家 " + currentPlayer + " 獲勝！");
                    gameOver = true;
                } else if (isDraw(board)) {
                    printBoard(board);
                    System.out.println("平手！");
                    gameOver = true;
                } else {
                    currentPlayer = (currentPlayer == 1) ? 2 : 1; // 換人
                }
            } else {
                System.out.println("位置無效，請重新輸入！");
            }
        }
        s.close();
    }
    
    public static void printBoard(int[][] board){
        System.out.println("-------------");
        for(int i = 0; i < 3; i++){
            System.out.print("| ");
            for(int j = 0; j < 3; j++){
                String mark = " ";
                if(board[i][j] == 1) mark = "X";
                else if(board[i][j] == 2) mark = "O";
                System.out.print(mark + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    public static boolean isValid(int[][] board, int row, int col){
        return row >= 0 && row < 3 &&
               col >= 0 && col < 3 &&
               board[row][col] == 0;
    }

    public static boolean checkWin(int[][] board, int player){
        for (int i = 0; i < 3; i++) {
            if(board[i][0] == player &&
                board[i][1] == player &&
                board[i][2] == player) return true;
            if (board[0][i] == player &&
                board[1][i] == player &&
                board[2][i] == player) return true;
        }
        if(board[0][0] == player &&
            board[1][1] == player &&
            board[2][2] == player) return true;
        if(board[0][2] == player &&
            board[1][1] == player &&
            board[2][0] == player) return true;
        return false;
    }

    public static boolean isDraw(int[][] board){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if (board[i][j] == 0) return false;
            }
        }
        return true;
    }
}
