//題目：Merge Two Sorted Lists
//兩個鏈表都已排序，要合併成一個有序鏈表。

// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class lt_21_MergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 建立虛擬頭節點，方便處理邊界情況
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        // 遍歷兩個鏈表
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1; // 把 list1 的節點接到結果上
                list1 = list1.next;   // 移動 list1
            } else {
                current.next = list2; // 把 list2 的節點接到結果上
                list2 = list2.next;   // 移動 list2
            }
            current = current.next; // 移動結果鏈表指針
        }

        // 把剩下的節點接上去（其中一個一定為空）
        if (list1 != null) current.next = list1;
        if (list2 != null) current.next = list2;

        return dummy.next; // 返回合併後的鏈表頭
    }

    public static void main(String[] args) {
        // list1 = 1->2->4
        ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        // list2 = 1->3->4
        ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4)));

        lt_21_MergeTwoSortedLists solver = new lt_21_MergeTwoSortedLists();
        ListNode merged = solver.mergeTwoLists(list1, list2);

        while (merged != null) {
            System.out.print(merged.val + (merged.next != null ? "->" : ""));
            merged = merged.next;
        }
    }
}
/*
解題思路：
1.建立一個虛擬頭節點 dummy，並用 current 指針操作。
2.同時遍歷兩個鏈表：
    比較 list1.val 與 list2.val，較小的接到結果上。
    移動該鏈表指針。
3.迴圈結束後，把未處理完的部分接到結果後面。
4.回傳 dummy.next 即為合併後的鏈表頭。
*/