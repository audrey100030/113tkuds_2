/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author audre
 */
public class AdvancedArrayRecursion {
    public static void main(String[] args) {
        //快速排序
        int arr[] = {7, 2, 1, 8, 5, 3};
        quickSort(arr, 0, arr.length - 1);
        System.out.print("快速排序後：");
        printArray(arr);
        
        //合併已排序陣列
        int[] a = {1, 4, 7};
        int[] b = {2, 3, 6, 8};
        int[] merged = mergeSortedArrays(a, 0, b, 0, new int[a.length + b.length], 0);
        System.out.print("合併結果：");
        printArray(merged);
        
        //第 k 小元素
        int[] array = {10, 4, 5, 8, 6, 11, 26};
        int k = 3;
        int kth = quickSelect(array, 0, array.length - 1, k);
        System.out.println("第 " + k + " 小的元素是：" + kth);
        
        //子集合總和是否等於目標
        int[] nums = {2, 4, 8};
        int target = 6;
        boolean result = subsetSum(nums, 0, target);
        System.out.println("是否存在子集合總和為 " + target + "？" + (result ? "是" : "否"));
    }
    
    public static void quickSort(int[] arr, int left, int right){
        if(left >= right) return;
        int num = arr[right];
        int index = partition(arr, left, right, num);
        quickSort(arr, left, index - 1);
        quickSort(arr, index + 1, right);
    }
    
    private static int partition(int[] arr, int left, int right, int num){
        int i = left;
        for(int j =left; j < right; j++){
            if(arr[j] < num){
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, right);
        return i;
    }
    
    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    public static void printArray(int[] arr){
        for(int num : arr){
           System.out.print(num + " "); 
        }
        System.out.println();
    }
    
    public static int[] mergeSortedArrays(int[] a, int i, int[] b, int j, int[] result, int k){
        if(i == a.length){
            while(j < b.length) result[k++] = b[j++];
            return result;
        }
        if(j == b.length){
            while(i < a.length) result[k++] = a[i++];
            return result;
        }
        if(a[i] < b[j]){
            result[k] = a[i];
            return mergeSortedArrays(a, i + 1, b, j, result, k + 1);
        }else{
            result[k] = b[j];
            return mergeSortedArrays(a, i, b, j + 1, result, k + 1);
        }
    }
    
    public static int quickSelect(int[] arr, int left, int right, int k){
        if(left == right) return arr[left];
        int index = partition(arr, left, right, arr[right]);
        int rank = index - left + 1;
        if(k == rank) return arr[index];
        else if(k < rank) return quickSelect(arr, left, index - 1, k);
        else return quickSelect(arr, index + 1, right, k - rank);
    }
    
    public static boolean subsetSum(int[] arr, int index, int target){
        if(target == 0) return true;
        if(index == arr.length) return false;
        return subsetSum(arr, index + 1, target - arr[index]) || subsetSum(arr, index + 1, target);
    }
}
