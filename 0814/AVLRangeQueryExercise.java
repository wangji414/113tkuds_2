import java.util.*;

public class AVLRangeQueryExercise {
    static class Node {
        int key, height=1;
        Node left, right;
        Node(int k){ key = k; }
    }
    private Node root;

    public void insert(int k){ root = insert(root,k); }
    public List<Integer> rangeQuery(int min, int max){
        List<Integer> ans = new ArrayList<>();
        range(root, min, max, ans);
        return ans;
    }

    // ====== 基本 AVL 插入（省略刪除） ======
    private int h(Node n){ return n==null?0:n.height; }
    private void upd(Node n){ n.height = Math.max(h(n.left), h(n.right)) + 1; }
    private int bf(Node n){ return n==null?0:h(n.left)-h(n.right); }
    private Node rotR(Node y){ Node x=y.left,t2=x.right; x.right=y; y.left=t2; upd(y); upd(x); return x; }
    private Node rotL(Node x){ Node y=x.right,t2=y.left; y.left=x; x.right=t2; upd(x); upd(y); return y; }

    private Node insert(Node n, int k){
        if(n==null) return new Node(k);
        if(k<n.key) n.left = insert(n.left,k);
        else if(k>n.key) n.right = insert(n.right,k);
        else return n;
        upd(n);
        int b=bf(n);
        if(b>1 && k<n.left.key) return rotR(n);
        if(b<-1 && k>n.right.key) return rotL(n);
        if(b>1 && k>n.left.key){ n.left=rotL(n.left); return rotR(n); }
        if(b<-1 && k<n.right.key){ n.right=rotR(n.right); return rotL(n); }
        return n;
    }

    // 中序 + 剪枝
    private void range(Node n, int min, int max, List<Integer> out){
        if(n==null) return;
        if(min < n.key) range(n.left, min, max, out);
        if(min <= n.key && n.key <= max) out.add(n.key);
        if(max > n.key) range(n.right, min, max, out);
    }

    public static void main(String[] args) {
        AVLRangeQueryExercise t = new AVLRangeQueryExercise();
        int[] data = {30,10,50,5,20,40,60,25,35,45};
        for(int x:data) t.insert(x);
        System.out.println(t.rangeQuery(15, 45)); // 期望：20,25,30,35,40,45（排序）
        System.out.println(t.rangeQuery(0, 9));   // 期望：5
        System.out.println(t.rangeQuery(55, 100));// 期望：60
    }
}

