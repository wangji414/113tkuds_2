import java.util.*;

public class AVLLeaderboardSystem {
    // 排序：分數高 -> 低；若分數相同，ID 小者在前
    static class Key implements Comparable<Key> {
        int score;
        int id;
        Key(int s,int i){ score=s; id=i; }
        public int compareTo(Key o){
            if(this.score != o.score) return o.score - this.score; // 高分在左（降序）
            return this.id - o.id;
        }
        public String toString(){ return "(" + id + ":" + score + ")"; }
    }

    static class Node {
        Key key;
        int height = 1;
        int size = 1; // 子樹節點數
        Node left, right;
        Node(Key k){ key=k; }
    }

    private Node root;
    private Map<Integer,Integer> scoreById = new HashMap<>();

    // ====== 公開 API ======
    public void addOrUpdateScore(int playerId, int delta){
        int newScore = scoreById.getOrDefault(playerId, 0) + delta;
        setScore(playerId, newScore);
    }
    public void setScore(int playerId, int score){
        // 若已有舊分數，先刪
        if(scoreById.containsKey(playerId)){
            int old = scoreById.get(playerId);
            root = delete(root, new Key(old, playerId));
        }
        // 插入新分數
        scoreById.put(playerId, score);
        root = insert(root, new Key(score, playerId));
    }
    // 取得玩家名次（第 1 名 = 1）
    public int getRank(int playerId){
        Integer s = scoreById.get(playerId);
        if(s==null) return -1;
        return rank(root, new Key(s, playerId));
    }
    // 取得前 K 名（回傳 (id:score)）
    public List<Key> topK(int k){
        List<Key> res = new ArrayList<>();
        collectTopK(root, k, res);
        return res;
    }

    // ====== AVL with size ======
    private int h(Node n){ return n==null?0:n.height; }
    private int sz(Node n){ return n==null?0:n.size; }
    private void upd(Node n){ n.height = Math.max(h(n.left), h(n.right)) + 1; n.size = sz(n.left)+sz(n.right)+1; }
    private int bf(Node n){ return n==null?0:h(n.left)-h(n.right); }
    private Node rotR(Node y){ Node x=y.left,t2=x.right; x.right=y; y.left=t2; upd(y); upd(x); return x; }
    private Node rotL(Node x){ Node y=x.right,t2=y.left; y.left=x; x.right=t2; upd(x); upd(y); return y; }

    private Node insert(Node n, Key k){
        if(n==null) return new Node(k);
        if(k.compareTo(n.key) < 0) n.left = insert(n.left,k);
        else if(k.compareTo(n.key) > 0) n.right = insert(n.right,k);
        else return n; // 同 key（同分數同 id）不重複插入
        upd(n);
        int b=bf(n);
        if(b>1 && k.compareTo(n.left.key) < 0) return rotR(n);
        if(b<-1 && k.compareTo(n.right.key) > 0) return rotL(n);
        if(b>1 && k.compareTo(n.left.key) > 0){ n.left=rotL(n.left); return rotR(n); }
        if(b<-1 && k.compareTo(n.right.key) < 0){ n.right=rotR(n.right); return rotL(n); }
        return n;
    }

    private Node min(Node n){ while(n.left!=null) n=n.left; return n; }

    private Node delete(Node n, Key k){
        if(n==null) return null;
        int cmp = k.compareTo(n.key);
        if(cmp < 0) n.left = delete(n.left,k);
        else if(cmp > 0) n.right = delete(n.right,k);
        else {
            if(n.left==null || n.right==null){
                n = (n.left!=null) ? n.left : n.right;
            }else{
                Node t = min(n.right);
                n.key = t.key;
                n.right = delete(n.right, t.key);
            }
        }
        if(n==null) return null;
        upd(n);
        int b=bf(n);
        if(b>1 && bf(n.left)>=0) return rotR(n);
        if(b>1 && bf(n.left)<0){ n.left=rotL(n.left); return rotR(n); }
        if(b<-1 && bf(n.right)<=0) return rotL(n);
        if(b<-1 && bf(n.right)>0){ n.right=rotR(n.right); return rotL(n); }
        return n;
    }

    // rank：節點在「降序序列」中的第幾名（1-based）
    private int rank(Node n, Key k){
        if(n==null) return 0; // 不存在
        int cmp = k.compareTo(n.key);
        if(cmp < 0){
            // 要找的在左子樹（更前面），名次就在左子樹
            return rank(n.left, k);
        }else if(cmp > 0){
            // 在右子樹（更後面），要加上左子樹大小 + 1（含自己）再繼續
            return sz(n.left) + 1 + rank(n.right, k);
        }else{
            // 正好是自己：左子樹大小 + 1
            return sz(n.left) + 1;
        }
    }

    // 取前 K 名（中序方向改成「左=高分」）
    private void collectTopK(Node n, int k, List<Key> out){
        if(n==null || out.size()>=k) return;
        collectTopK(n.left, k, out);
        if(out.size()<k) out.add(n.key);
        collectTopK(n.right, k, out);
    }

    public static void main(String[] args) {
        AVLLeaderboardSystem lb = new AVLLeaderboardSystem();
        lb.setScore(101, 1200);
        lb.setScore(102, 800);
        lb.setScore(103, 1500);
        lb.addOrUpdateScore(102, 500); // 102 -> 1300

        System.out.println("Top2: " + lb.topK(2)); // 103, 102
        System.out.println("Rank of 103: " + lb.getRank(103)); // 1
        System.out.println("Rank of 101: " + lb.getRank(101)); // 2 or 3 視更新後
        System.out.println("Rank of 102: " + lb.getRank(102)); // 2
    }
}
