import java.util.*;

public class AVLBasicExercise {
    // 內部節點
    static class Node {
        int key;
        int height;
        Node left, right;
        Node(int k) { key = k; height = 1; }
    }

    private Node root;

    // 公開 API
    public void insert(int key) { root = insert(root, key); }
    public boolean search(int key) { return search(root, key); }
    public int height() { return height(root); }
    public boolean isValidAVL() { return checkAVL(root) != -1; }

    // ===== 基本工具 =====
    private int height(Node n) { return n == null ? 0 : n.height; }
    private int balance(Node n) { return n == null ? 0 : height(n.left) - height(n.right); }
    private void update(Node n) { n.height = Math.max(height(n.left), height(n.right)) + 1; }

    // 旋轉
    private Node rotateRight(Node y) {
        Node x = y.left;
        Node t2 = x.right;
        x.right = y;
        y.left = t2;
        update(y);
        update(x);
        return x;
    }
    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node t2 = y.left;
        y.left = x;
        x.right = t2;
        update(x);
        update(y);
        return y;
    }

    // 插入（先 BST，再調整）
    private Node insert(Node node, int key) {
        if (node == null) return new Node(key);
        if (key < node.key) node.left = insert(node.left, key);
        else if (key > node.key) node.right = insert(node.right, key);
        else return node; // 忽略重複

        update(node);
        int bf = balance(node);

        // LL
        if (bf > 1 && key < node.left.key) return rotateRight(node);
        // RR
        if (bf < -1 && key > node.right.key) return rotateLeft(node);
        // LR
        if (bf > 1 && key > node.left.key) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        // RL
        if (bf < -1 && key < node.right.key) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        return node;
    }

    private boolean search(Node node, int key) {
        if (node == null) return false;
        if (key == node.key) return true;
        return key < node.key ? search(node.left, key) : search(node.right, key);
    }

    // 回傳高度；不平衡則回傳 -1
    private int checkAVL(Node node) {
        if (node == null) return 0;
        int lh = checkAVL(node.left);
        if (lh == -1) return -1;
        int rh = checkAVL(node.right);
        if (rh == -1) return -1;
        if (Math.abs(lh - rh) > 1) return -1;
        return Math.max(lh, rh) + 1;
    }

    // 中序列印（含平衡因子）
    public void printInOrder() { printInOrder(root); System.out.println(); }
    private void printInOrder(Node n) {
        if (n == null) return;
        printInOrder(n.left);
        System.out.print(n.key + "(" + balance(n) + ") ");
        printInOrder(n.right);
    }

    // demo
    public static void main(String[] args) {
        AVLBasicExercise avl = new AVLBasicExercise();
        int[] arr = {10,20,30,40,50,25};
        for (int x : arr) avl.insert(x);
        System.out.print("中序："); avl.printInOrder();
        System.out.println("高度：" + avl.height());
        System.out.println("search 25 = " + avl.search(25));
        System.out.println("search 99 = " + avl.search(99));
        System.out.println("是有效 AVL？ " + avl.isValidAVL());
    }
}
