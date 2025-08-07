/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author audre
 */
public class SelectionSortImplementation {
    public static void main(String[] args) {
        int[] one = {29, 10, 14, 37, 13}; 
        int[] two = one.clone();

        System.out.println("選擇排序過程：");
        selectionSort(one);

        System.out.println("\n氣泡排序過程：");
        bubbleSort(two);
    }
    
    // 選擇排序
    public static void selectionSort(int[] arr){
        int compareCount = 0;
        int swapCount = 0;
        for(int i = 0; i < arr.length - 1; i++){
            int minIndex = i;
            for(int j = i + 1; j < arr.length; j++){
                compareCount++;
                if (arr[j] < arr[minIndex]){
                    minIndex = j;
                }
            }
            if(minIndex != i){
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
                swapCount++;
            }
            System.out.print("第 " + (i + 1) + " 輪: ");
            print(arr);
        }
        System.out.println("總比較次數: " + compareCount);
        System.out.println("總交換次數: " + swapCount);
    }
    
    // 氣泡排序
    public static void bubbleSort(int[] arr){
        int compareCount = 0;
        int swapCount = 0;
        for(int i = 0; i < arr.length - 1; i++){
            for(int j = 0; j < arr.length - 1 - i; j++){
                compareCount++;
                if(arr[j] > arr[j + 1]){
                    // 交換
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapCount++;
                }
            }
            System.out.print("第 " + (i + 1) + " 輪: ");
            print(arr);
        }
        System.out.println("總比較次數: " + compareCount);
        System.out.println("總交換次數: " + swapCount);
    }
    
    // 印出陣列
    public static void print(int[] arr){
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
