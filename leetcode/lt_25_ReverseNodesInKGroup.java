//題目：Reverse Nodes in k-Group
//
class ListNode {
    int val;
    ListNode next;
    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

public class lt_25_ReverseNodesInKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head;

        // 計算鏈結長度
        int length = 0;
        ListNode temp = head;
        while (temp != null) {
            length++;
            temp = temp.next;
        }

        // 使用假頭方便處理
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevGroupEnd = dummy;

        // 一組一組處理
        while (length >= k) {
            ListNode groupStart = prevGroupEnd.next;
            ListNode curr = groupStart.next;

            // 翻轉當前區塊
            for (int i = 1; i < k; i++) {
                groupStart.next = curr.next;
                curr.next = prevGroupEnd.next;
                prevGroupEnd.next = curr;
                curr = groupStart.next;
            }

            // 移動到下一組
            prevGroupEnd = groupStart;
            length -= k;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        // 建立鏈結串列: 1->2->3->4->5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        lt_25_ReverseNodesInKGroup t = new lt_25_ReverseNodesInKGroup();
        int k = 2;
        ListNode newHead = t.reverseKGroup(head, k);

        // 輸出結果
        while (newHead != null) {
            System.out.print(newHead.val + " ");
            newHead = newHead.next;
        }
    }
}
