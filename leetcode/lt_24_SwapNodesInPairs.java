//題目：Swap Nodes in Pairs
//每兩個相鄰節點要交換，不能只改值，必須調整鏈表指針。

// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class lt_24_SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        // 建立虛擬頭節點，方便處理頭節點被交換的情況
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode prev = dummy;

        // 每次處理一對節點 (first, second)
        while (head != null && head.next != null) {
            ListNode first = head;
            ListNode second = head.next;

            // 交換節點
            prev.next = second;
            first.next = second.next;
            second.next = first;

            // 移動指針
            prev = first;
            head = first.next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        // 建立鏈表 1->2->3->4
        ListNode head = new ListNode(1,
                          new ListNode(2,
                          new ListNode(3,
                          new ListNode(4))));

        lt_24_SwapNodesInPairs solver = new lt_24_SwapNodesInPairs();
        ListNode newHead = solver.swapPairs(head);

        while (newHead != null) {
            System.out.print(newHead.val + (newHead.next != null ? "->" : ""));
            newHead = newHead.next;
        }
    }
}
/*
解題思路：
1.建立虛擬頭 dummy，讓交換頭節點更方便。
2.使用 prev 指向待交換區塊的前一個節點。
3.定義兩個節點 first = head、second = head.next。
4.執行交換：
    prev.next = second
    first.next = second.next
    second.next = first
5.移動指針，處理下一對節點。
6.返回 dummy.next 作為新頭。
*/