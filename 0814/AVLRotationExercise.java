/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author audre
 */
// 旋轉操作實作
public class AVLRotationExercise {

    static class Node{
        int value;
        Node left;
        Node right;
        int height;

        Node(int value){
            this.value = value;
            this.height = 1;
        }
    }

    static class AVLTree{
        Node root;

        int getHeight(Node node){
            if (node == null) return 0;
            return node.height;
        }

        void updateHeight(Node node){
            node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        }

        // 右旋
        Node rotateRight(Node y){
            Node x = y.left;
            Node T2 = x.right;

            x.right = y;
            y.left = T2;

            updateHeight(y);
            updateHeight(x);

            return x;
        }

        // 左旋
        Node rotateLeft(Node x){
            Node y = x.right;
            Node T2 = y.left;

            y.left = x;
            x.right = T2;

            updateHeight(x);
            updateHeight(y);

            return y;
        }

        // 左右旋
        Node rotateLeftRight(Node node){
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // 右左旋 
        Node rotateRightLeft(Node node){
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        Node insertForTest(Node node, int value){
            if(node == null) return new Node(value);

            if(value < node.value){
                node.left = insertForTest(node.left, value);
            }else if(value > node.value){
                node.right = insertForTest(node.right, value);
            }else{
                return node;
            }

            updateHeight(node);
            return node;
        }

        void inorder(Node node){
            if(node != null){
                inorder(node.left);
                System.out.print(node.value + " ");
                inorder(node.right);
            }
        }
    }

    public static void main(String[] args){
        AVLTree tree = new AVLTree();

        Node root1 = null;
        int[] case1 = {30, 20, 10}; 
        for (int v : case1) root1 = tree.insertForTest(root1, v);
        System.out.print("原樹 (LL): "); tree.inorder(root1); System.out.println();
        root1 = tree.rotateRight(root1);
        System.out.print("右旋後: "); tree.inorder(root1); System.out.println("\n");


        Node root2 = null;
        int[] case2 = {10, 20, 30};
        for (int v : case2) root2 = tree.insertForTest(root2, v);
        System.out.print("原樹 (RR): "); tree.inorder(root2); System.out.println();
        root2 = tree.rotateLeft(root2);
        System.out.print("左旋後: "); tree.inorder(root2); System.out.println("\n");

        Node root3 = null;
        int[] case3 = {30, 10, 20}; 
        for (int v : case3) root3 = tree.insertForTest(root3, v);
        System.out.print("原樹 (LR): "); tree.inorder(root3); System.out.println();
        root3 = tree.rotateLeftRight(root3);
        System.out.print("左右旋後: "); tree.inorder(root3); System.out.println("\n");

        Node root4 = null;
        int[] case4 = {10, 30, 20}; 
        for (int v : case4) root4 = tree.insertForTest(root4, v);
        System.out.print("原樹 (RL): "); tree.inorder(root4); System.out.println();
        root4 = tree.rotateRightLeft(root4);
        System.out.print("右左旋後: "); tree.inorder(root4); System.out.println();
    }
}

