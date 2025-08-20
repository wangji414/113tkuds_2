package midterm;

import java.util.*;

public class M10_RBPropertiesCheck {
    static class Node {
        int val;
        char color; // 'R' 或 'B'
        Node(int val, char color) {
            this.val = val;
            this.color = color;
        }
    }

    static boolean violationFound = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Node[] tree = new Node[n];

        for (int i = 0; i < n; i++) {
            int val = sc.nextInt();
            String col = sc.next();
            char color = col.charAt(0);
            if (val == -1) color = 'B'; // NIL 視為黑
            tree[i] = new Node(val, color);
        }

        // 1. 根節點為黑
        if (tree[0].color != 'B') {
            System.out.println("RootNotBlack");
            return;
        }

        // 2 & 3. 遍歷檢查
        int blackHeight = checkRB(tree, 0);
        if (violationFound) return;

        // 無違規
        System.out.println("RB Valid");
    }

    static int checkRB(Node[] tree, int idx) {
        if (idx >= tree.length || tree[idx].val == -1) return 1; // NIL，黑高度為1

        Node node = tree[idx];
        int leftIdx = 2 * idx + 1;
        int rightIdx = 2 * idx + 2;

        // 2. 紅紅相鄰
        if (node.color == 'R') {
            if ((leftIdx < tree.length && tree[leftIdx].color == 'R' && tree[leftIdx].val != -1) ||
                (rightIdx < tree.length && tree[rightIdx].color == 'R' && tree[rightIdx].val != -1)) {
                System.out.println("RedRedViolation at index " + idx);
                violationFound = true;
                return 0;
            }
        }

        int leftBH = checkRB(tree, leftIdx);
        if (violationFound) return 0;
        int rightBH = checkRB(tree, rightIdx);
        if (violationFound) return 0;

        // 3. 左右子樹黑高度一致
        if (leftBH != rightBH) {
            System.out.println("BlackHeightMismatch");
            violationFound = true;
            return 0;
        }

        // 當前節點為黑 黑高度 +1
        return node.color == 'B' ? leftBH + 1 : leftBH;
    }
}

/*
* Time Complexity: O(n)
* 說明：
* 每個節點訪問一次 → O(n)
* 左右子樹遞迴，計算黑高度與檢查紅紅 → O(n)
*/

