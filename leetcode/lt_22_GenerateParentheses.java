//題目：Generate Parentheses
//要生成所有「合法的括號組合」。

import java.util.*;

class lt_22_GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, new StringBuilder(), 0, 0, n);
        return result;
    }

    // 回溯函數
    private void backtrack(List<String> result, StringBuilder current, int open, int close, int max) {
        // 當字串長度達到 2 * n 時，表示生成完成
        if (current.length() == max * 2) {
            result.add(current.toString());
            return;
        }

        // 只要還能加左括號，就嘗試加
        if (open < max) {
            current.append("(");
            backtrack(result, current, open + 1, close, max);
            current.deleteCharAt(current.length() - 1); // 撤銷選擇
        }

        // 只要右括號數量小於左括號，就嘗試加右括號
        if (close < open) {
            current.append(")");
            backtrack(result, current, open, close + 1, max);
            current.deleteCharAt(current.length() - 1); // 撤銷選擇
        }
    }

    public static void main(String[] args) {
        lt_22_GenerateParentheses solver = new lt_22_GenerateParentheses();
        System.out.println(solver.generateParenthesis(3));
    }
}
/*
解題思路：
1.用一個字串 current 動態構建括號組合。
2.記錄目前已加入的 open（左括號數量）與 close（右括號數量）。
3.遞迴過程：
    如果 open < n，就能加左括號 "("。
    如果 close < open，就能加右括號 ")"。
4.當字串長度達到 2 * n 時，將組合加入結果。
*/