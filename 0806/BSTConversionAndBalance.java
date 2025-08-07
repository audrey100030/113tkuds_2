/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author audre
 */
public class BSTConversionAndBalance {
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 將BST轉換為排序的雙向鏈結串列
    static TreeNode bstToDoublyList(TreeNode root) {
        if (root == null) return null;
        TreeNode[] prev = new TreeNode[1];
        TreeNode[] head = new TreeNode[1];
        convertBSTtoDLL(root, prev, head);
        return head[0];
    }

    static void convertBSTtoDLL(TreeNode node, TreeNode[] prev, TreeNode[] head) {
        if (node == null) return;
        convertBSTtoDLL(node.left, prev, head);
        if (prev[0] != null) {
            prev[0].right = node;
            node.left = prev[0];
        } else {
            head[0] = node;
        }
        prev[0] = node;
        convertBSTtoDLL(node.right, prev, head);
    }

    static void printDoublyList(TreeNode head) {
        TreeNode curr = head;
        System.out.print("雙向鏈結串列：");
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.right;
        }
        System.out.println();
    }

    // 將排序陣列轉換為平衡的BST
    static TreeNode sortedArrayToBST(int[] nums) {
        return buildBalancedBST(nums, 0, nums.length - 1);
    }

    static TreeNode buildBalancedBST(int[] nums, int left, int right) {
        if (left > right) return null;
        int mid = (left + right) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = buildBalancedBST(nums, left, mid - 1);
        node.right = buildBalancedBST(nums, mid + 1, right);
        return node;
    }

    // 檢查BST是否平衡，並計算平衡因子
    static boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }

    static int checkHeight(TreeNode node) {
        if (node == null) return 0;
        int left = checkHeight(node.left);
        int right = checkHeight(node.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) return -1;
        return Math.max(left, right) + 1;
    }

    static void printBalanceFactors(TreeNode node) {
        if (node == null) return;
        int left = height(node.left);
        int right = height(node.right);
        int balance = left - right;
        System.out.println("節點 " + node.val + " 的平衡因子：" + balance);
        printBalanceFactors(node.left);
        printBalanceFactors(node.right);
    }

    static int height(TreeNode node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    // 將BST中每個節點的值改為所有大於等於該節點值的總和
    static void convertToGreaterSumTree(TreeNode root) {
        int[] sum = new int[1];
        reverseInorderSum(root, sum);
    }

    static void reverseInorderSum(TreeNode node, int[] sum) {
        if (node == null) return;
        reverseInorderSum(node.right, sum);
        sum[0] += node.val;
        node.val = sum[0];
        reverseInorderSum(node.left, sum);
    }

    static void inorder(TreeNode node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.val + " ");
        inorder(node.right);
    }

    static TreeNode cloneTree(TreeNode node) {
        if (node == null) return null;
        TreeNode newNode = new TreeNode(node.val);
        newNode.left = cloneTree(node.left);
        newNode.right = cloneTree(node.right);
        return newNode;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(10);

        System.out.println("=== 原始 BST 中序列印 ===");
        inorder(root);
        System.out.println();

        TreeNode rootForDLL = cloneTree(root);
        TreeNode head = bstToDoublyList(rootForDLL);
        printDoublyList(head);

        int[] sorted = {1, 2, 3, 4, 5, 6, 7};
        TreeNode balanced = sortedArrayToBST(sorted);

        System.out.println("=== 平衡 BST 中序列印 ===");
        inorder(balanced);
        System.out.println();

        System.out.println("原始 BST 是否平衡？" + (isBalanced(root) ? "是" : "否"));
        System.out.println("平衡 BST 是否平衡？" + (isBalanced(balanced) ? "是" : "否"));

        System.out.println("=== 原始 BST 平衡因子 ===");
        printBalanceFactors(root);

        convertToGreaterSumTree(root);
        System.out.println("=== 節點值改為 ≥該節點值總和後的 BST 中序列印 ===");
        inorder(root);
        System.out.println();
    }
}

