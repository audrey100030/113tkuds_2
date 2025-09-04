//題目：Count and Say
//用「遞迴」或「迴圈」依序生成下一個字串

public class lt_38_CountandSay {
    public static void main(String[] args) {
        lt_38_CountandSay sol = new lt_38_CountandSay();
        int n = 5;
        System.out.println("countAndSay(" + n + ") = " + sol.countAndSay(n));
    }

    public String countAndSay(int n) {
        if (n == 1) return "1";

        String prev = countAndSay(n - 1);
        StringBuilder sb = new StringBuilder();

        int i = 0;
        while (i < prev.length()) {
            char c = prev.charAt(i);
            int count = 0;

            while (i < prev.length() && prev.charAt(i) == c) {
                count++;
                i++;
            }
            sb.append(count).append(c);
        }
        return sb.toString();
    }
}
/*
解題思路：
1.遞迴計算前一個字串 countAndSay(n-1)。
2.使用 while 迴圈掃描字串，把連續相同字元壓縮成「數字+字元」。
3.回傳處理後的新字串。
*/