/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author audre
 */
public class BasicMinHeapPractice {
    private int[] heap;
    private int size; 

    public BasicMinHeapPractice(int capacity) {
        heap = new int[capacity];
        size = 0;
    }

    // 插入元素
    public void insert(int val){
        if(size == heap.length){
            System.out.println("堆疊滿了");
            return;
        }
        heap[size] = val; 
        heapifyUp(size);
        size++;
    }

    // 取出並返回最小值
    public int extractMin(){
        if(size == 0){
            System.out.println("堆疊是空的");
            return -1;
        }
        int min = heap[0];
        heap[0] = heap[size - 1]; 
        size--;
        heapifyDown(0);
        return min;
    }

    // 查看最小值但不移除
    public int getMin(){
        if(size == 0){
            System.out.println("堆疊是空的");
            return -1;
        }
        return heap[0];
    }

    // 返回大小
    public int size(){
        return size;
    }

    // 檢查是否為空
    public boolean isEmpty(){
        return size == 0;
    }

    private void heapifyUp(int index){
        while(index > 0){
            int dad = (index - 1) / 2;
            if(heap[index] < heap[dad]){
                swap(index, dad);
                index = dad;
            } else {
                break;
            }
        }
    }

    private void heapifyDown(int index){
        while(true){
            int left = index * 2 + 1;
            int right = index * 2 + 2;
            int min = index;

            if(left < size && heap[left] < heap[min]){
                min = left;
            }
            if(right < size && heap[right] < heap[min]){
                min = right;
            }

            if(min != index){
                swap(index, min);
                index = min;
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

    // 測試
    public static void main(String[] args){
        BasicMinHeapPractice minHeap = new BasicMinHeapPractice(10);

        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(8);
        minHeap.insert(1);

        System.out.println("Min: " + minHeap.getMin());
        System.out.println("Extracted: " + minHeap.extractMin());
        System.out.println("New Min: " + minHeap.getMin());
        System.out.println("Size: " + minHeap.size());
        System.out.println("Is Empty: " + minHeap.isEmpty());
    }
}
