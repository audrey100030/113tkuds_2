/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author audre
 */
public class KthSmallestElement {

    static class MaxHeap{
        int[] heap;
        int size;

        public MaxHeap(int capacity){
            heap = new int[capacity];
            size = 0;
        }

        public void insert(int val){
            if(size == heap.length){
                return;
            }
            heap[size] = val;
            heapifyUp(size);
            size++;
        }

        public int getMax(){
            if(size == 0) return -1;
            return heap[0];
        }

        public int extractMax(){
            if(size == 0) return -1;
            int max = heap[0];
            heap[0] = heap[size - 1];
            size--;
            heapifyDown(0);
            return max;
        }

        private void heapifyUp(int i){
            while(i > 0){
                int dad = (i - 1) / 2;
                if(heap[i] > heap[dad]){
                    swap(i, dad);
                    i = dad;
                } else {
                    break;
                }
            }
        }

        private void heapifyDown(int i){
            while(true){
                int left = 2 * i + 1;
                int right = 2 * i + 2;
                int max = i;

                if(left < size && heap[left] > heap[max]) max = left;
                if(right < size && heap[right] > heap[max]) max = right;

                if(max != i){
                    swap(i, max);
                    i = max;
                }else{
                    break;
                }
            }
        }

        private void swap(int i, int j){
            int temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }
    }


    //用大小為K的Max Heap
    public static int kthSmallestUsingMaxHeap(int[] arr, int k){
        MaxHeap maxHeap = new MaxHeap(k);

        for(int val : arr){
            if (maxHeap.size < k){
                maxHeap.insert(val);
            }else if(val < maxHeap.getMax()){
                maxHeap.extractMax();
                maxHeap.insert(val);
            }
        }
        return maxHeap.getMax();
    }


    //用MinHeap提取K次
    static class MinHeap {
        int[] heap;
        int size;

        public MinHeap(int capacity){
            heap = new int[capacity];
            size = 0;
        }

        public void insert(int val){
            if(size == heap.length) return;
            heap[size] = val;
            heapifyUp(size);
            size++;
        }

        public int extractMin(){
            if (size == 0) return -1;
            int min = heap[0];
            heap[0] = heap[size - 1];
            size--;
            heapifyDown(0);
            return min;
        }

        private void heapifyUp(int i){
            while(i > 0) {
                int parent = (i - 1) / 2;
                if(heap[i] < heap[parent]){
                    swap(i, parent);
                    i = parent;
                }else{
                    break;
                }
            }
        }

        private void heapifyDown(int i){
            while(true){
                int left = 2 * i + 1;
                int right = 2 * i + 2;
                int smallest = i;

                if(left < size && heap[left] < heap[smallest]) smallest = left;
                if(right < size && heap[right] < heap[smallest]) smallest = right;

                if(smallest != i){
                    swap(i, smallest);
                    i = smallest;
                }else{
                    break;
                }
            }
        }

        private void swap(int i, int j) {
            int temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }
    }

    public static int kthSmallestUsingMinHeap(int[] arr, int k){
        MinHeap minHeap = new MinHeap(arr.length);
        for(int val : arr){
            minHeap.insert(val);
        }
        int res = -1;
        for(int i = 0; i < k; i++){
            res = minHeap.extractMin();
        }
        return res;
    }


    public static void main(String[] args){
        int[] arr1 = {7, 10, 4, 3, 20, 15};
        int k1 = 3;
        System.out.println("方法1 (MaxHeap): " + kthSmallestUsingMaxHeap(arr1, k1));
        System.out.println("方法2 (MinHeap): " + kthSmallestUsingMinHeap(arr1, k1));

        int[] arr2 = {1};
        int k2 = 1;
        System.out.println("方法1 (MaxHeap): " + kthSmallestUsingMaxHeap(arr2, k2));
        System.out.println("方法2 (MinHeap): " + kthSmallestUsingMinHeap(arr2, k2));

        int[] arr3 = {3, 1, 4, 1, 5, 9, 2, 6};
        int k3 = 4;
        System.out.println("方法1 (MaxHeap): " + kthSmallestUsingMaxHeap(arr3, k3));
        System.out.println("方法2 (MinHeap): " + kthSmallestUsingMinHeap(arr3, k3));
    }
}
