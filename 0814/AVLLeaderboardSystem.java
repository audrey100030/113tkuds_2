/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author audre
 */
// AVL 樹應用 - 排行榜系統
import java.util.*;

public class AVLLeaderboardSystem {

    static class Node{
        String player;
        int score;
        Node left, right;
        int height;
        int size; 

        Node(String player, int score){
            this.player = player;
            this.score = score;
            this.height = 1;
            this.size = 1;
        }
    }

    static class AVLLeaderboard{
        Node root;
        Map<String, Integer> playerScores = new HashMap<>();

        int getHeight(Node node){
            return node == null ? 0 : node.height;
        }

        int getSize(Node node){
            return node == null ? 0 : node.size;
        }

        void updateNode(Node node){
            node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
            node.size = 1 + getSize(node.left) + getSize(node.right);
        }

        int getBalance(Node node){
            return node == null ? 0 : getHeight(node.left) - getHeight(node.right);
        }

        Node rotateRight(Node y){
            Node x = y.left;
            Node T2 = x.right;
            x.right = y;
            y.left = T2;
            updateNode(y);
            updateNode(x);
            return x;
        }

        Node rotateLeft(Node x){
            Node y = x.right;
            Node T2 = y.left;
            y.left = x;
            x.right = T2;
            updateNode(x);
            updateNode(y);
            return y;
        }

        int compare(String p1, int s1, String p2, int s2){
            if(s1 != s2) return s2 - s1; 
            return p1.compareTo(p2); 
        }

        Node insert(Node node, String player, int score){
            if(node == null) return new Node(player, score);

            if(compare(player, score, node.player, node.score) < 0){
                node.left = insert(node.left, player, score);
            }else if(compare(player, score, node.player, node.score) > 0){
                node.right = insert(node.right, player, score);
            }else{
                return node; 
            }

            updateNode(node);
            return balance(node);
        }

        Node balance(Node node){
            int bf = getBalance(node);

            if(bf > 1 && getBalance(node.left) >= 0) return rotateRight(node);
            if(bf > 1 && getBalance(node.left) < 0) {
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
            if(bf < -1 && getBalance(node.right) <= 0) return rotateLeft(node);
            if(bf < -1 && getBalance(node.right) > 0) {
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }
            return node;
        }

        void addPlayer(String player, int score){
            if(playerScores.containsKey(player)){
                System.out.println("玩家已存在，請用 updateScore 更新分數。");
                return;
            }
            root = insert(root, player, score);
            playerScores.put(player, score);
        }

        void updateScore(String player, int newScore){
            if(!playerScores.containsKey(player)) {
                System.out.println("玩家不存在，請先添加玩家。");
                return;
            }
            int oldScore = playerScores.get(player);
            root = delete(root, player, oldScore);
            root = insert(root, player, newScore);
            playerScores.put(player, newScore);
        }

        Node delete(Node node, String player, int score){
            if(node == null) return null;

            if(compare(player, score, node.player, node.score) < 0){
                node.left = delete(node.left, player, score);
            }else if(compare(player, score, node.player, node.score) > 0){
                node.right = delete(node.right, player, score);
            }else{
                if (node.left == null || node.right == null){
                    node = (node.left != null) ? node.left : node.right;
                }else{
                    Node successor = getMinNode(node.right);
                    node.player = successor.player;
                    node.score = successor.score;
                    node.right = delete(node.right, successor.player, successor.score);
                }
            }

            if(node != null){
                updateNode(node);
                node = balance(node);
            }
            return node;
        }

        Node getMinNode(Node node){
            while (node.left != null) node = node.left;
            return node;
        }

        // 查詢排名
        int getRank(String player){
            if(!playerScores.containsKey(player)) return -1;
            return getRankRec(root, player, playerScores.get(player)) + 1;
        }

        int getRankRec(Node node, String player, int score){
            if(node == null) return 0;

            if(compare(player, score, node.player, node.score) < 0){
                return getRankRec(node.left, player, score);
            }else if(compare(player, score, node.player, node.score) > 0){
                return getSize(node.left) + 1 + getRankRec(node.right, player, score);
            }else{
                return getSize(node.left);
            }
        }

        // 查詢前 K 名玩家
        List<String> getTopK(int k){
            List<String> result = new ArrayList<>();
            getTopKRec(root, k, result);
            return result;
        }

        void getTopKRec(Node node, int k, List<String> result){
            if(node == null || result.size() >= k) return;
            getTopKRec(node.left, k, result);
            if(result.size() < k) result.add(node.player + " (" + node.score + ")");
            getTopKRec(node.right, k, result);
        }
    }

    public static void main(String[] args){
        AVLLeaderboard leaderboard = new AVLLeaderboard();

        leaderboard.addPlayer("Alice", 95);
        leaderboard.addPlayer("Bob", 85);
        leaderboard.addPlayer("Charlie", 90);
        leaderboard.addPlayer("David", 100);

        System.out.println("前 3 名玩家: " + leaderboard.getTopK(3));
        System.out.println("Charlie 排名: " + leaderboard.getRank("Charlie"));

        leaderboard.updateScore("Bob", 99);
        System.out.println("更新 Bob 分數後，前 3 名玩家: " + leaderboard.getTopK(3));
        System.out.println("Bob 排名: " + leaderboard.getRank("Bob"));
    }
}

