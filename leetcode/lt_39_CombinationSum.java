//題目：Combination Sum
//所有可能組合，使得組合中數字的總和等於 target。

import java.util.*;

public class lt_39_CombinationSum {  // ← 改成 public class
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("請輸入候選數字 (以空白分隔): ");
        String[] arr = sc.nextLine().split(" ");
        int[] candidates = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            candidates[i] = Integer.parseInt(arr[i]);
        }

        System.out.print("請輸入 target: ");
        int target = sc.nextInt();

        lt_39_CombinationSum solver = new lt_39_CombinationSum();
        List<List<Integer>> result = solver.combinationSum(candidates, target);

        System.out.println("所有組合：");
        for (List<Integer> comb : result) {
            System.out.println(comb);
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] candidates, int target, int start, List<Integer> current, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            int num = candidates[i];
            if (num <= target) {
                current.add(num);
                backtrack(candidates, target - num, i, current, result);
                current.remove(current.size() - 1);
            }
        }
    }
}
/*
解題思路：
1.輸入候選數字
2.輸入目標數字
3.印出結果
*/