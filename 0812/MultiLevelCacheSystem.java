/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author audre
 */
import java.util.*;

public class MultiLevelCacheSystem {
    static class CacheEntry {
        int key;
        String value;
        int freq;
        long time;

        CacheEntry(int key, String value, int freq, long time) {
            this.key = key;
            this.value = value;
            this.freq = freq;
            this.time = time;
        }
    }

    static class CacheLevel {
        int capacity;
        int cost;
        Map<Integer, CacheEntry> map;
        PriorityQueue<CacheEntry> heap;

        CacheLevel(int capacity, int cost) {
            this.capacity = capacity;
            this.cost = cost;
            this.map = new HashMap<>();
            this.heap = new PriorityQueue<>((a, b) -> {
                if (a.freq != b.freq) return Integer.compare(a.freq, b.freq);
                return Long.compare(a.time, b.time);
            });
        }

        void add(CacheEntry entry) {
            map.put(entry.key, entry);
            heap.offer(entry);
        }

        void remove(CacheEntry entry) {
            map.remove(entry.key);
            heap.remove(entry);
        }

        boolean isFull() {
            return map.size() >= capacity;
        }

        CacheEntry evict() {
            CacheEntry victim = heap.poll();
            if (victim != null) {
                map.remove(victim.key);
            }
            return victim;
        }
    }

    CacheLevel[] levels;
    long timeCounter = 0;

    public MultiLevelCacheSystem() {
        levels = new CacheLevel[]{
                new CacheLevel(2, 1),
                new CacheLevel(5, 3),
                new CacheLevel(10, 10)
        };
    }

    public String get(int key) {
        for (int i = 0; i < levels.length; i++) {
            if (levels[i].map.containsKey(key)) {
                CacheEntry entry = levels[i].map.get(key);
                entry.freq++;
                entry.time = ++timeCounter;

                // 嘗試往上層搬
                promoteIfNeeded(i, entry);
                return entry.value;
            }
        }
        return null; // miss
    }

    public void put(int key, String value) {
        // 如果已存在，直接更新
        for (CacheLevel level : levels) {
            if (level.map.containsKey(key)) {
                CacheEntry e = level.map.get(key);
                e.value = value;
                get(key); // 視為一次存取
                return;
            }
        }

        CacheEntry entry = new CacheEntry(key, value, 1, ++timeCounter);
        insertToLevel(0, entry);
    }

    private void insertToLevel(int levelIndex, CacheEntry entry) {
        if (levelIndex >= levels.length) return; // 無法再放
        CacheLevel level = levels[levelIndex];

        if (level.isFull()) {
            CacheEntry victim = level.evict();
            if (victim != null) {
                insertToLevel(levelIndex + 1, victim); // 淘汰往下層放
            }
        }
        level.add(entry);
    }

    private void promoteIfNeeded(int currentLevel, CacheEntry entry) {
        if (currentLevel == 0) return; // 已在 L1
        CacheLevel higher = levels[currentLevel - 1];
        if (!higher.isFull()) {
            // 從目前層刪除，搬上去
            levels[currentLevel].remove(entry);
            higher.add(entry);
        } else {
            // 如果上層最差的比我差 → 搬上去
            CacheEntry worst = higher.heap.peek();
            if (worst != null && compareEntry(entry, worst) > 0) {
                higher.evict();
                levels[currentLevel].remove(entry);
                higher.add(entry);
                insertToLevel(currentLevel, worst); // 把淘汰的放回當前層
            }
        }
    }

    // 比較誰更值得上層（freq 高，或 time 新）
    private int compareEntry(CacheEntry a, CacheEntry b) {
        if (a.freq != b.freq) return Integer.compare(a.freq, b.freq);
        return Long.compare(a.time, b.time);
    }

    public void printState() {
        for (int i = 0; i < levels.length; i++) {
            System.out.print("L" + (i + 1) + ": ");
            for (CacheEntry e : levels[i].map.values()) {
                System.out.print("[" + e.key + "," + e.value + ",f=" + e.freq + "] ");
            }
            System.out.println();
        }
        System.out.println("----");
    }

    public static void main(String[] args) {
        MultiLevelCacheSystem cache = new MultiLevelCacheSystem();
        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        cache.printState();

        cache.get(1);
        cache.get(1);
        cache.get(2);
        cache.printState();

        cache.put(4, "D");
        cache.put(5, "E");
        cache.put(6, "F");
        cache.printState();
    }
}
