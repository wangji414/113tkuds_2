package finalexam;

import java.util.HashMap;
import java.util.Scanner;

public class LC01_TwoSum_THSRHoliday {

    public static int[] twoSum(int[] seats, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < seats.length; i++) {
            int x = seats[i];
            if (map.containsKey(x)) {
                return new int[] { map.get(x), i };
            }
            map.put(target - x, i);
        }
        return new int[] { -1, -1 };
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int target = sc.nextInt();
        int[] seats = new int[n];

        for (int i = 0; i < n; i++) {
            seats[i] = sc.nextInt();
        }

        int[] result = twoSum(seats, target);
        System.out.println(result[0] + " " + result[1]);

        sc.close();
    }
}
