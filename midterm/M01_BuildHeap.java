/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.*;

public class M01_BuildHeap {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String type = sc.next();   
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        
        buildHeap(arr, type);
        
        for (int i = 0; i < n; i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(arr[i]);
        }
    }
    
    static void buildHeap(int[] arr, String type) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i, type);
        }
    }
    
    static void heapify(int[] arr, int n, int i, String type) {
        int extreme = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        
        if (type.equals("max")) {
            if (left < n && arr[left] > arr[extreme]) extreme = left;
            if (right < n && arr[right] > arr[extreme]) extreme = right;
        } else {
            if (left < n && arr[left] < arr[extreme]) extreme = left;
            if (right < n && arr[right] < arr[extreme]) extreme = right;
        }
        
        if (extreme != i) {
            int temp = arr[i];
            arr[i] = arr[extreme];
            arr[extreme] = temp;
            
            heapify(arr, n, extreme, type);
        }
    }
}

/*
 * Time Complexity: O(n)
 * 說明：
 * Heapify 單次最壞 O(log n)。
 * 自底向上建堆，節點數多的層數很淺，節點數少的層數很深。
 * 加總成本 < 2n → O(n)。
 * 
 * 18. 建堆
 * 20. 輸出
 * 26. 自底向上建堆
 * 29. 從最後一個非葉節點開始
 * 34. Heapify 下沉
 * 35. max: 最大值索引; min: 最小值索引
 * 42. min heap
 * 47. 如果需要交換
 * 52. 遞迴處理
 */

