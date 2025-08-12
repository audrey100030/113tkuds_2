/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author audre
 */
import java.util.*;

public class MovingAverageStream {
    private int size;             
    private Queue<Integer> window;   
    private double sum;              
    private List<Integer> sortedList; 

    //初始化，size 為視窗大小
    public MovingAverageStream(int size){
        this.size = size;
        this.window = new LinkedList<>();
        this.sum = 0;
        this.sortedList = new ArrayList<>();
    }

    //加入新值並返回移動平均數
    public double next(int val){
        window.offer(val);
        sum += val;
        insertSorted(val); 

        if(window.size() > size){
            int removed = window.poll();
            sum -= removed;
            removeSorted(removed);
        }
        return sum / window.size();
    }

    //返回當前視窗的中位數
    public double getMedian(){
        int n = sortedList.size();
        if(n == 0) return 0;
        if(n % 2 == 1) {
            return sortedList.get(n / 2);
        }else{
            return (sortedList.get(n / 2 - 1) + sortedList.get(n / 2)) / 2.0;
        }
    }

    //返回當前視窗的最小值
    public int getMin(){
        if(sortedList.size() == 0) return 0;
        return sortedList.get(0);
    }

    //返回當前視窗的最大值
    public int getMax(){
        if(sortedList.size() == 0) return 0;
        return sortedList.get(sortedList.size() - 1);
    }

    private void insertSorted(int val){
        int i = 0;
        while(i < sortedList.size() && sortedList.get(i) < val){
            i++;
        }
        sortedList.add(i, val);
    }

    private void removeSorted(int val){
        for(int i = 0; i < sortedList.size(); i++){
            if(sortedList.get(i) == val){
                sortedList.remove(i);
                break;
            }
        }
    }

    public static void main(String[] args) {
        MovingAverageStream ma = new MovingAverageStream(3);
        System.out.println(ma.next(1));   
        System.out.println(ma.next(10)); 
        System.out.println(ma.next(3));  
        System.out.println(ma.next(5));   
        System.out.println(ma.getMedian());
        System.out.println(ma.getMin());   
        System.out.println(ma.getMax()); 
    }
}

