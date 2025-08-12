/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author audre
 */
public class PriorityQueueWithHeap {
 
    public static class Task {
        public String name;
        public int priority;
        public int timestamp;

        public Task(String name, int priority, int timestamp) {
            this.name = name;
            this.priority = priority;
            this.timestamp = timestamp;
        }
    }

    public Task[] heap;
    public int size;
    public int timeCounter;

    public PriorityQueueWithHeap(int capacity) {
        heap = new Task[capacity];
        size = 0;
        timeCounter = 0;
    }

    //添加任務
    public void addTask(String name, int priority) {
        if (size == heap.length) {
            System.out.println("Queue is full!");
            return;
        }
        heap[size] = new Task(name, priority, timeCounter++);
        heapifyUp(size);
        size++;
    }

    //執行優先級最高的任務
    public Task executeNext() {
        if (size == 0) {
            System.out.println("Queue is empty!");
            return null;
        }
        Task top = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown(0);
        return top;
    }

    //查看下一個要執行的任務
    public Task peek() {
        if (size == 0) {
            System.out.println("Queue is empty!");
            return null;
        }
        return heap[0];
    }

    //修改任務優先級
    public void changePriority(String name, int newPriority) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (heap[i].name.equals(name)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("Task not found!");
            return;
        }
        int oldPriority = heap[index].priority;
        heap[index].priority = newPriority;
        if (newPriority > oldPriority) {
            heapifyUp(index);
        } else if (newPriority < oldPriority) {
            heapifyDown(index);
        }
    }

    public void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (compare(heap[index], heap[parentIndex]) > 0) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    public void heapifyDown(int index) {
        while (true) {
            int left = index * 2 + 1;
            int right = index * 2 + 2;
            int largest = index;

            if (left < size && compare(heap[left], heap[largest]) > 0) {
                largest = left;
            }
            if (right < size && compare(heap[right], heap[largest]) > 0) {
                largest = right;
            }

            if (largest != index) {
                swap(index, largest);
                index = largest;
            } else {
                break;
            }
        }
    }

    public int compare(Task a, Task b) {
        if (a.priority != b.priority) {
            return a.priority - b.priority;
        }
        return b.timestamp - a.timestamp < 0 ? 1 : (b.timestamp == a.timestamp ? 0 : -1);
    }

    public void swap(int i, int j) {
        Task temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public static void main(String[] args) {
        PriorityQueueWithHeap pq = new PriorityQueueWithHeap(10);

        pq.addTask("備份", 1);
        pq.addTask("緊急修復", 5);
        pq.addTask("更新", 3);

        System.out.println("Next: " + pq.peek().name); 

        System.out.println("Execute: " + pq.executeNext().name); 
        System.out.println("Execute: " + pq.executeNext().name); 
        System.out.println("Execute: " + pq.executeNext().name);

        pq.addTask("掃描", 2);
        pq.addTask("清理", 4);
        pq.changePriority("掃描", 5); 
        System.out.println("Next: " + pq.peek().name); 
    }
}

