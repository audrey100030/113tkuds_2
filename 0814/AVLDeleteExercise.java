/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author audre
 */
// 刪除操作實作
public class AVLDeleteExercise {

    static class Node {
        int value;
        Node left, right;
        int height;

        Node(int value) {
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
            if (node == null) return 0;
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

        // 刪除節點
        Node delete(Node node, int value){
            if(node == null) return null;

            if(value < node.value){
                node.left = delete(node.left, value);
            }else if(value > node.value){
                node.right = delete(node.right, value);
            }else{
                // 葉子節點
                if(node.left == null && node.right == null){
                    return null;
                }
                // 只有一個子節點
                else if(node.left == null){
                    return node.right;
                }else if(node.right == null){
                    return node.left;
                }
                //有兩個子節點
                else{
                    Node successor = getMinNode(node.right); // 找後繼
                    node.value = successor.value;
                    node.right = delete(node.right, successor.value);
                }
            }

            updateHeight(node);
            return balance(node);
        }

        Node getMinNode(Node node){
            while (node.left != null){
                node = node.left;
            }
            return node;
        }

        Node balance(Node node){
            int balanceFactor = getBalance(node);

            if(balanceFactor > 1 && getBalance(node.left) >= 0){
                return rotateRight(node);
            }
            if(balanceFactor > 1 && getBalance(node.left) < 0){
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

        void inorder(Node node){
            if(node != null){
                inorder(node.left);
                System.out.print(node.value + " ");
                inorder(node.right);
            }
        }
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        int[] values = {20, 10, 30, 5, 15, 25, 35};

        for (int v : values) {
            tree.root = tree.insert(tree.root, v);
        }

        System.out.print("初始樹: ");
        tree.inorder(tree.root);
        System.out.println();

        tree.root = tree.delete(tree.root, 5);
        System.out.print("刪除葉子(5): ");
        tree.inorder(tree.root);
        System.out.println();

        tree.root = tree.delete(tree.root, 35);
        System.out.print("刪除單子節點(35): ");
        tree.inorder(tree.root);
        System.out.println();

        tree.root = tree.delete(tree.root, 20);
        System.out.print("刪除雙子節點(20): ");
        tree.inorder(tree.root);
        System.out.println();
    }
}

