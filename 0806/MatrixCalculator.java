/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author audre
 */
public class MatrixCalculator {
     public static void main(String[] args) {
        int[][] one = {{1, 2, 3},{4, 5, 6}};
        int[][] two = {{9, 8, 7},{6, 5, 4}};
        
        //加法
        System.out.println("加法：");
        int[][] sum = new int[one.length][one[0].length];
        for(int i=0; i<one.length; i++){
            for(int j=0; j<one[0].length; j++){
                sum[i][j] = one[i][j] + two[i][j];
                System.out.print(sum[i][j]+ "\t");
            }
            System.out.println();
        }
        
        //乘法
        System.out.println("乘法：");
        int[][] twoT = transpose(two);
        int[][] res = new int[one.length][twoT[0].length];
        for(int i=0; i<one.length; i++){
            for(int j=0; j<twoT[0].length; j++){
                for(int k=0; k<one[0].length; k++){
                    res[i][j] += one[i][k] * twoT[k][j];
                }
                System.out.print(res[i][j]+"\t");
            }
            System.out.println();
        }
        
        //轉置
        System.out.println("\none 的轉置");
        int[][] oneT = transpose(one);
        for(int i=0; i<oneT.length; i++){
            for(int j=0; j<oneT[0].length; j++){
                System.out.print(oneT[i][j]+"\t");
            }
            System.out.println();
        }
        
        //找最大和最小值
        int[][] three = {{12, 41, 6},{78, 14, 7}};
        int max = three[0][0];
        int min = three[0][0];
        for(int i=0; i<three.length; i++){
            for(int j=0; j<three[i].length; j++){
                if(max < three[i][j]){
                    max = three[i][j];
                }
            }
        }
        for(int i=0; i<three.length; i++){
            for(int j=0; j<three[i].length; j++){
                if(min > three[i][j]){
                    min = three[i][j];
                }
            }
        }
        System.out.println("Max："+ max);
        System.out.println("min："+ min);
    }
     
     public static int[][] transpose(int[][] matrix){
         int rows = matrix.length;
         int cols = matrix[0].length;
         int[][] ans = new int[cols][rows];
         for(int i=0; i<rows; i++){
             for(int j=0; j<cols; j++){
                 ans[j][i] = matrix[i][j];
             }
         }
         return ans;
     }
}
