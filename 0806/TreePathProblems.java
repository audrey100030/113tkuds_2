/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author audre
 */
import java.util.*;
public class TreePathProblems {
    static class TreeNode{
        int val;
        TreeNode left, right;

        TreeNode(int val){
            this.val = val;
        }
    }
    
    //找出從根節點到所有葉節點的路徑
     static List<List<Integer>> allRootToLeafPaths(TreeNode root){
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        dfsPaths(root, current, result);
        return result;
    }

    static void dfsPaths(TreeNode node, List<Integer> current, List<List<Integer>> result){
        if(node == null) return;
        current.add(node.val);
        if(node.left == null && node.right == null){
            result.add(new ArrayList<>(current));
        }else{
            dfsPaths(node.left, current, result);
            dfsPaths(node.right, current, result);
        }
        current.remove(current.size() - 1);
    }
    
    //判斷樹中是否存在和為目標值的根到葉路徑
    static boolean hasPathSum(TreeNode root, int target) {
        if(root == null) return false;
        if(root.left == null && root.right == null){
            return root.val == target;
        }
        return hasPathSum(root.left, target - root.val) || hasPathSum(root.right, target - root.val);
    }
    
    //找出樹中和最大的根到葉路徑
     static int maxRootToLeafSum(TreeNode root){
        if(root == null) return Integer.MIN_VALUE;
        if(root.left == null && root.right == null) return root.val;
        int left = maxRootToLeafSum(root.left);
        int right = maxRootToLeafSum(root.right);
        return root.val + Math.max(left, right);
    }
    
    //計算樹中任意兩節點間的最大路徑和（樹的直徑
      static int maxPathSum(TreeNode root){
        int[] max = new int[]{Integer.MIN_VALUE};
        maxPathDown(root, max);
        return max[0];
    }

    static int maxPathDown(TreeNode node, int[] max){
        if(node == null) return 0;
        int left = Math.max(0, maxPathDown(node.left, max));
        int right = Math.max(0, maxPathDown(node.right, max));
        max[0] = Math.max(max[0], node.val + left + right);
        return node.val + Math.max(left, right);
    }
    
    public static void main(String[] args){
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);
        System.out.println("1. 所有根到葉路徑：");
        List<List<Integer>> paths = allRootToLeafPaths(root);
        for (List<Integer> path : paths) {
            System.out.println(path);
        }
        System.out.println("2. 是否存在和為 22 的根到葉路徑？" + (hasPathSum(root, 22) ? "是" : "否"));
        System.out.println("3. 最大根到葉路徑和為：" + maxRootToLeafSum(root));
        System.out.println("4. 任意兩節點最大路徑和為：" + maxPathSum(root));
    }
    
}
