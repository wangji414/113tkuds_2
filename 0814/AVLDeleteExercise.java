public class AVLDeleteExercise {
    static class Node {
        int key, height = 1;
        Node left, right;
        Node(int k){ key = k; }
    }

    private Node root;

    // 公開 API
    public void insert(int k){ root = insert(root, k); }
    public void delete(int k){ root = delete(root, k); }
    public void printInOrder(){ in(root); System.out.println(); }
    public boolean isValidAVL(){ return checkAVL(root) != -1; }

    // 工具
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
        int b = bf(n);
        if(b>1 && k<n.left.key) return rotR(n);
        if(b<-1 && k>n.right.key) return rotL(n);
        if(b>1 && k>n.left.key){ n.left=rotL(n.left); return rotR(n);}
        if(b<-1 && k<n.right.key){ n.right=rotR(n.right); return rotL(n);}
        return n;
    }

    private Node min(Node n){ while(n.left!=null) n=n.left; return n; }

    private Node delete(Node n, int k){
        if(n==null) return null;
        if(k<n.key) n.left = delete(n.left,k);
        else if(k>n.key) n.right = delete(n.right,k);
        else {
            // 命中：葉子或單子節點
            if(n.left==null || n.right==null){
                n = (n.left!=null)? n.left : n.right; // 直接提上來即可
            }else{
                // 兩子節點：用右子樹最小者
                Node t = min(n.right);
                n.key = t.key;
                n.right = delete(n.right, t.key);
            }
        }
        if(n==null) return null;
        upd(n);
        int b = bf(n);
        if(b>1 && bf(n.left)>=0) return rotR(n);          // LL
        if(b>1 && bf(n.left)<0){ n.left=rotL(n.left); return rotR(n);} // LR
        if(b<-1 && bf(n.right)<=0) return rotL(n);        // RR
        if(b<-1 && bf(n.right)>0){ n.right=rotR(n.right); return rotL(n);} // RL
        return n;
    }

    private int checkAVL(Node n){
        if(n==null) return 0;
        int lh = checkAVL(n.left); if(lh==-1) return -1;
        int rh = checkAVL(n.right); if(rh==-1) return -1;
        if(Math.abs(lh-rh)>1) return -1;
        return Math.max(lh,rh)+1;
    }

    private void in(Node n){
        if(n==null) return;
        in(n.left);
        System.out.print(n.key+"("+bf(n)+") ");
        in(n.right);
    }

    public static void main(String[] args) {
        AVLDeleteExercise t = new AVLDeleteExercise();
        int[] a = {10,20,30,40,50,25};
        for(int x:a) t.insert(x);
        System.out.print("插入後："); t.printInOrder();
        t.delete(40); System.out.print("刪 40："); t.printInOrder();
        t.delete(10); System.out.print("刪 10："); t.printInOrder();
        t.delete(25); System.out.print("刪 25："); t.printInOrder();
        System.out.println("有效 AVL？ " + t.isValidAVL());
    }
}
