/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author audre
 */
public class BSTRangeQuerySystem {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val){
            this.val = val;
        }
    }
    
    public static TreeNode insert(TreeNode root, int val){
        if(root == null) return new TreeNode(val);
        if(val < root.val) root.left = insert(root.left, val);
        else root.right = insert(root.right, val);
        return root;
    }
    
    //[min, max] 範圍內的所有節點
    public static void rangeQuery(TreeNode root, int min, int max){
        if(root == null) return;
        if(root.val > min) rangeQuery(root.left, min, max);
        if(root.val >= min && root.val <= max){
            System.out.print(root.val + " ");
        }
        if(root.val < max) rangeQuery(root.right, min, max);
    }
    
    //指定範圍內的節點數量
    public static int rangeCount(TreeNode root, int min, int max){
        if(root == null) return 0;
        if(root.val < min) return rangeCount(root.right, min, max);
        if(root.val > max) return rangeCount(root.left, min, max);
        return 1 + rangeCount(root.left, min, max) + rangeCount(root.right, min, max);
    }
    
    //指定範圍內所有節點值的總和
    public static int rangeSum(TreeNode root, int min, int max){
        if(root == null) return 0;
        if(root.val < min) return rangeSum(root.right, min, max);
        if(root.val > max) return rangeSum(root.left, min, max);
        return root.val + rangeSum(root.left, min, max) + rangeSum(root.right, min, max);
    }
    
    //最接近給定值的節點
    public static int closestValue(TreeNode root, int target){
        int closest = root.val;
        TreeNode current = root;
        while(current != null){
            if(Math.abs(current.val - target) < Math.abs(closest - target)){
                closest = current.val;
            }
            if(target < current.val){
                current = current.left;
            }else if(target > current.val){
                current = current.right;
            }else{
                break;
            }
        }
        return closest;
    }
    
    public static void main(String[] args) {
        int[] values = {15, 10, 20, 8, 12, 17, 25};
        TreeNode root = null;
        for (int val : values){
            root = insert(root, val);
        }
        int min = 10, max = 20;
        System.out.print("Range Query [" + min + "," + max + "]: ");
        rangeQuery(root, min, max); 
        System.out.println();
        int count = rangeCount(root, min, max);
        System.out.println("Range Count [" + min + "," + max + "]: " + count);
        int sum = rangeSum(root, min, max);
        System.out.println("Range Sum [" + min + "," + max + "]: " + sum);
        int target = 16;
        int closest = closestValue(root, target);
        System.out.println("Closest to " + target + ": " + closest);
    }
}
