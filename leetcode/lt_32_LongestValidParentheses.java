//題目：Longest Valid Parentheses
//找到最長的「有效括號子字串」

import java.util.*;

class lt_32_LongestValidParentheses {
    public int longestValidParentheses(String s) {
        int maxLen = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // 基準點，方便計算長度

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                stack.push(i); // 記錄 '(' 的索引
            } else {
                stack.pop(); // 嘗試匹配 ')'
                if (stack.isEmpty()) {
                    // 沒有匹配到，更新基準點
                    stack.push(i);
                } else {
                    // 當前有效長度 = 當前索引 - 最後一個未匹配的索引
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }

        return maxLen;
    }

    public static void main(String[] args) {
        lt_32_LongestValidParentheses solver = new lt_32_LongestValidParentheses();
        System.out.println(solver.longestValidParentheses("(()"));      // 2
        System.out.println(solver.longestValidParentheses(")()())"));   // 4
        System.out.println(solver.longestValidParentheses(""));         // 0
    }
}
/*
解題思路：
1.用 stack 記錄括號的索引，初始壓入 -1 當基準點。
2.遇到 '(' → push 索引進 stack。
3.遇到 ')' → pop 一個索引，表示成功配對。
    如果 stack 空了，表示沒有基準，push 當前索引作為新的基準點。
    否則，用當前索引減去 stack.top()，計算出有效括號長度。
4.遍歷結束，maxLen 即為最長有效長度。
*/