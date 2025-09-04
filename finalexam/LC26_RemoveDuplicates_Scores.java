/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author audre
 */
import java.util.*;

public class LC26_RemoveDuplicates_Scores {
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int write = 1; 
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[write - 1]) {
                nums[write] = nums[i];
                write++;
            }
        }
        return write;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int newLen = removeDuplicates(nums);
        System.out.println(newLen);
        for (int i = 0; i < newLen; i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(nums[i]);
        }
        if (newLen > 0) System.out.println();
    }
}

