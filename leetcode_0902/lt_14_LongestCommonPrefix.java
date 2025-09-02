//題目：Longest Common Prefix
//找出所有字串的共同前綴（從左邊開始相同的部分）。若沒有共同前綴，回傳空字串。

class lt_14_LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return ""; // 邊界情況：空陣列

        // 先假設第一個字串是共同前綴
        String prefix = strs[0];

        // 從第二個字串開始比較
        for (int i = 1; i < strs.length; i++) {
            // 不斷縮短 prefix，直到符合當前字串的開頭
            while (!strs[i].startsWith(prefix)) {
                prefix = prefix.substring(0, prefix.length() - 1); // 去掉最後一個字元
                if (prefix.isEmpty()) return ""; // 若縮短到空字串，直接回傳 ""
            }
        }

        return prefix;
    }

    // 測試
    public static void main(String[] args) {
        lt_14_LongestCommonPrefix solver = new lt_14_LongestCommonPrefix();
        System.out.println(solver.longestCommonPrefix(new String[]{"flower","flow","flight"})); // "fl"
        System.out.println(solver.longestCommonPrefix(new String[]{"dog","racecar","car"}));   // ""
    }
}
/*
解題思路：
1.假設第一個字串是初始前綴。
2.從第二個字串開始，比較是否以 prefix 開頭。
3.如果不是，縮短 prefix（去掉最後一個字元），直到符合或變成空字串。
4.全部字串檢查完後，得到最長共同前綴
*/
