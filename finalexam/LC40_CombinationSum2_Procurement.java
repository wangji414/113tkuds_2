package finalexam;

import java.util.*;

public class LC40_CombinationSum2_Procurement {

    public static void combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<Integer> path = new ArrayList<>();
        backtrack(candidates, target, 0, path);
    }

    private static void backtrack(int[] candidates, int target, int start, List<Integer> path) {
        if (target == 0) {
            for (int i = 0; i < path.size(); i++) {
                if (i > 0) System.out.print(" ");
                System.out.print(path.get(i));
            }
            System.out.println();
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > target) break;
            if (i > start && candidates[i] == candidates[i - 1]) continue; // 去重同層
            path.add(candidates[i]);
            backtrack(candidates, target - candidates[i], i + 1, path); // 每個值只能用一次
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int target = sc.nextInt();
        int[] candidates = new int[n];
        for (int i = 0; i < n; i++) candidates[i] = sc.nextInt();
        combinationSum2(candidates, target);
        sc.close();
    }
}
