package midterm;

import java.util.*;

public class M09_AVLValidate {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n == 0) {
            System.out.println("Valid");
            return;
        }

        List<Integer> vals = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            vals.add(sc.nextInt());
        }

        TreeNode root = buildTree(vals);

        if (!isBST(root, Long.MIN_VALUE, Long.MAX_VALUE)) {
            System.out.println("Invalid BST");
        } else if (!isAVL(root).isAVL) {
            System.out.println("Invalid AVL");
        } else {
            System.out.println("Valid");
        }
        sc.close();
    }

    // 依層序建立二元樹，-1 表 null
    static TreeNode buildTree(List<Integer> vals) {
        if (vals.get(0) == -1) return null;

        TreeNode root = new TreeNode(vals.get(0));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;
        while (i < vals.size()) {
            TreeNode node = queue.poll();
            if (node == null) continue;

            // 左子節點
            if (i < vals.size()) {
                int v = vals.get(i++);
                node.left = (v == -1) ? null : new TreeNode(v);
                queue.offer(node.left);
            }

            // 右子節點
            if (i < vals.size()) {
                int v = vals.get(i++);
                node.right = (v == -1) ? null : new TreeNode(v);
                queue.offer(node.right);
            }
        }
        return root;
    }

    static boolean isBST(TreeNode node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return isBST(node.left, min, node.val) && isBST(node.right, node.val, max);
    }

    // AVL 檢查結果
    static class AVLResult {
        boolean isAVL;
        int height;
        AVLResult(boolean isAVL, int height) {
            this.isAVL = isAVL;
            this.height = height;
        }
    }

    static AVLResult isAVL(TreeNode node) {
        if (node == null) return new AVLResult(true, 0);

        AVLResult left = isAVL(node.left);
        AVLResult right = isAVL(node.right);

        boolean balanced = left.isAVL && right.isAVL && Math.abs(left.height - right.height) <= 1;
        int height = Math.max(left.height, right.height) + 1;

        return new AVLResult(balanced, height);
    }
}
/*
* 檢查 AVL
* Time Complexity: O(n)
* 說明：
* 後序遞迴每個節點一次 -> O(n)
*/
