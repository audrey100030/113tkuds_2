//題目：3Sum Closest
//找出三個數字，讓它們的總和最接近 target
import java.util.*;

class lt_16_ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums); // 先排序，方便使用雙指針
        int n = nums.length;

        // 初始答案：先假設前三個數的和
        int closest = nums[0] + nums[1] + nums[2];

        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                // 如果這個 sum 更接近 target，就更新答案
                if (Math.abs(sum - target) < Math.abs(closest - target)) {
                    closest = sum;
                }

                // 移動指針
                if (sum < target) {
                    left++; // 總和太小 → 增加
                } else if (sum > target) {
                    right--; // 總和太大 → 減少
                } else {
                    // 完全等於 target → 最佳解，直接回傳
                    return sum;
                }
            }
        }

        return closest;
    }

    public static void main(String[] args) {
        lt_16_ThreeSumClosest solver = new lt_16_ThreeSumClosest();
        System.out.println(solver.threeSumClosest(new int[]{-1, 2, 1, -4}, 1)); // 2
        System.out.println(solver.threeSumClosest(new int[]{0, 0, 0}, 1));     // 0
    }
}
/*
解題思路：
1.排序陣列，方便移動指針調整和。
2.固定一個數 nums[i]，再用 左右指針尋找剩下兩數。
3.每次計算 sum = nums[i] + nums[left] + nums[right]：
    若 sum 更接近 target，更新 closest。
    若 sum < target → 左指針右移（增加總和）。
    若 sum > target → 右指針左移（減少總和）。
    若 sum == target → 直接回傳（最佳解）。
*/
