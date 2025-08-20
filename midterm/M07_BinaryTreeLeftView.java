package midterm;

import java.util.*;

public class M07_BinaryTreeLeftView {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n == 0) {
            System.out.println("LeftView:");
            return;
        }

        List<Integer> vals = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            vals.add(sc.nextInt());
        }

        TreeNode root = buildTree(vals);
        List<Integer> leftView = getLeftView(root);

        System.out.print("LeftView:");
        for (int val : leftView) {
            System.out.print(" " + val);
        }
        System.out.println();
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

    static List<Integer> getLeftView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == 0) res.add(node.val); // 每層最左側

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return res;
    }
}
