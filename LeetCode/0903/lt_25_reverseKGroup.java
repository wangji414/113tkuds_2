class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head;

        // dummy 節點方便處理
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevGroupEnd = dummy;

        while (true) {
            // 找到當前區間的第 k 個節點
            ListNode kth = getKth(prevGroupEnd, k);
            if (kth == null) break; // 不足 k 個，結束
            ListNode groupStart = prevGroupEnd.next;
            ListNode nextGroupStart = kth.next;

            // 反轉這段 [groupStart, kth]
            reverse(groupStart, kth);

            // 接回到主鏈
            prevGroupEnd.next = kth;
            groupStart.next = nextGroupStart;

            // 移動到下一組
            prevGroupEnd = groupStart;
        }

        return dummy.next;
    }

    // 幫助函式：找到從 node 開始的第 k 個節點
    private ListNode getKth(ListNode node, int k) {
        while (node != null && k > 0) {
            node = node.next;
            k--;
        }
        return node;
    }

    // 幫助函式：反轉區間 [start, end]
    private void reverse(ListNode start, ListNode end) {
        ListNode prev = null;
        ListNode curr = start;
        ListNode stop = end.next; // 停止條件

        while (curr != stop) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
    }
}
/*
解題思路：
1. 使用 dummy 節點簡化鏈結處理。
2. 逐組檢查是否有 k 個節點 (getKth)，若不足則結束。
3. 若足夠，反轉這一組 [start, end] 區間。
4. 把反轉後的區間接回主鏈，並移動 prevGroupEnd 到新的尾端。
5. 重複直到無法再形成 k 長度的組合。
6. 時間複雜度 O(n)，每個節點最多被反轉一次。
7. 空間複雜度 O(1)，只需常數額外指標。
*/
