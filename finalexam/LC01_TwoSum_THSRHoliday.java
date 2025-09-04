/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.*;

public class LC01_TwoSum_THSRHoliday {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int target = sc.nextInt();
        int[] seats = new int[n];
        for (int i = 0; i < n; i++) {
            seats[i] = sc.nextInt();
        }

        Map<Integer, Integer> map = new HashMap<>();
        int idx1 = -1, idx2 = -1;

        for (int i = 0; i < n; i++) {
            int x = seats[i];

            if (map.containsKey(x)) {
                idx1 = map.get(x);
                idx2 = i;
                break;
            }

            map.put(target - x, i);
        }

        System.out.println(idx1 + " " + idx2);
    }
}
