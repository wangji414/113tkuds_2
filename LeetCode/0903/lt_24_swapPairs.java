class Solution {
    public ListNode swapPairs(ListNode head) {
        // 建立 dummy 節點，方便處理頭節點交換
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (head != null && head.next != null) {
            // 取出要交換的兩個節點
            ListNode first = head;
            ListNode second = head.next;

            // 執行交換
            prev.next = second;
            first.next = second.next;
            second.next = first;

            // 移動指標，處理下一組
            prev = first;
            head = first.next;
        }

        return dummy.next;
    }
}
/*
解題思路：
1. 使用 dummy 節點避免處理頭節點交換的特殊情況。
2. 每次取出兩個節點 (first, second)，調整指標順序完成交換：
   - prev.next → second
   - first.next → second.next
   - second.next → first
3. 更新 prev 與 head 指標，繼續處理下一對節點。
4. 時間複雜度 O(n)，每個節點走訪一次。
5. 空間複雜度 O(1)，只需額外的指標變數。
*/
