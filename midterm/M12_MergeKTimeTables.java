package midterm;

import java.util.*;

public class M12_MergeKTimeTables {
    static class TimeEntry implements Comparable<TimeEntry> {
        int time;      // 到站時間（分鐘）
        int listIdx;   // 來源列表編號
        int elemIdx;   // 在來源列表的索引

        TimeEntry(int time, int listIdx, int elemIdx) {
            this.time = time;
            this.listIdx = listIdx;
            this.elemIdx = elemIdx;
        }

        @Override
        public int compareTo(TimeEntry o) {
            return this.time - o.time;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        List<int[]> lists = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            int len = sc.nextInt();
            int[] arr = new int[len];
            for (int j = 0; j < len; j++) {
                arr[j] = sc.nextInt();
            }
            lists.add(arr);
        }

        List<Integer> merged = mergeKLists(lists);
        for (int i = 0; i < merged.size(); i++) {
            System.out.print(merged.get(i) + (i == merged.size()-1 ? "\n" : " "));
        }
        sc.close();
    }

    static List<Integer> mergeKLists(List<int[]> lists) {
        PriorityQueue<TimeEntry> pq = new PriorityQueue<>();
        List<Integer> res = new ArrayList<>();

        // 初始每個列表第一個元素入隊
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i).length > 0) {
                pq.offer(new TimeEntry(lists.get(i)[0], i, 0));
            }
        }

        while (!pq.isEmpty()) {
            TimeEntry cur = pq.poll();
            res.add(cur.time);

            int[] arr = lists.get(cur.listIdx);
            int nextIdx = cur.elemIdx + 1;
            if (nextIdx < arr.length) {
                pq.offer(new TimeEntry(arr[nextIdx], cur.listIdx, nextIdx));
            }
        }

        return res;
    }
}
