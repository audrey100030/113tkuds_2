/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.*;

public class M04_TieredTaxSimple {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        long totalTax = 0; 

        for (int i = 0; i < n; i++) {
            int income = sc.nextInt();
            int tax = computeTax(income);
            totalTax += tax;
            System.out.println("Tax: " + tax);
        }

        long avg = totalTax / n;
        System.out.println("Average: " + avg);
    }

    static int computeTax(int income) {
        int tax = 0;

        if (income > 1000000) {
            tax += (income - 1000000) * 30 / 100;
            income = 1000000;
        }
        if (income > 500000) {
            tax += (income - 500000) * 20 / 100;
            income = 500000;
        }
        if (income > 120000) {
            tax += (income - 120000) * 12 / 100;
            income = 120000;
        }
        if (income > 0) {
            tax += income * 5 / 100;
        }

        return tax;
    }
}

/*
 * Time Complexity: O(n)
 * 說明：
 * 每個收入僅需走訪 4 個稅率區間，成本 O(1)。
 * 總共有 n 筆輸入，因此總時間複雜度 O(n)。
 * 記憶體僅存少量變數，空間複雜度 O(1)。
 *
 * 13. 累積稅額，避免溢位用 long
 * 22. 計算平均（四捨五入取整數）
 * 26. 計算單筆收入的稅額
 */

