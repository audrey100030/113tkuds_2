/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.*;

public class M12_MergeKTimeTables {
    static class Entry {
        int time;
        int listIdx;
        int pos;
        Entry(int t, int l, int p) {
            time = t;
            listIdx = l;
            pos = p;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        List<int[]> lists = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            int len = sc.nextInt();
            int[] arr = new int[len];
            for (int j = 0; j < len; j++) {
                arr[j] = sc.nextInt();
            }
            lists.add(arr);
        }
        sc.close();

        List<Integer> merged = mergeKLists(lists, K);

        for (int i = 0; i < merged.size(); i++) {
            System.out.print(merged.get(i));
            if (i < merged.size() - 1) System.out.print(" ");
        }
    }

    public static List<Integer> mergeKLists(List<int[]> lists, int K) {
        List<Integer> res = new ArrayList<>();
        PriorityQueue<Entry> pq = new PriorityQueue<>(new Comparator<Entry>() {
            public int compare(Entry a, Entry b) {
                return a.time - b.time; // 最小時間優先
            }
        });

        // 每個清單的第一個元素放進堆
        for (int i = 0; i < K; i++) {
            if (lists.get(i).length > 0) {
                pq.add(new Entry(lists.get(i)[0], i, 0));
            }
        }

        while (!pq.isEmpty()) {
            Entry e = pq.poll();
            res.add(e.time);

            // 該清單的下一個元素
            int nextPos = e.pos + 1;
            if (nextPos < lists.get(e.listIdx).length) {
                pq.add(new Entry(lists.get(e.listIdx)[nextPos], e.listIdx, nextPos));
            }
        }

        return res;
    }
}

/*
 * Time Complexity: O(N log K)
 * 說明：
 * - N = 總時刻數，K ≤ 5。
 * - 每次從 heap 取元素 O(log K)，共 N 次 → O(N log K)。
 * - 在此情境下 K 很小，近似 O(N)。
 */

