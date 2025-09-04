/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.*;

public class LC04_Median_QuakeFeeds {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        double[] A = new double[n];
        double[] B = new double[m];

        for (int i = 0; i < n; i++) A[i] = sc.nextDouble();
        for (int j = 0; j < m; j++) B[j] = sc.nextDouble();

        // 確保 A 是較短的
        if (n > m) {
            double[] tmp = A; A = B; B = tmp;
            int t = n; n = m; m = t;
        }

        int total = n + m;
        int half = (total + 1) / 2;
        int left = 0, right = n;
        double median = 0.0;

        while (left <= right) {
            int i = (left + right) / 2; // A 的切點
            int j = half - i;           // B 的切點

            double leftA = (i == 0) ? Double.NEGATIVE_INFINITY : A[i - 1];
            double rightA = (i == n) ? Double.POSITIVE_INFINITY : A[i];
            double leftB = (j == 0) ? Double.NEGATIVE_INFINITY : B[j - 1];
            double rightB = (j == m) ? Double.POSITIVE_INFINITY : B[j];

            if (leftA <= rightB && leftB <= rightA) {
                if (total % 2 == 1) {
                    median = Math.max(leftA, leftB);
                } else {
                    median = (Math.max(leftA, leftB) + Math.min(rightA, rightB)) / 2.0;
                }
                break;
            } else if (leftA > rightB) {
                right = i - 1;
            } else {
                left = i + 1;
            }
        }

        System.out.printf("%.1f\n", median);
    }
}
