/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author audre
 */
import java.util.*;
public class MultiWayTreeAndDecisionTree {
    
    //建立一個可以有任意多個子節點的多路樹
     static class TreeNode{
        String val;
        List<TreeNode> children;

        TreeNode(String val){
            this.val = val;
            this.children = new ArrayList<>();
        }
    }
    
    //實作多路樹的深度優先和廣度優先走訪
     static void dfs(TreeNode node){
        if(node == null) return;
        System.out.print(node.val + " ");
        for(TreeNode child : node.children){
            dfs(child);
        }
    }

    static void bfs(TreeNode root){
        if(root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode current = queue.poll();
            System.out.print(current.val + " ");
            for(TreeNode child : current.children){
                queue.offer(child);
            }
        }
    }

    //模擬簡單的決策樹結構（如猜數字遊戲
    static void simulateDecisionTree(TreeNode node, Scanner scanner){
        if(node == null) return;
        if(node.children.isEmpty()){
            System.out.println("決策結果：" + node.val);
            return;
        }
        System.out.println(node.val + " (輸入 1 或 2)");
        int choice = scanner.nextInt();
        if(choice >= 1 && choice <= node.children.size()){
            simulateDecisionTree(node.children.get(choice - 1), scanner);
        }else{
            System.out.println("無效選擇。");
        }
    }
    
    //計算多路樹的高度和每個節點的度數
    static int getHeight(TreeNode node){
        if(node == null) return 0;
        int maxHeight = 0;
        for(TreeNode child : node.children){
            maxHeight = Math.max(maxHeight, getHeight(child));
        }
        return maxHeight + 1;
    }
    
    static void printDegrees(TreeNode node){
        if(node == null) return;
        System.out.println("節點 " + node.val + " 的度數：" + node.children.size());
        for(TreeNode child : node.children){
            printDegrees(child);
        }
    }
    
    public static void main(String[] args) {
        TreeNode root = new TreeNode("A");
        TreeNode b = new TreeNode("B");
        TreeNode c = new TreeNode("C");
        TreeNode d = new TreeNode("D");
        TreeNode e = new TreeNode("E");
        TreeNode f = new TreeNode("F");
        TreeNode g = new TreeNode("G");
        root.children.add(b);
        root.children.add(c);
        root.children.add(d);
        b.children.add(e);
        b.children.add(f);
        d.children.add(g);
        System.out.println("=== 多路樹 DFS ===");
        dfs(root);
        System.out.println();
        System.out.println("=== 多路樹 BFS ===");
        bfs(root);
        System.out.println();
        System.out.println("=== 多路樹高度 ===");
        System.out.println(getHeight(root));
        System.out.println("=== 每個節點的度數 ===");
        printDegrees(root);
        TreeNode q1 = new TreeNode("數字大於 50 嗎？");
        TreeNode q2 = new TreeNode("數字大於 75 嗎？");
        TreeNode q3 = new TreeNode("數字小於等於 25 嗎？");
        TreeNode a1 = new TreeNode("答案是 80");
        TreeNode a2 = new TreeNode("答案是 60");
        TreeNode a3 = new TreeNode("答案是 10");
        TreeNode a4 = new TreeNode("答案是 30");
        q1.children.add(q2);
        q1.children.add(q3);
        q2.children.add(a1);
        q2.children.add(a2);
        q3.children.add(a3);
        q3.children.add(a4);
        System.out.println("=== 模擬猜數字決策樹 ===");
        Scanner scanner = new Scanner(System.in);
        simulateDecisionTree(q1, scanner);
    }
}
