/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.*;

public class M02_YouBikeNextArrival {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        sc.nextLine();
        int[] times = new int[n];
        
        for (int i = 0; i < n; i++) {
            String t = sc.nextLine();
            times[i] = toMinutes(t);
        }
        
        String queryStr = sc.nextLine();
        int query = toMinutes(queryStr);
        
        int ansIndex = binarySearch(times, query);
        
        if (ansIndex == -1) {
            System.out.println("No bike");
        } else {
            System.out.println(toHHMM(times[ansIndex]));
        }
    }
    
    static int toMinutes(String t) {
        String[] parts = t.split(":");
        int hh = Integer.parseInt(parts[0]);
        int mm = Integer.parseInt(parts[1]);
        return hh * 60 + mm;
    }
    
    static String toHHMM(int minutes) {
        int hh = minutes / 60;
        int mm = minutes % 60;
        return String.format("%02d:%02d", hh, mm);
    }
    
    static int binarySearch(int[] arr, int query) {
        int left = 0, right = arr.length - 1;
        int ans = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] > query) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
}

