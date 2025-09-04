package finalexam;

import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class LC23_MergeKLists_Hospitals {

    public static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for (ListNode node : lists) {
            if (node != null) pq.offer(node);
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (!pq.isEmpty()) {
            ListNode minNode = pq.poll();
            tail.next = minNode;
            tail = tail.next;
            if (minNode.next != null) pq.offer(minNode.next);
        }

        return dummy.next;
    }

    public static void printList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.next != null) System.out.print(" ");
            curr = curr.next;
        }
        System.out.println();
    }

    public static ListNode buildList(List<Integer> arr) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int v : arr) {
            curr.next = new ListNode(v);
            curr = curr.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        sc.nextLine();
        ListNode[] lists = new ListNode[k];

        for (int i = 0; i < k; i++) {
            String line = sc.nextLine();
            String[] parts = line.trim().split("\\s+");
            List<Integer> nums = new ArrayList<>();
            for (String p : parts) {
                int val = Integer.parseInt(p);
                if (val == -1) break;
                nums.add(val);
            }
            lists[i] = buildList(nums);
        }

        ListNode merged = mergeKLists(lists);
        printList(merged);
        sc.close();
    }
}
