//題目：Remove Nth Node From End of List
//要刪掉從尾端數來的第 n 個節點。

// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class lt_19_RemoveNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 建立虛擬頭節點，方便處理刪除頭節點的情況
        ListNode dummy = new ListNode(0, head);
        ListNode fast = dummy;
        ListNode slow = dummy;

        // fast 先走 n+1 步，讓 slow 停在要刪除的前一個節點
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // fast 與 slow 一起走，直到 fast 到達尾端
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // slow.next 就是要刪除的節點
        slow.next = slow.next.next;

        return dummy.next; // 回傳新的頭節點
    }

    public static void main(String[] args) {
        // 建立測試鏈表 1->2->3->4->5
        ListNode head = new ListNode(1,
                          new ListNode(2,
                          new ListNode(3,
                          new ListNode(4,
                          new ListNode(5)))));

        lt_19_RemoveNthFromEnd solver = new lt_19_RemoveNthFromEnd();
        ListNode newHead = solver.removeNthFromEnd(head, 2);

        while (newHead != null) {
            System.out.print(newHead.val + (newHead.next != null ? "->" : ""));
            newHead = newHead.next;
        }
    }
}
/*
解題思路：
1.建立一個 虛擬頭節點（dummy），指向 head。
2.用兩個指針 fast、slow：
    fast 先走 n+1 步。
    接著 fast 和 slow 一起走，直到 fast 到尾端。
3.此時 slow.next 就是要刪除的節點。
4.修改指標 slow.next = slow.next.next。
5.回傳 dummy.next 作為新鏈表頭。
*/