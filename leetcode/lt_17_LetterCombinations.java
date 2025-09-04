//題目：Letter Combinations of a Phone Number
//每個數字（2-9）對應多個字母。要輸出所有可能的字母組合。類似「排列組合」問題 → 適合用 回溯法。
import java.util.*;

class lt_17_LetterCombinations {
    // 數字到字母的映射表
    private static final String[] mapping = {
        "",     // 0 (不用)
        "",     // 1 (不用)
        "abc",  // 2
        "def",  // 3
        "ghi",  // 4
        "jkl",  // 5
        "mno",  // 6
        "pqrs", // 7
        "tuv",  // 8
        "wxyz"  // 9
    };

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) return result; // 邊界處理

        backtrack(result, new StringBuilder(), digits, 0);
        return result;
    }

    // 回溯函數
    private void backtrack(List<String> result, StringBuilder current, String digits, int index) {
        // 當組合長度等於輸入長度時，加入結果
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        // 找出當前數字對應的字母集合
        String letters = mapping[digits.charAt(index) - '0'];

        // 遍歷可能的字母，逐一嘗試
        for (char c : letters.toCharArray()) {
            current.append(c); // 做選擇
            backtrack(result, current, digits, index + 1); // 遞迴到下一層
            current.deleteCharAt(current.length() - 1); // 撤銷選擇（回溯）
        }
    }

    public static void main(String[] args) {
        lt_17_LetterCombinations solver = new lt_17_LetterCombinations();
        System.out.println(solver.letterCombinations("23"));
    }
}
/*
解題思路：
1.建立數字到字母的對應表。
2.從第一個數字開始，依序嘗試每個對應字母。
3.每次選擇一個字母後，進入下一層遞迴。
4.當組合長度等於輸入長度時，存入結果。
5.遞迴返回時撤銷選擇（backtrack）。
*/