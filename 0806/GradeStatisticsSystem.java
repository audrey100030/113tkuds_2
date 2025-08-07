/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author audre
 */
public class GradeStatisticsSystem {
    public static void main(String[] args) {
        int[] grade = {85, 92, 78, 96, 87, 73, 89, 94, 82, 90};
        
        //平均值
        int sum = 0;
        for(int i=0; i < grade.length; i++){
            sum += grade[i];
        }
        double average = (double)sum / grade.length;
        
        //統計各等
        int A = 0, B = 0, C = 0, D = 0, F = 0;
        for(int i=0; i < grade.length; i++){
            if(grade[i] >= 90){
                A++;
            }else if(grade[i] < 90 && grade[i] >= 80){
                B++;
            }else if(grade[i] < 80 && grade[i] >= 70){
                C++;
            }else if(grade[i] < 70 && grade[i] >= 60){
                D++;
            }else{
                F++;
            }
        }
        
        //最高分
        int max = grade[0];
        for(int i=1; i < grade.length; i++){
            if(grade[i] > max){
                max = grade[i];
            }
        }
        
        //最低分
        int min = grade[0];
        for(int i=1; i<grade.length ; i++){
            if(grade[i] < min){
                min = grade[i];
            }
        }
        
        //高於平均分的學生人數
        int overaverage = 0;
        for(int i=0; i<grade.length ; i++){
            if(grade[i] > average){
                overaverage++;
            }
        }
        
        //列印完整的成績報表
        System.out.println("---成績報表---");
        System.out.print("成績：");
        for(int i=1; i<grade.length ; i++){
            System.out.print(grade[i]+" ");
        }
        System.out.printf("平均值：%.2f\n", average);
        System.out.println("最高分："+ max);
        System.out.println("最低分："+ min);
        System.out.println("高於平均的人數："+ overaverage);
        System.out.println("等第統計：");
        System.out.println("A："+A);
        System.out.println("B："+B);
        System.out.println("C："+C);
        System.out.println("D："+D);
         System.out.println("F："+F);
    }
}
