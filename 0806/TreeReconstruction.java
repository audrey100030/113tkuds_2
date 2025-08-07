/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author audre
 */
import java.util.*;
public class TreeReconstruction {
    static class TreeNode{
        int val;
        TreeNode left, right;

        TreeNode(int val){
            this.val = val;
        }
    }
    
    //根據前序和中序走訪結果重建二元樹
    static TreeNode buildFromPreIn(int[] preorder, int[] inorder){
        return buildPreIn(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    static TreeNode buildPreIn(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd){
        if(preStart > preEnd || inStart > inEnd) return null;
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);
        int idx = inStart;
        while(inorder[idx] != rootVal) idx++;
        int leftSize = idx - inStart;
        root.left = buildPreIn(preorder, preStart + 1, preStart + leftSize,  inorder, inStart, idx - 1);
        root.right = buildPreIn(preorder, preStart + leftSize + 1, preEnd, inorder, idx + 1, inEnd);
        return root;
    }
    
    //根據後序和中序走訪結果重建二元樹
    static TreeNode buildFromPostIn(int[] postorder, int[] inorder){
        return buildPostIn(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1);
    }

    static TreeNode buildPostIn(int[] postorder, int postStart, int postEnd, int[] inorder, int inStart, int inEnd){
        if(postStart > postEnd || inStart > inEnd) return null;

        int rootVal = postorder[postEnd];
        TreeNode root = new TreeNode(rootVal);
        int idx = inStart;
        while (inorder[idx] != rootVal) idx++;
        int leftSize = idx - inStart;
        root.left = buildPostIn(postorder, postStart, postStart + leftSize - 1, inorder, inStart, idx - 1);
        root.right = buildPostIn(postorder, postStart + leftSize, postEnd - 1, inorder, idx + 1, inEnd);
        return root;
    }
    
    //根據層序走訪結果重建完全二元樹
     static TreeNode buildCompleteTree(int[] levelOrder){
        if(levelOrder.length == 0) return null;
        TreeNode[] nodes = new TreeNode[levelOrder.length];
        for(int i = 0; i < levelOrder.length; i++){
            nodes[i] = new TreeNode(levelOrder[i]);
        }
        for(int i = 0; i < levelOrder.length; i++){
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            if (left < levelOrder.length) nodes[i].left = nodes[left];
            if (right < levelOrder.length) nodes[i].right = nodes[right];
        }
        return nodes[0];
    }
    
    //驗證重建的樹是否正確
     static void preorder(TreeNode node){
        if(node == null) return;
        System.out.print(node.val + " ");
        preorder(node.left);
        preorder(node.right);
    }

    static void inorder(TreeNode node){
        if(node == null) return;
        inorder(node.left);
        System.out.print(node.val + " ");
        inorder(node.right);
    }

    static void postorder(TreeNode node){
        if(node == null) return;
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.val + " ");
    }

    static void levelOrder(TreeNode root){
        if (root == null) return;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode curr = q.poll();
            System.out.print(curr.val + " ");
            if (curr.left != null) q.add(curr.left);
            if (curr.right != null) q.add(curr.right);
        }
    }
    
    public static void main(String[] args){
        int[] preorder = {1, 2, 4, 5, 3, 6};
        int[] inorder =  {4, 2, 5, 1, 3, 6};
        int[] postorder = {4, 5, 2, 6, 3, 1};
        int[] levelOrder = {1, 2, 3, 4, 5, 6};
        TreeNode root1 = buildFromPreIn(preorder, inorder);
        System.out.println("前序+中序重建後的中序：");
        inorder(root1); System.out.println();
        System.out.println("前序：");
        preorder(root1); System.out.println();
        TreeNode root2 = buildFromPostIn(postorder, inorder);
        System.out.println("後序+中序重建後的中序：");
        inorder(root2); System.out.println();
        System.out.println("後序：");
        postorder(root2); System.out.println();
        TreeNode root3 = buildCompleteTree(levelOrder);
        System.out.println("層序重建後的層序輸出：");
        levelOrder(root3); System.out.println();
    }
    
}
