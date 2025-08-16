public class AVLRotations {
    
    // 右旋操作
    public static AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;
        
        // 執行旋轉
        x.right = y;
        y.left = T2;
        
        // 更新高度
        y.updateHeight();
        x.updateHeight();
        
        return x; // 新的根節點
    }
    
    // 左旋操作
    public static AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;
        
        // 執行旋轉
        y.left = x;
        x.right = T2;
        
        // 更新高度
        x.updateHeight();
        y.updateHeight();
        
        return y; // 新的根節點
    }

    // 前序列印 (方便觀察樹結構)
    public static void preOrder(AVLNode node) {
        if (node != null) {
            System.out.print(node.data + "(h=" + node.height + ") ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public static void main(String[] args) {
        // 測試右旋
        System.out.println("=== 測試右旋 ===");
        AVLNode root = new AVLNode(30);
        root.left = new AVLNode(20);
        root.left.left = new AVLNode(10);

        // 更新高度
        root.left.left.updateHeight();
        root.left.updateHeight();
        root.updateHeight();

        System.out.println("旋轉前 (前序):");
        preOrder(root);

        root = rightRotate(root);

        System.out.println("\n旋轉後 (前序):");
        preOrder(root);

        // 測試左旋
        System.out.println("\n\n=== 測試左旋 ===");
        AVLNode root2 = new AVLNode(10);
        root2.right = new AVLNode(20);
        root2.right.right = new AVLNode(30);

        root2.right.right.updateHeight();
        root2.right.updateHeight();
        root2.updateHeight();

        System.out.println("旋轉前 (前序):");
        preOrder(root2);

        root2 = leftRotate(root2);

        System.out.println("\n旋轉後 (前序):");
        preOrder(root2);
    }
}
