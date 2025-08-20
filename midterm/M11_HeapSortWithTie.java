/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.*;

public class M11_HeapSortWithTie {
    static class Student {
        int score;
        int index; 
        Student(int s, int i) {
            score = s; index = i;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Student[] arr = new Student[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Student(sc.nextInt(), i);
        }
        sc.close();

        heapSort(arr, n);

        for (int i = 0; i < n; i++) {
            System.out.print(arr[i].score);
            if (i < n - 1) System.out.print(" ");
        }
    }

    static void heapSort(Student[] arr, int n) {
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }
    }

    static void heapify(Student[] arr, int heapSize, int root) {
        int largest = root;
        int left = 2 * root + 1;
        int right = 2 * root + 2;

        if (left < heapSize && compare(arr[left], arr[largest]) > 0) {
            largest = left;
        }
        if (right < heapSize && compare(arr[right], arr[largest]) > 0) {
            largest = right;
        }

        if (largest != root) {
            swap(arr, root, largest);
            heapify(arr, heapSize, largest);
        }
    }

    static int compare(Student a, Student b) {
        if (a.score != b.score) return a.score - b.score; 
        return b.index - a.index;
    }

    static void swap(Student[] arr, int i, int j) {
        Student temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

/*
 * Time Complexity: O(n log n)
 * 說明：
 * 建堆 O(n)。
 * 每次取最大並重建堆 log n，共 n 次 → O(n log n)。
 * 整體時間複雜度 O(n log n)。
 * 
 * 11. 輸入順序
 * 28. 輸出排序結果
 * 34. Heap Sort 主程序
 * 35. 建立最大堆
 * 38. 逐步取出最大元素放到最後
 * 44. 維護堆
 * 62. 比較函式：分數優先，大的排前；若相同，index 小的排前
 * 63. 分數小的應排前，為了 max-heap 反向
 * 64. index 小的優先 → 所以 b.index 大的當作「小」
 * 67. 交換
 */
