/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author audre
 */
import java.util.*;

public class LevelOrderTraversalVariations {

    static class TreeNode{
        int val;
        TreeNode left, right;
        TreeNode(int val){
            this.val = val;
        }
    }

    //將每一層的節點分別儲存在不同的List中
    static List<List<Integer>> levelOrderLevels(TreeNode root){
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for(int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                level.add(node.val);
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
            }
            result.add(level);
        }
        return result;
    }

    //實作之字形層序走訪（奇數層從左到右，偶數層從右到左）
    static List<List<Integer>> zigzagLevelOrder(TreeNode root){
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean leftToRight = true;
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> level = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                level.add(0); // 初始化為 size 個空位
            }
            for(int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                int index = leftToRight ? i : size - 1 - i;
                level.set(index, node.val);
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
            }
            result.add(level);
            leftToRight = !leftToRight;
        }
        return result;
    }

    //只列印每一層的最後一個節點
    static void printRightmostEachLevel(TreeNode root){
        if(root == null){
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        System.out.print("每層最後一個節點：");
        while(!queue.isEmpty()){
            int size = queue.size();
            TreeNode last = null;
            for(int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                last = node;
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
            }
            System.out.print(last.val + " ");
        }
        System.out.println();
    }

    //實作垂直層序走訪（按照節點的水平位置分組）
    static List<List<Integer>> verticalOrder(TreeNode root){
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Map<Integer, List<Integer>> map = new TreeMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> colQueue = new LinkedList<>();
        queue.add(root);
        colQueue.add(0);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            int col = colQueue.poll();
            if(!map.containsKey(col)){
                map.put(col, new ArrayList<>());
            }
            map.get(col).add(node.val);
            if(node.left != null){
                queue.add(node.left);
                colQueue.add(col - 1);
            }
            if(node.right != null){
                queue.add(node.right);
                colQueue.add(col + 1);
            }
        }
        for(List<Integer> group : map.values()){
            result.add(group);
        }
        return result;
    }

    static void inorder(TreeNode root){
        if(root == null){
            return;
        }
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    public static void main(String[] args){
        int[] values = {5, 3, 8, 2, 4, 7, 10, 1};
        TreeNode root = null;
        for(int v : values){
            root = insert(root, v);
        }
        System.out.println("每層各自儲存：");
        List<List<Integer>> levelLists = levelOrderLevels(root);
        for(List<Integer> level : levelLists){
            System.out.println(level);
        }
        System.out.println("\n之字形層序：");
        List<List<Integer>> zigzag = zigzagLevelOrder(root);
        for(List<Integer> level : zigzag){
            System.out.println(level);
        }
        System.out.println();
        printRightmostEachLevel(root);
        System.out.println("垂直層序：");
        List<List<Integer>> vertical = verticalOrder(root);
        for (List<Integer> group : vertical) {
            System.out.println(group);
        }
    }
    static TreeNode insert(TreeNode root, int val){
        if(root == null) {
            return new TreeNode(val);
        }
        if(val < root.val){
            root.left = insert(root.left, val);
        }else{
            root.right = insert(root.right, val);
        }
        return root;
    }
}
