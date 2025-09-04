//題目：Next Permutation
//找到下一個字典序排列

import java.util.*;

class lt_31_NextPermutation {
    public void nextPermutation(int[] nums) {
        int n = nums.length;

        // 1. 從右往左，找到第一個下降點 i (nums[i] < nums[i+1])
        int i = n - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        if (i >= 0) {
            // 2. 從右往左，找到第一個比 nums[i] 大的數字 j
            int j = n - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            // 3. 交換 nums[i] 和 nums[j]
            swap(nums, i, j);
        }

        // 4. 反轉 i 之後的數字，使其變為最小字典序
        reverse(nums, i + 1, n - 1);
    }

    // swap 輔助函式
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // reverse 輔助函式
    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        lt_31_NextPermutation solver = new lt_31_NextPermutation();

        int[] nums1 = {1,2,3};
        solver.nextPermutation(nums1);
        System.out.println(Arrays.toString(nums1)); // [1,3,2]

        int[] nums2 = {3,2,1};
        solver.nextPermutation(nums2);
        System.out.println(Arrays.toString(nums2)); // [1,2,3]

        int[] nums3 = {1,1,5};
        solver.nextPermutation(nums3);
        System.out.println(Arrays.toString(nums3)); // [1,5,1]
    }
}
/*
解題思路：
1.從右往左找到第一個下降點 i（nums[i] < nums[i+1]）。
    如果不存在，代表陣列已經是最大排列。
2.從右往左找到第一個大於 nums[i] 的數字 j，交換 nums[i] 和 nums[j]。
3.反轉 i 後面的子陣列，使它變為最小排列。
*/