/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author audre
 */
// 持久化 AVL 樹
import java.util.*;

public class PersistentAVLExercise {

    static class Node{
        final int value;
        final Node left;
        final Node right;
        final int height;

        Node(int value, Node left, Node right){
            this.value = value;
            this.left = left;
            this.right = right;
            this.height = 1 + Math.max(getHeight(left), getHeight(right));
        }

        static int getHeight(Node node){
            return node == null ? 0 : node.height;
        }
    }

    static class PersistentAVL{
        List<Node> versions = new ArrayList<>();

        public PersistentAVL() {
            versions.add(null); 
        }

        // 插入並產生新版本
        public void insert(int value){
            Node newRoot = insertRec(versions.get(versions.size() - 1), value);
            versions.add(newRoot);
        }

        private Node insertRec(Node node, int value){
            if (node == null){
                return new Node(value, null, null);
            }

            if(value < node.value){
                Node newLeft = insertRec(node.left, value);
                node = balance(new Node(node.value, newLeft, node.right));
            }else if(value > node.value) {
                Node newRight = insertRec(node.right, value);
                node = balance(new Node(node.value, node.left, newRight));
            }else{
                return node; 
            }

            return node;
        }

       
        private Node balance(Node node){
            int balanceFactor = Node.getHeight(node.left) - Node.getHeight(node.right);

            if(balanceFactor > 1 && 
                Node.getHeight(node.left.left) >= Node.getHeight(node.left.right)){
                return rotateRight(node);
            }
            if(balanceFactor > 1 && 
                Node.getHeight(node.left.left) < Node.getHeight(node.left.right)) {
                return rotateRight(new Node(node.value, rotateLeft(node.left), node.right));
            }
            if(balanceFactor < -1 && 
                Node.getHeight(node.right.right) >= Node.getHeight(node.right.left)){
                return rotateLeft(node);
            }
            if(balanceFactor < -1 && 
                Node.getHeight(node.right.right) < Node.getHeight(node.right.left)){
                return rotateLeft(new Node(node.value, node.left, rotateRight(node.right)));
            }

            return node;
        }

        private Node rotateRight(Node y){
            Node x = y.left;
            Node T2 = x.right;
            return new Node(x.value, x.left, new Node(y.value, T2, y.right));
        }

        private Node rotateLeft(Node x){
            Node y = x.right;
            Node T2 = y.left;
            return new Node(y.value, new Node(x.value, x.left, T2), y.right);
        }

        public void inorder(int version){
            inorderRec(versions.get(version));
            System.out.println();
        }

        private void inorderRec(Node node){
            if(node != null){
                inorderRec(node.left);
                System.out.print(node.value + " ");
                inorderRec(node.right);
            }
        }

        public int getVersionCount(){
            return versions.size();
        }
    }

    public static void main(String[] args){
        PersistentAVL pavl = new PersistentAVL();

        pavl.insert(10); 
        pavl.insert(20); 
        pavl.insert(5);  
        pavl.insert(15);

        System.out.println("版本總數: " + pavl.getVersionCount());

        System.out.print("版本 0 (空樹): ");
        pavl.inorder(0);

        System.out.print("版本 1: ");
        pavl.inorder(1);

        System.out.print("版本 2: ");
        pavl.inorder(2);

        System.out.print("版本 3: ");
        pavl.inorder(3);

        System.out.print("版本 4: ");
        pavl.inorder(4);
    }
}

