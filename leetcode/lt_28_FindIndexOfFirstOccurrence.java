//題目：Find the Index of the First Occurrence in a String
//找 needle 在 haystack 中第一次出現的位置。如果不存在，回傳 -1。如果 needle 是空字串，回傳 0。
class lt_28_FindIndexOfFirstOccurrence {
    public int strStr(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();

        if (m == 0) return 0; // 邊界條件：空字串出現在索引 0

        // 遍歷 haystack
        for (int i = 0; i <= n - m; i++) {
            // 從 i 開始的子字串長度 >= needle
            if (haystack.substring(i, i + m).equals(needle)) {
                return i;
            }
        }

        return -1; // 找不到
    }

    public static void main(String[] args) {
        lt_28_FindIndexOfFirstOccurrence solver = new lt_28_FindIndexOfFirstOccurrence();
        System.out.println(solver.strStr("sadbutsad", "sad")); // 0
        System.out.println(solver.strStr("leetcode", "leeto")); // -1
    }
}
/*
解題思路：
1.遍歷 haystack，每次截取長度為 needle.length() 的子字串。
2.比對是否等於 needle。
3.第一個相等的位置就是答案。
*/