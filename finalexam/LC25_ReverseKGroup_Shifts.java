/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author audre
 */
import java.util.*;
public class LC25_ReverseKGroup_Shifts {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    private static ListNode reverse(ListNode start, ListNode end) {
        ListNode prev = null, cur = start;
        while (cur != end) {
            ListNode nxt = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nxt;
        }
        return prev;
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        if (k <= 1 || head == null) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode groupPrev = dummy;

        while (true) {
            ListNode kth = groupPrev;
            for (int i = 0; i < k && kth != null; i++) {
                kth = kth.next;
            }
            if (kth == null) break;

            ListNode groupStart = groupPrev.next;
            ListNode groupNext = kth.next;

            kth.next = null;
            ListNode newHead = reverse(groupStart, null);

            groupPrev.next = newHead;
            groupStart.next = groupNext;

            groupPrev = groupStart;
        }

        return dummy.next;
    }

    private static ListNode buildList(int[] arr) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (int x : arr) {
            tail.next = new ListNode(x);
            tail = tail.next;
        }
        return dummy.next;
    }

    private static void printList(ListNode head) {
        ListNode cur = head;
        boolean first = true;
        while (cur != null) {
            if (!first) System.out.print(" ");
            System.out.print(cur.val);
            first = false;
            cur = cur.next;
        }
        System.out.println();
    }

   public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    if (!sc.hasNextInt()) return;
    int k = sc.nextInt();
    sc.nextLine(); 

    if (!sc.hasNextLine()) return;
    String line = sc.nextLine().trim();
    if (line.isEmpty()) return;

    String[] parts = line.split("\\s+");
    int[] arr = new int[parts.length];
    for (int i = 0; i < parts.length; i++) {
        arr[i] = Integer.parseInt(parts[i]);
    }

    ListNode head = buildList(arr);
    ListNode res = reverseKGroup(head, k);
    if (res != null) printList(res);
}

}
