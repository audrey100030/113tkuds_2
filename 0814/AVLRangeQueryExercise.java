/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author audre
 */
// 範圍查詢
import java.util.*;

public class AVLRangeQueryExercise {

    static class Node{
        int value;
        Node left, right;
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

        int getBalance(Node node){
            if(node == null) return 0;
            return getHeight(node.left) - getHeight(node.right);
        }

        Node rotateLeft(Node x){
            Node y = x.right;
            Node T2 = y.left;
            y.left = x;
            x.right = T2;
            updateHeight(x);
            updateHeight(y);
            return y;
        }

        Node rotateRight(Node y){
            Node x = y.left;
            Node T2 = x.right;
            x.right = y;
            y.left = T2;
            updateHeight(y);
            updateHeight(x);
            return x;
        }

        Node insert(Node node, int value){
            if(node == null) return new Node(value);

            if(value < node.value){
                node.left = insert(node.left, value);
            }else if(value > node.value){
                node.right = insert(node.right, value);
            }else{
                return node; 
            }

            updateHeight(node);
            return balance(node);
        }

        Node balance(Node node){
            int balanceFactor = getBalance(node);

            if(balanceFactor > 1 && getBalance(node.left) >= 0){
                return rotateRight(node);
            }
            if (balanceFactor > 1 && getBalance(node.left) < 0){
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
            if(balanceFactor < -1 && getBalance(node.right) <= 0){
                return rotateLeft(node);
            }
            if(balanceFactor < -1 && getBalance(node.right) > 0){
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }
            return node;
        }

        public List<Integer> rangeQuery(int min, int max){
            List<Integer> result = new ArrayList<>();
            rangeQueryRec(root, min, max, result);
            return result;
        }

        private void rangeQueryRec(Node node, int min, int max, List<Integer> result){
            if(node == null) return;

            if(node.value > min){
                rangeQueryRec(node.left, min, max, result);
            }

            if(node.value >= min && node.value <= max){
                result.add(node.value);
            }

            if(node.value < max){
                rangeQueryRec(node.right, min, max, result);
            }
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
        int[] values = {20, 10, 30, 5, 15, 25, 35, 1, 8, 12, 18};

        for(int v : values){
            tree.root = tree.insert(tree.root, v);
        }

        System.out.print("AVL 樹中序遍歷: ");
        tree.inorder(tree.root);
        System.out.println();

        List<Integer> rangeResult = tree.rangeQuery(10, 25);
        System.out.println("範圍 [10, 25] 查詢結果: " + rangeResult);
    }
}

