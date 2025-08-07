/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author audre
 */
public class RecursiveMathCalculator {
    public static void main(String[] args) {
        // 組合數
        int n =5, k = 2;
        System.out.println("C(" + n + ", "+ k + ") = " + combination(n, k));
        
        // 卡塔蘭數
        int c = 5;
        System.out.println("Catalan(" + c + ") = " + catalan(c));
        
        // 漢諾塔移動步數
        int h = 4;
        System.out.println("Hanoi(" + h + ") moves = " + hanoi(h));
        
        // 回文數判斷
        int num = 12321;
        System.out.println(num + " 是回文數嗎？" + (isPalindrome(num) ? "是" : "否"));
        
    }
    
    public static int combination(int n, int k){
             if(k == 0 || k == n) return 1;
             return combination(n - 1, k - 1) + combination(n - 1, k);
         }
    
    public static int catalan(int n){
        if(n == 0) return 1;
        int sum = 0;
        for(int i=0; i < n; i++){
            sum += catalan(i) * catalan(n - 1 - i);
        }
        return sum;
    }
    
    public static int hanoi(int n){
        if(n == 1) return 1;
        return 2 * hanoi(n - 1) + 1;
    }
    
    public static boolean isPalindrome(int num){
        return num == reverse(num, 0);
    }
    
    public static int reverse(int num, int rev){
        if(num == 0) return rev;
        return reverse(num / 10, rev * 10 + num % 10);
    }
}
