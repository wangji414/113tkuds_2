/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 建立 dummy 節點，方便處理刪除頭節點的情況
        ListNode dummy = new ListNode(0, head);
        ListNode fast = dummy;
        ListNode slow = dummy;

        // 先讓 fast 前進 n+1 步，保持間距
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // fast 和 slow 一起移動，直到 fast 到尾端
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // slow 的下一個節點就是要刪除的節點
        slow.next = slow.next.next;

        return dummy.next; // 回傳新的頭節點
    }
}
/*
解題思路：
1. 使用 dummy 節點處理刪除頭節點的特殊情況。
2. 使用雙指針，讓 fast 先移動 n+1 步，保持間隔。
3. 然後 fast 和 slow 一起移動，直到 fast 到尾端。
4. 此時 slow 停在刪除節點的前一個節點，調整指標刪除節點。
5. 時間複雜度 O(L)，L 為鏈結串列長度；空間複雜度 O(1)。
*/
