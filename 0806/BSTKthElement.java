/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author audre
 */
public class BSTKthElement {

    static class TreeNode {

        int val;
        TreeNode left, right;
        int size;

        TreeNode(int val) {
            this.val = val;
            this.size = 1;
        }
    }

    static void updateSize(TreeNode node) {
        if (node == null) {
            return;
        }
        int leftSize = 0;
        if (node.left != null) {
            leftSize = node.left.size;
        }
        int rightSize = 0;
        if (node.right != null) {
            rightSize = node.right.size;
        }
        node.size = 1 + leftSize + rightSize;
    }

    static TreeNode insert(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (val < root.val) {
            root.left = insert(root.left, val);
        } else {
            root.right = insert(root.right, val);
        }
        updateSize(root);
        return root;
    }

    //找出第k小的元素
    static int kthSmallest(TreeNode root, int k){
        if(root == null) {
            return -1;
        }
        int leftSize = (root.left != null) ? root.left.size : 0;
        if(k == leftSize + 1){
            return root.val;  // 直接回傳目前這個節點的值
        }else if(k <= leftSize) {
            return kthSmallest(root.left, k);
        }else{
            return kthSmallest(root.right, k - leftSize - 1);
        }

    }

    //找出第k大的元素
    static int kthLargest(TreeNode root, int k) {
        if (root == null) {
            return -1;
        }
        int total = root.size;
        return kthSmallest(root, total - k + 1);
    }

    //找出第k小到第j小之間的所有元素
    static void kthRange(TreeNode root, int k, int j) {
        int[] count = {0};
        inorderInRange(root, k, j, count);
    }

    static void inorderInRange(TreeNode node, int k, int j, int[] count) {
        if (node == null) {
            return;
        }
        inorderInRange(node.left, k, j, count);
        count[0]++;
        if (count[0] >= k && count[0] <= j) {
            System.out.print(node.val + " ");
        }
        inorderInRange(node.right, k, j, count);
    }

    //實作一個支援動態插入刪除的第k小元素查詢
    static TreeNode delete(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (val < root.val) {
            root.left = delete(root.left, val);
        } else if (val > root.val) {
            root.right = delete(root.right, val);
        } else {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            TreeNode minNode = getMin(root.right);
            root.val = minNode.val;
            root.right = delete(root.right, minNode.val);
        }
        updateSize(root);
        return root;
    }

    static TreeNode getMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    static void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    public static void main(String[] args) {
        int[] values = {5, 3, 8, 2, 4, 7, 10, 1};
        TreeNode root = null;
        for (int v : values) {
            root = insert(root, v);
        }
        System.out.print("目前中序：");
        inorder(root);
        System.out.println();
        int k = 3;
        System.out.println("第 " + k + " 小的元素是：" + kthSmallest(root, k));
        k = 2;
        System.out.println("第 " + k + " 大的元素是：" + kthLargest(root, k));
        int j = 6;
        System.out.print("第 " + k + " 小到第 " + j + " 小之間的元素：");
        kthRange(root, k, j);
        System.out.println();
        root = insert(root, 6);
        System.out.print("插入 6 後中序：");
        inorder(root);
        System.out.println();
        root = delete(root, 4);
        System.out.print("刪除 4 後中序：");
        inorder(root);
        System.out.println();
        System.out.println("刪除後第 4 小元素是：" + kthSmallest(root, 4));
    }
}
