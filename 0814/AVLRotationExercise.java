public class AVLRotationExercise {
    static class Node {
        int key, height = 1;
        Node left, right;
        Node(int k){ key = k; }
    }

    static int h(Node n){ return n == null ? 0 : n.height; }
    static void upd(Node n){ n.height = Math.max(h(n.left), h(n.right)) + 1; }
    static int bf(Node n){ return n == null ? 0 : h(n.left) - h(n.right); }

    // 右旋
    static Node rightRotate(Node y){
        Node x = y.left, t2 = x.right;
        x.right = y; y.left = t2;
        upd(y); upd(x);
        return x;
    }
    // 左旋
    static Node leftRotate(Node x){
        Node y = x.right, t2 = y.left;
        y.left = x; x.right = t2;
        upd(x); upd(y);
        return y;
    }
    // 左右旋 (LR)
    static Node leftRight(Node z){
        z.left = leftRotate(z.left);
        return rightRotate(z);
    }
    // 右左旋 (RL)
    static Node rightLeft(Node z){
        z.right = rightRotate(z.right);
        return leftRotate(z);
    }

    static void preOrder(Node n){
        if(n==null) return;
        System.out.print(n.key + "(h=" + h(n) + ",bf=" + bf(n) + ") ");
        preOrder(n.left); preOrder(n.right);
    }

    public static void main(String[] args) {
        // LL: 30<-20<-10
        Node ll = new Node(30); ll.left = new Node(20); ll.left.left = new Node(10);
        upd(ll.left.left); upd(ll.left); upd(ll);
        System.out.println("LL 旋轉前："); preOrder(ll); System.out.println();
        ll = rightRotate(ll);
        System.out.println("LL 旋轉後："); preOrder(ll); System.out.println("\n");

        // RR: 10->20->30
        Node rr = new Node(10); rr.right = new Node(20); rr.right.right = new Node(30);
        upd(rr.right.right); upd(rr.right); upd(rr);
        System.out.println("RR 旋轉前："); preOrder(rr); System.out.println();
        rr = leftRotate(rr);
        System.out.println("RR 旋轉後："); preOrder(rr); System.out.println("\n");

        // LR: 30<-10->20
        Node lr = new Node(30); lr.left = new Node(10); lr.left.right = new Node(20);
        upd(lr.left.right); upd(lr.left); upd(lr);
        System.out.println("LR 旋轉前："); preOrder(lr); System.out.println();
        lr = leftRight(lr);
        System.out.println("LR 旋轉後："); preOrder(lr); System.out.println("\n");

        // RL: 10->30<-20
        Node rl = new Node(10); rl.right = new Node(30); rl.right.left = new Node(20);
        upd(rl.right.left); upd(rl.right); upd(rl);
        System.out.println("RL 旋轉前："); preOrder(rl); System.out.println();
        rl = rightLeft(rl);
        System.out.println("RL 旋轉後："); preOrder(rl); System.out.println();
    }
}

