package finalexam;

import java.io.*;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class LC24_SwapPairs_Shifts {
    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            ListNode a = prev.next;
            ListNode b = a.next;

            // 交換 a 與 b
            a.next = b.next;
            b.next = a;
            prev.next = b;

            // 移動 prev 到下一對
            prev = a;
        }
        return dummy.next;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null || line.trim().isEmpty()) {
            System.out.println();
            return;
        }

        String[] parts = line.trim().split("\\s+");
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (String p : parts) {
            tail.next = new ListNode(Integer.parseInt(p));
            tail = tail.next;
        }

        ListNode newHead = swapPairs(dummy.next);

        StringBuilder sb = new StringBuilder();
        while (newHead != null) {
            sb.append(newHead.val);
            if (newHead.next != null) sb.append(" ");
            newHead = newHead.next;
        }
        System.out.println(sb);
    }
}
