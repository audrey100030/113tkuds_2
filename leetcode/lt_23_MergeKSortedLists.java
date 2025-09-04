//題目：Merge k Sorted Lists
//有 k 條已排序鏈表，要合併成一條有序鏈表

import java.util.*;

// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class lt_23_MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        // 使用最小堆 (優先隊列)，存放當前每個鏈表的頭節點
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);

        // 把所有非空鏈表的頭節點加入堆
        for (ListNode node : lists) {
            if (node != null) {
                pq.offer(node);
            }
        }

        // 建立虛擬頭節點，方便操作
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        // 不斷取出最小的節點，並將其後繼節點放回堆
        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            current.next = node;
            current = current.next;

            if (node.next != null) {
                pq.offer(node.next);
            }
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        // list1 = 1->4->5
        ListNode list1 = new ListNode(1, new ListNode(4, new ListNode(5)));
        // list2 = 1->3->4
        ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        // list3 = 2->6
        ListNode list3 = new ListNode(2, new ListNode(6));

        ListNode[] lists = {list1, list2, list3};

        lt_23_MergeKSortedLists solver = new lt_23_MergeKSortedLists();
        ListNode merged = solver.mergeKLists(lists);

        while (merged != null) {
            System.out.print(merged.val + (merged.next != null ? "->" : ""));
            merged = merged.next;
        }
    }
}
/*
解題思路：
1.建立一個最小堆，存放每條鏈表的當前節點。
2.每次取出堆頂（最小值），接到結果鏈表後面。
3.如果該節點有後繼，將後繼放回堆。
4.重複直到堆為空。
*/