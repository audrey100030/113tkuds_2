/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.*;

public class M08_BSTRangedSum {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        int L = sc.nextInt();
        int R = sc.nextInt();
        sc.close();

        TreeNode root = buildTree(arr);
        int sum = rangeSumBST(root, L, R);

        System.out.println("Sum: " + sum);
    }

    public static TreeNode buildTree(int[] arr) {
        if (arr.length == 0 || arr[0] == -1) return null;

        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        int i = 1;
        while (i < arr.length) {
            TreeNode node = q.poll();
            if (node != null) {
                if (i < arr.length && arr[i] != -1) {
                    node.left = new TreeNode(arr[i]);
                    q.add(node.left);
                }
                i++;
                if (i < arr.length && arr[i] != -1) {
                    node.right = new TreeNode(arr[i]);
                    q.add(node.right);
                }
                i++;
            }
        }
        return root;
    }

    public static int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) return 0;
        if (root.val < L) return rangeSumBST(root.right, L, R);
        if (root.val > R) return rangeSumBST(root.left, L, R);
        return root.val + rangeSumBST(root.left, L, R) + rangeSumBST(root.right, L, R);
    }
}


