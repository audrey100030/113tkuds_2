//題目：4Sum
//找出所有四元組 (a,b,c,d) 使得 nums[a] + nums[b] + nums[c] + nums[d] == target。去重複。
import java.util.*;

class lt_18_FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); // 先排序，方便處理重複與雙指針
        int n = nums.length;

        for (int i = 0; i < n - 3; i++) {
            // 避免 i 重複
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            for (int j = i + 1; j < n - 2; j++) {
                // 避免 j 重複
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                int left = j + 1;
                int right = n - 1;

                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];

                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        // 跳過重複的 left
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        // 跳過重複的 right
                        while (left < right && nums[right] == nums[right - 1]) right--;

                        left++;
                        right--;
                    } else if (sum < target) {
                        left++; // 總和太小 → 左指針右移
                    } else {
                        right--; // 總和太大 → 右指針左移
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        lt_18_FourSum solver = new lt_18_FourSum();
        System.out.println(solver.fourSum(new int[]{1,0,-1,0,-2,2}, 0));

        System.out.println(solver.fourSum(new int[]{2,2,2,2,2}, 8));
    }
}
/*
解題思路：
1.先 排序陣列。
2.用兩層迴圈固定前兩個數 nums[i] 和 nums[j]。
3.剩下的兩數用 雙指針 (left, right) 搜尋。
4.若 sum == target → 加入結果，並跳過重複元素。
5.若 sum < target → 左指針右移；若 sum > target → 右指針左移。
*/