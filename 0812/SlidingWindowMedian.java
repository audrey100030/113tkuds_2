/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author audre
 */
public class SlidingWindowMedian {

    public static double[] medianSlidingWindow(int[] nums, int k){
        int n = nums.length;
        double[] result = new double[n - k + 1];

        for(int i = 0; i <= n - k; i++){
            int[] window = new int[k];
            for(int j = 0; j < k; j++){
                window[j] = nums[i + j];
            }
            for (int a = 0; a < k - 1; a++){
                for(int b = 0; b < k - 1 - a; b++){
                    if(window[b] > window[b + 1]){
                        int temp = window[b];
                        window[b] = window[b + 1];
                        window[b + 1] = temp;
                    }
                }
            }
            if(k % 2 == 1){
                result[i] = window[k / 2];
            }else{ 
                result[i] = (window[k / 2 - 1] + window[k / 2]) / 2.0;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 3, -1, -3, 5, 3, 6, 7};
        int k1 = 3;
        double[] res1 = medianSlidingWindow(nums1, k1);
        printArray(res1);
        int[] nums2 = {1, 2, 3, 4};
        int k2 = 2;
        double[] res2 = medianSlidingWindow(nums2, k2);
        printArray(res2);
    }

    public static void printArray(double[] arr){
        System.out.print("[");
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i]);
            if(i < arr.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}

