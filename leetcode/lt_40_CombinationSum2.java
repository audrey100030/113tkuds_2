//題目：Combination Sum II

import java.util.*;

public class lt_40_CombinationSum2 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates); // 排序，方便去除重複組合
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        // 呼叫遞迴函數
        backtrack(candidates, target, 0, 0, path, results);

        return results;
    }

    private void backtrack(int[] candidates, int target, int startIndex, int currentSum,
                           List<Integer> path, List<List<Integer>> results) {
        // 如果剛好等於 target，就把 path 複製加入結果
        if (currentSum == target) {
            results.add(new ArrayList<>(path));
            return;
        }

        // 如果超過 target，就不用繼續了
        if (currentSum > target) {
            return;
        }

        // 用 for loop 遍歷候選數字
        for (int i = startIndex; i < candidates.length; i++) {
            // 跳過重複數字（避免相同組合重複出現）
            if (i > startIndex && candidates[i] == candidates[i - 1]) {
                continue;
            }

            // 選擇 candidates[i]
            path.add(candidates[i]);

            // 遞迴，因為數字只能用一次，所以下一次從 i+1 開始
            backtrack(candidates, target, i + 1, currentSum + candidates[i], path, results);

            // 回溯，把剛剛加入的數字移除
            path.remove(path.size() - 1);
        }
    }

    // 測試用 main 函數
    public static void main(String[] args) {
        lt_40_CombinationSum2 solver = new lt_40_CombinationSum2();

        int[] candidates1 = {10, 1, 2, 7, 6, 1, 5};
        System.out.println(solver.combinationSum2(candidates1, 8));
        // 預期輸出: [[1,1,6], [1,2,5], [1,7], [2,6]]

        int[] candidates2 = {2, 5, 2, 1, 2};
        System.out.println(solver.combinationSum2(candidates2, 5));
        // 預期輸出: [[1,2,2], [5]]
    }
}
/*
解題思路：
1.Arrays.sort(candidates) → 對陣列排序，方便處理重複元素。
2.results → 最後存放所有符合條件的組合。
3.path → 暫存目前選擇的數字。
4.backtrack(...) → 遞迴函數，參數包括：
    startIndex → 控制從哪個位置開始選數字。
    currentSum → 目前選擇的數字總和。
    path → 當前的組合路徑。
    results → 存放合法解答。
5.if 條件：
    currentSum == target → 把當前 path 複製到 results。
    currentSum > target → 超過目標，直接 return。
5.for 迴圈：
    跳過相同數字（避免重複）。
    選擇 candidates[i]，加入 path。
    遞迴呼叫下一層，i+1 確保每個數字只能用一次。
    回溯，移除剛剛選擇的數字。
*/
