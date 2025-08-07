/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author audre
 */
public class RecursionVsIteration {
    public static void main(String[] args) {
        //計算二項式係數
        int n = 10, k = 5;
        long start = System.nanoTime();
        int binomialRec = binomialRecursive(n, k);
        long end = System.nanoTime();
        System.out.println("遞迴 C(" + n + "," + k + ") = " + binomialRec + "，耗時：" + (end - start) + " ns");
        start = System.nanoTime();
        int binomialIter = binomialIterative(n, k);
        end = System.nanoTime();
        System.out.println("迭代 C(" + n + "," + k + ") = " + binomialIter + "，耗時：" + (end - start) + " ns");
        
        //陣列乘積
        int[] array = {2, 3, 4, 5};
        start = System.nanoTime();
        int productRec = arrayProductRecursive(array, 0);
        end = System.nanoTime();
        System.out.println("遞迴乘積 = " + productRec + "，耗時：" + (end - start) + " ns");
        start = System.nanoTime();
        int productIter = arrayProductIterative(array);
        end = System.nanoTime();
        System.out.println("迭代乘積 = " + productIter + "，耗時：" + (end - start) + " ns");
        
        //字串中元音數量
        char[] text = "education".toCharArray();
        start = System.nanoTime();
        int vowelRec = countVowelsRecursive(text, 0);
        end = System.nanoTime();
        System.out.println("遞迴元音數 = " + vowelRec + "，耗時：" + (end - start) + " ns");
        start = System.nanoTime();
        int vowelIter = countVowelsIterative(text);
        end = System.nanoTime();
        System.out.println("迭代元音數 = " + vowelIter + "，耗時：" + (end - start) + " ns");

        
        //檢查括號配對
        char[] expr = {'(', '(', ')', '(', ')', ')'};
        start = System.nanoTime();
        boolean validRec = isValidParenthesesRecursive(expr, 0, 0);
        end = System.nanoTime();
        System.out.println("遞迴括號配對正確？" + validRec + "，耗時：" + (end - start) + " ns");

        start = System.nanoTime();
        boolean validIter = isValidParenthesesIterative(expr);
        end = System.nanoTime();
        System.out.println("迭代括號配對正確？" + validIter + "，耗時：" + (end - start) + " ns");
    }
    
    public static int binomialRecursive(int n, int k){
        if(k == 0 || k == n) return 1;
        return binomialRecursive(n - 1, k - 1) + binomialRecursive(n - 1, k);
    }

    public static int binomialIterative(int n, int k){
        int[][] dp = new int[n + 1][k + 1];
        for(int i = 0; i <= n; i++){
            int min = Math.min(i, k);
            for(int j = 0; j <= min; j++){
                if(j == 0 || j == i) dp[i][j] = 1;
                else dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }
        return dp[n][k];
    }
    
    public static int arrayProductRecursive(int[] arr, int index) {
        if(index == arr.length) return 1;
        return arr[index] * arrayProductRecursive(arr, index + 1);
    }

    public static int arrayProductIterative(int[] arr){
        int result = 1;
        for(int i = 0; i < arr.length; i++) {
            result *= arr[i];
        }
        return result;
    }
    
    public static boolean isVowel(char c){
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    public static int countVowelsRecursive(char[] arr, int index){
        if(index == arr.length) return 0;
        return (isVowel(arr[index]) ? 1 : 0) + countVowelsRecursive(arr, index + 1);
    }

    public static int countVowelsIterative(char[] arr){
        int count = 0;
        for(int i = 0; i < arr.length; i++){
            if(isVowel(arr[i])) count++;
        }
        return count;
    }
    
     public static boolean isValidParenthesesRecursive(char[] arr, int index, int count){
        if(count < 0) return false;
        if(index == arr.length) return count == 0;
        if(arr[index] == '(') return isValidParenthesesRecursive(arr, index + 1, count + 1);
        else if(arr[index] == ')') return isValidParenthesesRecursive(arr, index + 1, count - 1);
        return isValidParenthesesRecursive(arr, index + 1, count);
    }

    public static boolean isValidParenthesesIterative(char[] arr){
        int count = 0;
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == '(') count++;
            else if(arr[i] == ')') count--;
            if(count < 0) return false;
        }
        return count == 0;
    }
}
