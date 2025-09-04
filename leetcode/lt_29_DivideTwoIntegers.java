//題目：Divide Two Integers
//不能用內建運算，必須手動模擬除法。

class lt_29_DivideTwoIntegers {
    public int divide(int dividend, int divisor) {
        // 特殊情況：溢出
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE; // 超過範圍，回傳最大值
        }

        // 判斷結果正負號
        boolean negative = (dividend < 0) ^ (divisor < 0);

        // 轉成 long，避免溢出
        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);

        int result = 0;

        // 用減法模擬除法，透過位運算加速
        while (a >= b) {
            long temp = b;
            long multiple = 1;

            // 儘量放大 divisor，直到超過 dividend
            while (a >= (temp << 1)) {
                temp <<= 1;        // divisor * 2
                multiple <<= 1;    // 倍數 * 2
            }

            a -= temp;          // 減去最大能放的倍數
            result += multiple; // 加上對應倍數
        }

        return negative ? -result : result;
    }

    public static void main(String[] args) {
        lt_29_DivideTwoIntegers solver = new lt_29_DivideTwoIntegers();
        System.out.println(solver.divide(10, 3));   // 3
        System.out.println(solver.divide(7, -3));   // -2
        System.out.println(solver.divide(-2147483648, -1)); // 2147483647 (溢出處理)
    }
}
/*
解題思路：
1.特判溢出情況 (-2^31 / -1)。
2.判斷結果正負號，並把 dividend、divisor 轉成正數（用 long 避免溢出）。
3.每次嘗試讓 divisor 左移（乘 2），直到快超過 dividend。
4.減去最大的倍數，並累加到結果。
5.最後根據正負號決定結果。
*/