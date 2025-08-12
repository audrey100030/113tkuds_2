/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author audre
 */
public class MergeKSortedArrays {

    static class Element{
        int value;
        int arrayIndex;
        int elementIndex;

        public Element(int value, int arrayIndex, int elementIndex){
            this.value = value;
            this.arrayIndex = arrayIndex;
            this.elementIndex = elementIndex;
        }
    }

    static class MinHeap{
        Element[] heap;
        int size;

        public MinHeap(int capacity){
            heap = new Element[capacity];
            size = 0;
        }

        public void insert(Element e){
            if(size == heap.length) return;
            heap[size] = e;
            heapifyUp(size);
            size++;
        }

        public Element extractMin(){
            if(size == 0) return null;
            Element min = heap[0];
            heap[0] = heap[size - 1];
            size--;
            heapifyDown(0);
            return min;
        }

        private void heapifyUp(int i){
            while(i > 0) {
                int dad = (i - 1) / 2;
                if(heap[i].value < heap[dad].value) {
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
                int smallest = i;

                if(left < size && heap[left].value < heap[smallest].value) smallest = left;
                if(right < size && heap[right].value < heap[smallest].value) smallest = right;

                if(smallest != i){
                    swap(i, smallest);
                    i = smallest;
                }else{
                   break;
                }
            }
        }

        private void swap(int i, int j){
            Element temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }

        public boolean isEmpty(){
            return size == 0;
        }
    }

    public static int[] mergeKSortedArrays(int[][] arrays){
        if(arrays == null) return new int[0];

        int totalLength = 0;
        int k = arrays.length;

        for(int i = 0; i < k; i++){
            totalLength += arrays[i].length;
        }

        MinHeap heap = new MinHeap(k);

        for (int i = 0; i < k; i++){
            if(arrays[i].length > 0){
                heap.insert(new Element(arrays[i][0], i, 0));
            }
        }

        int[] result = new int[totalLength];
        int resultIndex = 0;

        while(!heap.isEmpty()){
            Element minElem = heap.extractMin();
            result[resultIndex++] = minElem.value;

            int nextElementIndex = minElem.elementIndex + 1;
            if (nextElementIndex < arrays[minElem.arrayIndex].length) {
                heap.insert(new Element(arrays[minElem.arrayIndex][nextElementIndex], minElem.arrayIndex, nextElementIndex));
            }
        }

        return result;
    }

    public static void main(String[] args){
        int[][] test1 = {{1,4,5}, {1,3,4}, {2,6}};
        int[][] test2 = {{1,2,3}, {4,5,6}, {7,8,9}};
        int[][] test3 = {{1}, {0}};

        printArray(mergeKSortedArrays(test1)); 
        printArray(mergeKSortedArrays(test2)); 
        printArray(mergeKSortedArrays(test3)); 
    }

    private static void printArray(int[] arr){
        System.out.print("[");
        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i]);
            if (i != arr.length -1) System.out.print(",");
        }
        System.out.println("]");
    }
}

