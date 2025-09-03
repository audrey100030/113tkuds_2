//題目：Integer to Roman
//把整數轉換成羅馬數字。

class lt_12_IntegerToRoman {
    public String intToRoman(int num) {
        // 對應表（由大到小）
        int[] values = {
            1000, 900, 500, 400,
            100, 90, 50, 40,
            10, 9, 5, 4,
            1
        };
        String[] symbols = {
            "M", "CM", "D", "CD",
            "C", "XC", "L", "XL",
            "X", "IX", "V", "IV",
            "I"
        };

        StringBuilder sb = new StringBuilder();

        // 從大到小處理
        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                num -= values[i];
                sb.append(symbols[i]);
            }
        }

        return sb.toString();
    }

    // 測試
    public static void main(String[] args) {
        lt_12_IntegerToRoman solver = new lt_12_IntegerToRoman();
        System.out.println(solver.intToRoman(3));    // "III"
        System.out.println(solver.intToRoman(58));   // "LVIII"
        System.out.println(solver.intToRoman(1994)); // "MCMXCIV"
    }
}
/*
解題思路：
1.建立 數值與符號對應表（包含特殊情況 900、400、90…）。
2.從最大值開始，若當前數字 num >= value[i]，就扣掉並加上對應符號。
3.重複直到 num == 0。
*/
