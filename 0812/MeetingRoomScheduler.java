/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author audre
 */
public class MeetingRoomScheduler {

    //第一部分
    public static int minMeetingRooms(int[][] meetings) {
        sortByStart(meetings);
        int[] heap = new int[meetings.length];
        int heapSize = 0;

        for(int[] m : meetings){
            if(heapSize > 0 && heap[0] <= m[0]){
                heap[0] = m[1];
            }else{
                heap[heapSize++] = m[1];
            }
            bubbleSort(heap, heapSize);
        }
        return heapSize;
    }

    // 第二部分：N=1 的最佳總會議時間
    public static int maxMeetingTimeOneRoom(int[][] meetings){
        sortByEnd(meetings);
        int n = meetings.length;
        int[] dp = new int[n];
        dp[0] = meetings[0][1] - meetings[0][0];
        for(int i = 1; i < n; i++){
            int incl = meetings[i][1] - meetings[i][0];
            int prevIndex = findPrev(meetings, i);
            if(prevIndex != -1){
                incl += dp[prevIndex];
            }
            dp[i] = Math.max(dp[i - 1], incl);
        }
        return dp[n - 1];
    }

    private static int findPrev(int[][] meetings, int i){
        for(int j = i - 1; j >= 0; j--){
            if(meetings[j][1] <= meetings[i][0]){
                return j;
            }
        }
        return -1;
    }

    private static void sortByStart(int[][] arr){
        for(int i = 0; i < arr.length - 1; i++){
            for(int j = 0; j < arr.length - 1 - i; j++){
                if(arr[j][0] > arr[j + 1][0]){
                    int[] temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    private static void sortByEnd(int[][] arr){
        for(int i = 0; i < arr.length - 1; i++) {
            for(int j = 0; j < arr.length - 1 - i; j++){
                if(arr[j][1] > arr[j + 1][1]){
                    int[] temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    private static void bubbleSort(int[] arr, int size){
        for(int i = 0; i < size - 1; i++){
            for(int j = 0; j < size - 1 - i; j++){
                if(arr[j] > arr[j + 1]){
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args){
        int[][] meetings1 = {{0, 30}, {5, 10}, {15, 20}};
        System.out.println(minMeetingRooms(meetings1)); 

        int[][] meetings2 = {{9, 10}, {4, 9}, {4, 17}};
        System.out.println(minMeetingRooms(meetings2));

        int[][] meetings3 = {{1, 5}, {8, 9}, {8, 9}};
        System.out.println(minMeetingRooms(meetings3));

        int[][] meetings4 = {{1, 4}, {2, 3}, {4, 6}};
        System.out.println(maxMeetingTimeOneRoom(meetings4));
    }
}

