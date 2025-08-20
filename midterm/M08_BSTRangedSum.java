package midterm;

import java.util.*;

public class M08_BSTRangedSum {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n == 0) {
            System.out.println("Sum: 0");
            return;
        }

        List<Integer> vals = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            vals.add(sc.nextInt());
        }

        TreeNode root = buildTree(vals);

        int L = sc.nextInt();
        int R = sc.nextInt();

        long sum = rangeSumBST(root, L, R);
        System.out.println("Sum: " + sum);
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


    static long rangeSumBST(TreeNode node, int L, int R) {
        if (node == null) return 0;

        if (node.val < L) {
            return rangeSumBST(node.right, L, R);
        } else if (node.val > R) {
            return rangeSumBST(node.left, L, R);
        } else {
            return node.val
                 + rangeSumBST(node.left, L, R)
                 + rangeSumBST(node.right, L, R);
        }
    }
}

