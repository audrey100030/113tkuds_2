/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author audre
 */
public class BinaryTreeBasicOperations{
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val){
            this.val = val;
        }
    }
    
    static class SumCount{
        int sum = 0;
        int count = 0;
    }

    public static void computeSumAndCount(TreeNode node, SumCount result){
        if(node == null) return;
        result.sum += node.val;
        result.count++;
        computeSumAndCount(node.left, result);
        computeSumAndCount(node.right, result);
    }
    
    public static int findMax(TreeNode node){
        if(node == null) return Integer.MIN_VALUE;
        int leftMax = findMax(node.left);
        int rightMax = findMax(node.right);
        return max3(node.val, leftMax, rightMax);
    }

    public static int findMin(TreeNode node){
        if(node == null) return Integer.MAX_VALUE;
        int leftMin = findMin(node.left);
        int rightMin = findMin(node.right);
        return min3(node.val, leftMin, rightMin);
    }

    public static int max3(int a, int b, int c){
        int max = a;
        if(b > max) max = b;
        if(c > max) max = c;
        return max;
    }

    public static int min3(int a, int b, int c){
        int min = a;
        if(b < min) min = b;
        if(c < min) min = c;
        return min;
    }
    
    public static int getMaxWidth(TreeNode root){
        if(root == null) return 0;

        TreeNode[] queue = new TreeNode[100];
        int front = 0, rear = 0;
        queue[rear++] = root;
        int maxWidth = 0;

        while (front < rear){
            int levelSize = rear - front;
            if(levelSize > maxWidth) maxWidth = levelSize;

            int currentLevelEnd = rear;
            while(front < currentLevelEnd){
                TreeNode node = queue[front++];
                if(node.left != null) queue[rear++] = node.left;
                if(node.right != null) queue[rear++] = node.right;
            }
        }
        return maxWidth;
    }
    
    public static boolean isCompleteBinaryTree(TreeNode root){
        if (root == null) return true;
        TreeNode[] queue = new TreeNode[100];
        int front = 0, rear = 0;
        queue[rear++] = root;
        boolean foundNull = false;
        while(front < rear){
            TreeNode node = queue[front++];
            if(node == null) {
                foundNull = true;
            }else{
                if(foundNull) return false;
                queue[rear++] = node.left;
                queue[rear++] = node.right;
            }
        }
        return true;
    }
    
    public static void main(String[] args){
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(7);
        root.right.right = new TreeNode(20);
        
        //總和與平均
        SumCount result = new SumCount();
        computeSumAndCount(root, result);
        System.out.println("Total Sum: " + result.sum);
        System.out.println("Average: " + ((double) result.sum / result.count));
        
        //最大與最小值
        System.out.println("Max Value: " + findMax(root));
        System.out.println("Min Value: " + findMin(root));
        
        //最大寬度
        System.out.println("Max Width: " + getMaxWidth(root));
        
        //是否為完全二元樹
        System.out.println("Is Complete Binary Tree: " + isCompleteBinaryTree(root));
    }
}
