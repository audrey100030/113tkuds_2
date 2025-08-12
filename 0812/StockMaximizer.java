/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author audre
 */
public class StockMaximizer {

    public static int maxProfit(int[] prices, int k) {
        int n = prices.length;
        int[] profits = new int[n];
        int count = 0;

        int i = 0;
        while (i < n - 1) {
            while (i < n - 1 && prices[i] >= prices[i + 1]) {
                i++;
            }
            int buy = i;

            while (i < n - 1 && prices[i] <= prices[i + 1]) {
                i++;
            }
            int sell = i;

            if (sell > buy) {
                profits[count++] = prices[sell] - prices[buy];
            }
        }

        buildMaxHeap(profits, count);

        int totalProfit = 0;
        for (int j = 0; j < k && count > 0; j++) {
            totalProfit += profits[0]; // 堆頂最大值
            profits[0] = profits[count - 1];
            count--;
            heapify(profits, count, 0);
        }
        return totalProfit;
    }

    private static void buildMaxHeap(int[] arr, int heapSize) {
        for (int i = heapSize / 2 - 1; i >= 0; i--) {
            heapify(arr, heapSize, i);
        }
    }

    private static void heapify(int[] arr, int heapSize, int rootIndex) {
        int largest = rootIndex;
        int left = 2 * rootIndex + 1;
        int right = 2 * rootIndex + 2;

        if (left < heapSize && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < heapSize && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != rootIndex) {
            int temp = arr[rootIndex];
            arr[rootIndex] = arr[largest];
            arr[largest] = temp;
            heapify(arr, heapSize, largest);
        }
    }

    public static void main(String[] args) {
        int[] prices1 = {2, 4, 1};
        System.out.println(maxProfit(prices1, 2));

        int[] prices2 = {3, 2, 6, 5, 0, 3};
        System.out.println(maxProfit(prices2, 2)); 

        int[] prices3 = {1, 2, 3, 4, 5};
        System.out.println(maxProfit(prices3, 2));
    }
}


