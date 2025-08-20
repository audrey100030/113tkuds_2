/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.*;

public class M05_GCD_LCM_Recursive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();

        long g = gcd(a, b);
        long l = (a / g) * b; 

        System.out.println("GCD: " + g);
        System.out.println("LCM: " + l);
    }

    static long gcd(long x, long y) {
        if (y == 0) return x;
        return gcd(y, x % y);
    }
}

/*
 * Time Complexity: O(log(min(a, b)))
 * 說明：
 * 遞迴歐幾里得演算法，每次遞迴使數字大小縮小至少一半。
 * 因此最多需要 O(log(min(a, b))) 次遞迴。
 * LCM 計算僅需一次除法與一次乘法，為 O(1)。
 * 總複雜度 O(log(min(a, b)))。
 * 
 * 15. 先除再乘，避免溢位
 * 21. 遞迴歐幾里得算法
 */

