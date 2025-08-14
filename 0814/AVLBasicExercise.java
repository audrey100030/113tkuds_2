/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author audre
 */
// 練習 1：實作基本操作
public class AVLBasicExercise {

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

        // 插入節點
        public void insert(int value){
            root = insertRec(root, value);
        }

        private Node insertRec(Node node, int value){
            if(node == null){
                return new Node(value);
            }

            if(value < node.value){
                node.left = insertRec(node.left, value);
            }else if(value > node.value){
                node.right = insertRec(node.right, value);
            }else{
                return node; 
            }

            node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

            int balance = getBalance(node);

            if(balance > 1 && value < node.left.value){
                return rotateRight(node);
            }
            if(balance < -1 && value > node.right.value){
                return rotateLeft(node);
            }
            if(balance > 1 && value > node.left.value){
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
            if(balance < -1 && value < node.right.value){
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }
            return node;
        }

        // 搜尋節點
        public boolean search(int value){
            return searchRec(root, value);
        }

        private boolean searchRec(Node node, int value){
            if(node == null) return false;
            if(node.value == value) return true;
            if(value < node.value) {
                return searchRec(node.left, value);
            }else{
                return searchRec(node.right, value);
            }
        }

        // 計算高度
        public int getHeight(Node node){
            if(node == null) return 0;
            return node.height;
        }
        
        private int getBalance(Node node){
            if(node == null) return 0;
            return getHeight(node.left) - getHeight(node.right);
        }

        private Node rotateRight(Node y){
            Node x = y.left;
            Node T2 = x.right;

            x.right = y;
            y.left = T2;

            y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
            x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));

            return x;
        }

        private Node rotateLeft(Node x){
            Node y = x.right;
            Node T2 = y.left;

            y.left = x;
            x.right = T2;

            x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));
            y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));

            return y;
        }

        // 檢查是否為有效的 AVL 樹
        public boolean isAVL(){
            return isAVLRec(root);
        }

        private boolean isAVLRec(Node node){
            if(node == null) return true;

            int balance = getBalance(node);
            if(balance < -1 || balance > 1) return false;

            return isAVLRec(node.left) && isAVLRec(node.right);
        }
    }

    public static void main(String[] args){
        AVLTree tree = new AVLTree();
        int[] values = {10, 20, 30, 40, 50, 25};

        for (int v : values){
            tree.insert(v);
        }

        System.out.println("搜尋 25: " + tree.search(25));
        System.out.println("搜尋 100: " + tree.search(100));
        System.out.println("是否為有效的 AVL 樹: " + tree.isAVL());
        System.out.println("樹的高度: " + tree.getHeight(tree.root));
    }
}
