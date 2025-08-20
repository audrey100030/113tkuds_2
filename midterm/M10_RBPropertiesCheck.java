/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.*;

public class M10_RBPropertiesCheck {
    static class Node {
        int val;
        char color;
        Node left, right;
        Node(int v, char c) {
            this.val = v;
            this.color = c;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] vals = new int[n];
        char[] cols = new char[n];
        for (int i = 0; i < n; i++) {
            vals[i] = sc.nextInt();
            cols[i] = sc.next().charAt(0);
            if (vals[i] == -1) cols[i] = 'B'; 
        }
        sc.close();

        if (cols[0] == 'R') {
            System.out.println("RootNotBlack");
            return;
        }

        for (int i = 0; i < n; i++) {
            if (vals[i] == -1) continue;
            if (cols[i] == 'R') {
                int left = 2 * i + 1, right = 2 * i + 2;
                if (left < n && vals[left] != -1 && cols[left] == 'R') {
                    System.out.println("RedRedViolation at index " + i);
                    return;
                }
                if (right < n && vals[right] != -1 && cols[right] == 'R') {
                    System.out.println("RedRedViolation at index " + i);
                    return;
                }
            }
        }

        if (!checkBlackHeight(0, vals, cols).isValid) {
            System.out.println("BlackHeightMismatch");
            return;
        }

        System.out.println("RB Valid");
    }

    static class Result {
        boolean isValid;
        int blackHeight;
        Result(boolean v, int h) {
            isValid = v; blackHeight = h;
        }
    }

    static Result checkBlackHeight(int idx, int[] vals, char[] cols) {
        if (idx >= vals.length || vals[idx] == -1) {
            return new Result(true, 1); // NIL 節點 = 黑，高度 +1
        }

        Result left = checkBlackHeight(2 * idx + 1, vals, cols);
        Result right = checkBlackHeight(2 * idx + 2, vals, cols);

        if (!left.isValid || !right.isValid) return new Result(false, 0);
        if (left.blackHeight != right.blackHeight) return new Result(false, 0);

        int add = (cols[idx] == 'B') ? 1 : 0;
        return new Result(true, left.blackHeight + add);
    }
}

