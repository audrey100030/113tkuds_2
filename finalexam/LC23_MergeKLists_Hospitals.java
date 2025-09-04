/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author audre
 */
import java.util.*;
public class LC23_MergeKLists_Hospitals {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>( (a, b) -> a.val - b.val );

        for (ListNode node : lists) {
            if (node != null) pq.offer(node);
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (!pq.isEmpty()) {
            ListNode min = pq.poll();
            tail.next = min;
            tail = tail.next;

            if (min.next != null) pq.offer(min.next);
        }

        return dummy.next;
    }

    public static ListNode buildList(Scanner sc) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (true) {
            int x = sc.nextInt();
            if (x == -1) break;
            tail.next = new ListNode(x);
            tail = tail.next;
        }
        return dummy.next;
    }

    public static void printList(ListNode head) {
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
        int k = sc.nextInt();

        ListNode[] lists = new ListNode[k];
        for (int i = 0; i < k; i++) {
            lists[i] = buildList(sc);
        }

        ListNode merged = mergeKLists(lists);
        if (merged != null) printList(merged);
    }
}
