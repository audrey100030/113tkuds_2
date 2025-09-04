//題目：Valid Parentheses
//每個開括號必須由相同型別的閉括號關閉

import java.util.*;

class lt_20_ValidParentheses {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            // 如果是左括號，壓入堆疊
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                // 如果是右括號，先檢查堆疊是否為空
                if (stack.isEmpty()) return false;

                char top = stack.pop();
                // 檢查是否匹配
                if ((c == ')' && top != '(') ||
                    (c == ']' && top != '[') ||
                    (c == '}' && top != '{')) {
                    return false;
                }
            }
        }

        // 最後堆疊必須為空才是有效括號
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        lt_20_ValidParentheses solver = new lt_20_ValidParentheses();
        System.out.println(solver.isValid("()"));      // true
        System.out.println(solver.isValid("()[]{}"));  // true
        System.out.println(solver.isValid("(]"));      // false
        System.out.println(solver.isValid("([)]"));    // false
        System.out.println(solver.isValid("{[]}"));    // true
    }
}
/*
解題思路：
1.遍歷字串
    如果遇到 左括號 → 壓入堆疊。
    如果遇到 右括號 → 檢查堆疊是否為空，並判斷堆疊頂端是否匹配。
2.如果不匹配或堆疊為空 → 回傳 false。
3.遍歷結束後，若堆疊仍有剩下 → 回傳 false。
4.只有在堆疊剛好清空時，才是有效括號 → 回傳 true。
*/