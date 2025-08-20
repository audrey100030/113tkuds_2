/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author audre
 */
import java.util.*;

public class M09_AVLValidate {
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
        sc.close();

        TreeNode root = buildTree(arr);

        if (!isBST(root, Long.MIN_VALUE, Long.MAX_VALUE)) {
            System.out.println("Invalid BST");
            return;
        }

        if (!isAVL(root)) {
            System.out.println("Invalid AVL");
            return;
        }

        System.out.println("Valid");
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

    public static boolean isBST(TreeNode root, long min, long max) {
        if (root == null) return true;
        if (root.val <= min || root.val >= max) return false;
        return isBST(root.left, min, root.val) && isBST(root.right, root.val, max);
    }

    public static boolean isAVL(TreeNode root) {
        return checkHeight(root) != -1;
    }

    private static int checkHeight(TreeNode node) {
        if (node == null) return 0;

        int left = checkHeight(node.left);
        if (left == -1) return -1;

        int right = checkHeight(node.right);
        if (right == -1) return -1;

        if (Math.abs(left - right) > 1) return -1;

        return Math.max(left, right) + 1;
    }
}

/*
 * Time Complexity: O(n)
 * 說明：
 * 建樹遍歷陣列一次 → O(n)
 * 驗證 BST：每節點走一次 → O(n)
 * 驗證 AVL：後序遍歷每節點一次 → O(n)
 * 整體仍為 O(n)。
 *
 * 13. 樹節點定義
 * 41. 建樹 (層序)
 * 52. 左子
 * 57. 右子
 * 67. 檢查 BST（帶上下界）
 * 73. 檢查 AVL，回傳是否平衡
 * 77. 若不平衡回傳 -1，否則回傳高度
 */
