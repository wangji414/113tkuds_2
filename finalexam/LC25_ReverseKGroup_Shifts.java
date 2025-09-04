package finalexam;

import java.io.*;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class LC25_ReverseKGroup_Shifts {

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0, head);
        ListNode prevGroupEnd = dummy;

        while (true) {
            ListNode kth = prevGroupEnd;
            for (int i = 0; i < k && kth != null; i++) kth = kth.next;
            if (kth == null) break;

            ListNode groupStart = prevGroupEnd.next;
            ListNode nextGroupStart = kth.next;

            // 反轉區段
            ListNode prev = nextGroupStart;
            ListNode curr = groupStart;
            while (curr != nextGroupStart) {
                ListNode temp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = temp;
            }

            prevGroupEnd.next = kth;
            prevGroupEnd = groupStart;
        }
        return dummy.next;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine().trim());

        String line = br.readLine();
        String[] parts = line.trim().split("\\s+");

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (String p : parts) {
            tail.next = new ListNode(Integer.parseInt(p));
            tail = tail.next;
        }

        ListNode newHead = reverseKGroup(dummy.next, k);

        // 輸出
        StringBuilder sb = new StringBuilder();
        while (newHead != null) {
            sb.append(newHead.val).append(newHead.next != null ? " " : "\n");
            newHead = newHead.next;
        }
        System.out.print(sb);
    }
}
