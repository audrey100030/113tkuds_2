/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.*;

public class M03_TopKConvenience {
    static class Item {
        String name;
        int qty;
        Item(String name, int qty) {
            this.name = name;
            this.qty = qty;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int K = sc.nextInt();
        Item[] items = new Item[n];

        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int qty = sc.nextInt();
            items[i] = new Item(name, qty);
        }

        Arrays.sort(items, new Comparator<Item>() {
            public int compare(Item a, Item b) {
                if (a.qty != b.qty) return b.qty - a.qty;
                return a.name.compareTo(b.name); 
            }
        });

        for (int i = 0; i < Math.min(K, n); i++) {
            System.out.println(items[i].name + " " + items[i].qty);
        }
    }
}

/*
 * Time Complexity: O(n log n)
 * 說明：
 * 讀入 n 筆資料 O(n)。
 * 使用 Arrays.sort() 對 n 筆排序，成本 O(n log n)。
 * 輸出前 K 筆 O(K)，相較於排序可忽略。
 * 總時間複雜度為 O(n log n)。
 * 
 * 31. 排序：銷量降序，若相同則字典序升序
 * 33. 數量大的在前
 * 34. 字典序
 * 38. 輸出前 K 個（不足就全列）
 */

