import java.util.PriorityQueue;

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        // 最小堆，根據節點的 val 進行排序
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);

        // 將每個串列的頭節點加入堆中
        for (ListNode node : lists) {
            if (node != null) {
                pq.offer(node);
            }
        }

        // dummy head 方便處理
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        // 不斷取出最小節點，並把它的 next 放回堆中
        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            curr.next = node;
            curr = curr.next;

            if (node.next != null) {
                pq.offer(node.next);
            }
        }

        return dummy.next;
    }
}
/*
解題思路：
1. 使用最小堆 (PriorityQueue) 儲存每個鏈結串列當前的節點。
2. 每次從堆中取出最小節點，接到結果串列後，把它的下一個節點再放回堆中。
3. 直到堆為空，所有節點皆已排序合併完成。
4. 時間複雜度 O(N log k)：
   - N 為所有節點總數，k 為串列數。
   - 每次堆操作需 O(log k)，總共有 N 次插入/刪除操作。
5. 空間複雜度 O(k)，因為堆最多同時存放 k 個節點。
*/
