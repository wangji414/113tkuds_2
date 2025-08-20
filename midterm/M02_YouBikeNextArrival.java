package midterm;

import java.util.*;

public class M02_YouBikeNextArrival {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine(); // 吃掉換行

        int[] times = new int[n];
        for (int i = 0; i < n; i++) {
            String t = sc.nextLine().trim();
            times[i] = toMinutes(t);
        }

        String queryStr = sc.nextLine().trim();
        int query = toMinutes(queryStr);

        int idx = binarySearch(times, query);

        if (idx == -1) {
            System.out.println("No bike");
        } else {
            System.out.println(toHHMM(times[idx]));
        }
        sc.close();
    }
    
    // 轉分鐘數
    static int toMinutes(String s) {
        String[] parts = s.split(":");
        int h = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        return h * 60 + m;
    }

    // 轉HH:mm
    static String toHHMM(int minutes) {
        int h = minutes / 60;
        int m = minutes % 60;
        return String.format("%02d:%02d", h, m);
    }

    // 找第一個大於query的位置 無就傳-1
    static int binarySearch(int[] arr, int query) {
        int left = 0, right = arr.length - 1;
        int ans = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] > query) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
}
