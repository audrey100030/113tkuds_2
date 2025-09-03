//題目：3Sum
//找出所有三元組 (i, j, k)，使 nums[i] + nums[j] + nums[k] == 0。解答不可有重複的組合。

import java.util.*;

class lt_15_ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); // 先排序，方便處理重複與雙指針

        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            // 避免第一個數字重複
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    // 找到一組解
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // 跳過重複的 left
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    // 跳過重複的 right
                    while (left < right && nums[right] == nums[right - 1]) right--;

                    // 繼續移動指針
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++; // 總和太小 → 左指針右移
                } else {
                    right--; // 總和太大 → 右指針左移
                }
            }
        }

        return result;
    }

    // 測試
    public static void main(String[] args) {
        lt_15_ThreeSum solver = new lt_15_ThreeSum();
        System.out.println(solver.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
        // 輸出 [[-1, -1, 2], [-1, 0, 1]]

        System.out.println(solver.threeSum(new int[]{0, 1, 1}));
        // 輸出 []

        System.out.println(solver.threeSum(new int[]{0, 0, 0}));
        // 輸出 [[0, 0, 0]]
    }
}
/*
解題思路：
1.排序陣列，方便去重與雙指針移動。
2.固定一個數 nums[i]，再用 左右指針搜尋剩下兩數。
3.若 sum == 0 → 記錄答案，並跳過重複的數字。
4.若 sum < 0 → 左指針右移；若 sum > 0 → 右指針左移。
5.外層 i 迴圈時，也要跳過重複的 nums[i]。
*/
