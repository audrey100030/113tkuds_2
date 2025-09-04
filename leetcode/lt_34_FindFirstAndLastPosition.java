//題目：Find First and Last Position of Element in Sorted Array
//找到 target 的第一個與最後一個位置。

import java.util.*;

class lt_34_FindFirstAndLastPosition {
    public int[] searchRange(int[] nums, int target) {
        int first = findBound(nums, target, true);   // 找左邊界
        int last = findBound(nums, target, false);   // 找右邊界
        return new int[]{first, last};
    }

    // 輔助函式：二分搜尋，找左右邊界
    private int findBound(int[] nums, int target, boolean isFirst) {
        int left = 0, right = nums.length - 1;
        int bound = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                bound = mid; 
                if (isFirst) {
                    right = mid - 1; // 繼續往左找
                } else {
                    left = mid + 1;  // 繼續往右找
                }
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return bound;
    }

    public static void main(String[] args) {
        lt_34_FindFirstAndLastPosition solver = new lt_34_FindFirstAndLastPosition();
        System.out.println(Arrays.toString(solver.searchRange(new int[]{5,7,7,8,8,10}, 8))); // [3,4]
        System.out.println(Arrays.toString(solver.searchRange(new int[]{5,7,7,8,8,10}, 6))); // [-1,-1]
        System.out.println(Arrays.toString(solver.searchRange(new int[]{}, 0)));             // [-1,-1]
    }
}
/*
解題思路：
1.用一次二分搜尋找 左邊界（當找到 target 時，繼續往左搜尋）。
2.用一次二分搜尋找 右邊界（當找到 target 時，繼續往右搜尋）。
3.如果找不到，回傳 -1。
*/