//題目：Roman to Integer
//數字從左到右依序加總。若「小數字在大數字前」則要做減法（如 IV = 4）。

import java.util.*;

class lt_13_RomanToInteger {
    public int romanToInt(String s) {
        // 建立對應表
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int total = 0;

        // 從左到右遍歷字串
        for (int i = 0; i < s.length(); i++) {
            int value = map.get(s.charAt(i)); // 當前字元的數值

            // 如果後一個字元比當前大 → 減去這個值 (表示 IV, IX, XL, XC, CD, CM)
            if (i + 1 < s.length() && map.get(s.charAt(i + 1)) > value) {
                total -= value;
            } else {
                // 否則直接加上
                total += value;
            }
        }

        return total;
    }

    // 測試
    public static void main(String[] args) {
        lt_13_RomanToInteger solver = new lt_13_RomanToInteger();
        System.out.println(solver.romanToInt("III"));      // 3
        System.out.println(solver.romanToInt("LVIII"));    // 58
        System.out.println(solver.romanToInt("MCMXCIV"));  // 1994
    }
}
/*
解題思路：
1.建立羅馬字元與數值的對應表。
2.從左到右遍歷字串：
    若「下一個字元」比「當前字元」大 → 減去當前值。
    否則，加上當前值。
3.累計結果後回傳。
*/
