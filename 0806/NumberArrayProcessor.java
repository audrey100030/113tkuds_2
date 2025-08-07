/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author audre
 */
public class NumberArrayProcessor {
    public static void main(String[] args){
        //移除重複元素
        int[] one = {1, 8, 9, 7, 4, 8};
        System.out.println("移除重複元素：");
        int[] newone = remove(one);
        printArray(newone);
        
        //合併已排序的陣列
        int[] two = {1, 4, 9, 15, 19, 78};
        int[] three = {5, 16, 25, 33, 59, 67};
        System.out.println("合併已排序陣列：");
        int[] plus = mergeSorted(two, three);
        printArray(plus);
        
        //找出出現率最高的元素
        int[] four = {7, 9, 6, 4, 7, 2};
        System.out.println("出現最多次的元素：");
        int mode = most(four);
        System.out.println(mode);
        
        //分成兩個相等（或近似相等）的子陣列
        int[] five = {1, 2, 3, 4, 9};
        System.out.println("分割為兩組總和相近的子陣列：");
        split(five);
    }
    
    public static int[] remove(int[] arr){
        int[] temp = new int[arr.length];
        int count = 0;
        for (int i = 0; i < arr.length; i++){
            boolean found = false;
            for (int j = 0; j < count; j++){
                if (arr[i] == temp[j]){
                    found = true;
                    break;
                }
            }
            if (!found){
                temp[count] = arr[i];
                count++;
            }
        }
        int[] result = new int[count];
        for (int i = 0; i < count; i++){
            result[i] = temp[i];
        }
        return result;
    }
    
    public static void printArray(int[] arr){
        for (int n : arr){
            System.out.print(n + " ");
        }
        System.out.println();
    }
    
    public static int[] mergeSorted(int[] a, int[] b){
        int[] res = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;
        while (i < a.length && j < b.length){
            if (a[i] < b[j]) {
                res[k++] = a[i++];
            } else {
                res[k++] = b[j++];
            }
        }
        while (i < a.length) res[k++] = a[i++];
        while (j < b.length) res[k++] = b[j++];
        return res;
    }
    
    public static int most(int[] arr){
        int maxCount = 0;
        int res = arr[0];
        for (int i = 0; i < arr.length; i++){
            int count = 0;
            for (int j = 0; j < arr.length; j++){
                if (arr[i] == arr[j]) {
                    count++;
                }
            }
            if (count > maxCount){
                maxCount = count;
                res = arr[i];
            }
        }
        return res;
    }
    
    public static void split(int[] arr){
        for (int i = 0; i < arr.length - 1; i++){
            for (int j = i + 1; j < arr.length; j++){
                if (arr[i] < arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        int[] group1 = new int[arr.length];
        int[] group2 = new int[arr.length];
        int sum1 = 0, sum2 = 0;
        int g1 = 0, g2 = 0;
        for (int i = 0; i < arr.length; i++) {
            if (sum1 <= sum2) {
                group1[g1++] = arr[i];
                sum1 += arr[i];
            } else {
                group2[g2++] = arr[i];
                sum2 += arr[i];
            }
        }
        System.out.print("子陣列1: ");
        for (int i = 0; i < g1; i++){
            System.out.print(group1[i] + " ");
        }
        System.out.println(" 總和: " + sum1);
        System.out.print("子陣列2: ");
        for (int i = 0; i < g2; i++) {
            System.out.print(group2[i] + " ");
        }
        System.out.println(" 總和: " + sum2);
    }
}
