/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author audre
 */
public class TreeMirrorAndSymmetry {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) {
            this.val = val;
        }
    }
    
    //二元樹是否對稱
    public static boolean isSymmetric(TreeNode root){
        if(root == null) return true;
        return isMirror(root.left, root.right);
    }
    
    public static boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.val == t2.val) && isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
    }
    
    //二元樹轉換為其鏡像樹
    public static void mirrorTree(TreeNode root){
        if(root == null) return;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        mirrorTree(root.left);
        mirrorTree(root.right);
    }
    
    //兩棵二元樹是否互為鏡像
     public static boolean areMirrors(TreeNode t1, TreeNode t2){
         return isMirror(t1, t2);
     }
    
    //檢查一棵樹是否為另一棵樹的子樹
     public static boolean isSubtree(TreeNode root, TreeNode subtree){
         if(root == null) return false;
         if(isSameTree(root, subtree)) return true;
        return isSubtree(root.left, subtree) || isSubtree(root.right, subtree);
     }
     
      public static boolean isSameTree(TreeNode a, TreeNode b){
        if(a == null && b == null) return true;
        if(a == null || b == null) return false;
        if(a.val != b.val) return false;
        return isSameTree(a.left, b.left) && isSameTree(a.right, b.right);
    }

    public static void inorderPrint(TreeNode root){
        if(root == null) return;
        inorderPrint(root.left);
        System.out.print(root.val + " ");
        inorderPrint(root.right);
    }
    
    public static void main(String[] args) {
        TreeNode A = new TreeNode(1);
        A.left = new TreeNode(2);
        A.right = new TreeNode(2);
        A.left.left = new TreeNode(3);
        A.right.right = new TreeNode(3);
        System.out.println("Is Symmetric: " + isSymmetric(A));
        mirrorTree(A);
        System.out.print("Mirror of A (inorder): ");
        inorderPrint(A);
        System.out.println();
        TreeNode B = new TreeNode(1);
        B.left = new TreeNode(2);
        B.right = new TreeNode(2);
        B.left.left = new TreeNode(3);
        B.right.right = new TreeNode(3);
        System.out.println("A and B are mirrors: " + areMirrors(A, B));
        TreeNode sub = new TreeNode(2);
        sub.right = new TreeNode(3);
        System.out.println("Is sub a subtree of A: " + isSubtree(A, sub));
    }
}
