import java.util.*;

public class PersistentAVLExercise {
    // 不可變節點（immutable）
    static class Node {
        final int key;
        final int height;
        final Node left, right;
        Node(int key, Node left, Node right){
            this.key = key; this.left = left; this.right = right;
            this.height = Math.max(h(left), h(right)) + 1;
        }
    }

    // 版本：每次插入都返回新的根
    private final List<Node> versions = new ArrayList<>();

    public PersistentAVLExercise(){
        versions.add(null); // v0: 空樹
    }

    public int latestVersion(){ return versions.size()-1; }

    // 在指定版本插入，產生新版本
    public int insertAtVersion(int version, int key){
        Node base = versions.get(version);
        Node nr = insert(base, key);
        versions.add(nr);
        return versions.size()-1;
    }

    public List<Integer> inorderAt(int version){
        List<Integer> out = new ArrayList<>();
        inorder(versions.get(version), out);
        return out;
    }

    // ====== 基本工具（純函數式） ======
    private static int h(Node n){ return n==null?0:n.height; }
    private static int bf(Node n){ return n==null?0:h(n.left)-h(n.right); }

    private static Node node(int key, Node l, Node r){ return new Node(key,l,r); }

    // 右旋（回傳新子樹根）
    private static Node rotR(Node y){
        Node x = y.left;
        Node t2 = x.right;
        // 注意：要創造新節點，不能修改舊節點
        Node newY = node(y.key, t2, y.right);
        Node newX = node(x.key, x.left, newY);
        return newX;
    }
    // 左旋
    private static Node rotL(Node x){
        Node y = x.right;
        Node t2 = y.left;
        Node newX = node(x.key, x.left, t2);
        Node newY = node(y.key, newX, y.right);
        return newY;
    }

    private Node insert(Node n, int key){
        if(n==null) return node(key, null, null);
        if(key < n.key){
            Node l = insert(n.left, key);
            n = node(n.key, l, n.right);
        }else if(key > n.key){
            Node r = insert(n.right, key);
            n = node(n.key, n.left, r);
        }else{
            return n; // duplicate：直接沿用舊節點
        }
        int b = bf(n);
        if(b>1 && key < n.left.key) return rotR(n);            // LL
        if(b<-1 && key > n.right.key) return rotL(n);          // RR
        if(b>1 && key > n.left.key)  return rotR(node(n.key, rotL(n.left), n.right)); // LR
        if(b<-1 && key < n.right.key) return rotL(node(n.key, n.left, rotR(n.right))); // RL
        return n;
    }

    private void inorder(Node n, List<Integer> out){
        if(n==null) return;
        inorder(n.left, out);
        out.add(n.key);
        inorder(n.right, out);
    }

    public static void main(String[] args) {
        PersistentAVLExercise p = new PersistentAVLExercise();
        int v1 = p.insertAtVersion(0, 30); // v1
        int v2 = p.insertAtVersion(v1, 10); // v2
        int v3 = p.insertAtVersion(v2, 20); // v3（會觸發旋轉）
        int v4 = p.insertAtVersion(v3, 40); // v4
        int v5 = p.insertAtVersion(v4, 35); // v5

        System.out.println("v0: " + p.inorderAt(0)); // []
        System.out.println("v1: " + p.inorderAt(v1));
        System.out.println("v2: " + p.inorderAt(v2));
        System.out.println("v3: " + p.inorderAt(v3));
        System.out.println("v4: " + p.inorderAt(v4));
        System.out.println("v5: " + p.inorderAt(v5));
    }
}

