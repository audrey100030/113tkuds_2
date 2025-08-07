/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author audre
 */
import java.util.*;
public class BSTValidationAndRepair {
    static class TreeNode{
        int val;
        TreeNode left, right;
        TreeNode(int val){
            this.val = val;
        }
    }
    
    //驗證一棵二元樹是否為有效的BST
    static boolean isValidBST(TreeNode root){
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    static boolean isValid(TreeNode node, long min, long max){
        if(node == null) return true;
        if(node.val <= min || node.val >= max) return false;
        return isValid(node.left, min, node.val) && isValid(node.right, node.val, max);
    }
    
    //找出BST中不符合規則的節點
    static List<TreeNode> findInvalidNodes(TreeNode root){
        List<TreeNode> result = new ArrayList<>();
        TreeNode[] prev = new TreeNode[1];
        inorderDetect(root, prev, result);
        return result;
    }

    static void inorderDetect(TreeNode node, TreeNode[] prev, List<TreeNode> result){
        if(node == null) return;
        inorderDetect(node.left, prev, result);
        if(prev[0] != null && node.val <= prev[0].val){
            result.add(prev[0]);
            result.add(node);
        }
        prev[0] = node;
        inorderDetect(node.right, prev, result);
    }
    
    //修復一棵有兩個節點位置錯誤的BST
    static void recoverBST(TreeNode root){
        TreeNode[] first = new TreeNode[1];
        TreeNode[] second = new TreeNode[1];
        TreeNode[] prev = new TreeNode[1];
        inorderRecover(root, prev, first, second);
        if(first[0] != null && second[0] != null){
            int temp = first[0].val;
            first[0].val = second[0].val;
            second[0].val = temp;
        }
    }

    static void inorderRecover(TreeNode node, TreeNode[] prev, TreeNode[] first, TreeNode[] second){
        if(node == null) return;
        inorderRecover(node.left, prev, first, second);
        if(prev[0] != null && node.val <= prev[0].val){
            if(first[0] == null){
                first[0] = prev[0];
                second[0] = node;
            }else{
                second[0] = node;
            }
        }
        prev[0] = node;
        inorderRecover(node.right, prev, first, second);
    }
    
    //計算需要移除多少個節點才能讓樹變成有效的BST
    static int minDeletionsToBST(TreeNode root){
        List<Integer> inorderList = new ArrayList<>();
        collectInorder(root, inorderList);
        return inorderList.size() - lengthOfLIS(inorderList);
    }

    static void collectInorder(TreeNode node, List<Integer> list){
        if(node == null) return;
        collectInorder(node.left, list);
        list.add(node.val);
        collectInorder(node.right, list);
    }

    static int lengthOfLIS(List<Integer> nums){
        List<Integer> dp = new ArrayList<>();
        for(int num : nums){
            int i = 0;
            while(i < dp.size() && dp.get(i) < num){
                i++;
            }
            if(i == dp.size()){
                dp.add(num);
            }else{
                dp.set(i, num);
            }
        }
        return dp.size();
    }

    static void inorder(TreeNode node){
        if(node == null) return;
        inorder(node.left);
        System.out.print(node.val + " ");
        inorder(node.right);
    }
    
    public static void main(String[] args){
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(10);
        root.left.left.left = new TreeNode(6); 
        System.out.print("中序列印（含錯誤）：");
        inorder(root);
        System.out.println();
        System.out.println("是否為有效 BST？" + (isValidBST(root) ? "是" : "否"));
        List<TreeNode> invalids = findInvalidNodes(root);
        System.out.print("發現錯誤節點：");
        for (TreeNode n : invalids) {
            System.out.print(n.val + " ");
        }
        System.out.println();
        System.out.println("修復 BST 中...");
        recoverBST(root);
        System.out.print("修復後中序列印：");
        inorder(root);
        System.out.println();
        System.out.println("修復後是否為有效 BST？" + (isValidBST(root) ? "是" : "否"));
        TreeNode broken = new TreeNode(3);
        broken.left = new TreeNode(2);
        broken.right = new TreeNode(1); // 不符合 BST
        System.out.println("最少需移除節點數使成為 BST：" + minDeletionsToBST(broken));
    }
    
}
