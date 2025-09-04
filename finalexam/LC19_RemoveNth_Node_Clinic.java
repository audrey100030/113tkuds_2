/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author audre
 */

import java.util.*;
public class LC19_RemoveNth_Node_Clinic {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int v) { this.val = v; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (int i = 0; i < n; i++) {
            tail.next = new ListNode(sc.nextInt());
            tail = tail.next;
        }
        int k = sc.nextInt();

        ListNode head = dummy.next;
        ListNode fast = head, slow = dummy;

        for (int i = 0; i < k; i++) fast = fast.next;

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        if (slow.next != null) slow.next = slow.next.next;

        StringBuilder sb = new StringBuilder();
        for (ListNode cur = dummy.next; cur != null; cur = cur.next) {
            if (sb.length() > 0) sb.append(' ');
            sb.append(cur.val);
        }
        System.out.println(sb.toString());
    }
}
