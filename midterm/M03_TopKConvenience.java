package midterm;

import java.util.*;

public class M03_TopKConvenience {
    static class Item {
        String name;
        int qty;
        int index; //保留輸入順序

        Item(String name, int qty, int index) {
            this.name = name;
            this.qty = qty;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int K = sc.nextInt();
        sc.nextLine(); // 吃掉換行

        PriorityQueue<Item> pq = new PriorityQueue<>(new Comparator<Item>() {
            @Override
            public int compare(Item a, Item b) {
                if (a.qty != b.qty) return a.qty - b.qty; 
                return a.index - b.index; // 輸入順序
            }
        });

        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int qty = sc.nextInt();
            Item item = new Item(name, qty, i);

            if (pq.size() < K) {
                pq.offer(item);
            } else {
                // 若當前比堆頂大->替換
                if (compareForTopK(item, pq.peek()) > 0) {
                    pq.poll();
                    pq.offer(item);
                }
            }
        }

        // qty由大到小 若同qty則依輸入順序
        List<Item> result = new ArrayList<>(pq);
        result.sort((a, b) -> {
            if (b.qty != a.qty) return b.qty - a.qty;
            return a.index - b.index;
        });

        for (Item it : result) {
            System.out.println(it.name + " " + it.qty);
        }
        
        sc.close();
    }

    // qty大者優先 若相同依輸入順序
    static int compareForTopK(Item a, Item b) {
        if (a.qty != b.qty) return a.qty - b.qty;
        return b.index - a.index; // 保證先輸入的優先留下
    }
}
/*
 * Time Complexity: O(n log K)
 * 說明：
 * 每次插入或替換堆 (heap) -> O(log K)
 * 共 n 筆資料 -> O(n log K)
 */