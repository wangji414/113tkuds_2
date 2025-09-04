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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 建立 dummy 節點，方便操作
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        // 逐一比較 list1 與 list2 的值，較小的接到新串列後面
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }

        // 把剩下的節點接上去 (其中一個可能還有剩)
        if (list1 != null) current.next = list1;
        if (list2 != null) current.next = list2;

        return dummy.next; // 回傳合併後的串列
    }
}
/*
解題思路：
1. 使用 dummy 節點，避免處理頭節點特殊情況。
2. 用一個指標 current 來建立新串列。
3. 比較 list1 和 list2 當前節點的值，把較小的接到新串列後面。
4. 當其中一個串列走完，直接把另一個剩餘部分接上。
5. 時間複雜度 O(m+n)，空間複雜度 O(1)。
*/
