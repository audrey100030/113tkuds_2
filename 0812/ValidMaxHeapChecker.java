/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author audre
 */
public class ValidMaxHeapChecker {

    // 檢查是否為 Max Heap
    public static boolean isValidMaxHeap(int[] arr){
        int n = arr.length;
        if (n == 0) return true; 
        if (n == 1) return true;

        int lastdad = (n - 2) / 2; 

        for(int i = 0; i <= lastdad; i++){
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if(left < n && arr[i] < arr[left]){
                System.out.println("false (索引" + left + "的" + arr[left] + "大於父節點" + arr[i] + ")");
                return false;
            }

            if(right < n && arr[i] < arr[right]){
                System.out.println("false (索引" + right + "的" + arr[right] + "大於父節點" + arr[i] + ")");
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args){
        int[] test1 = {100, 90, 80, 70, 60, 75, 65};
        int[] test2 = {100, 90, 80, 95, 60, 75, 65};
        int[] test3 = {50};
        int[] test4 = {};

        System.out.println(isValidMaxHeap(test1));
        System.out.println(isValidMaxHeap(test2));
        System.out.println(isValidMaxHeap(test3));
        System.out.println(isValidMaxHeap(test4)); 
    }
}

