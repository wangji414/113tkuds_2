class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[] merged = new int[m + n];
        int i = 0, j = 0, k = 0;

        while (i < m && j < n) {
            if (nums1[i] < nums2[j]) merged[k++] = nums1[i++];
            else merged[k++] = nums2[j++];
        }
        while (i < m) merged[k++] = nums1[i++];
        while (j < n) merged[k++] = nums2[j++];

        int mid = merged.length / 2;
        if (merged.length % 2 == 0) return (merged[mid - 1] + merged[mid]) / 2.0;
        else return merged[mid];
    }
}

public class lt_04_findMedianSortedArrays {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.findMedianSortedArrays(new int[]{1,3}, new int[]{2}));
        System.out.println(sol.findMedianSortedArrays(new int[]{1,2}, new int[]{3,4}));
        System.out.println(sol.findMedianSortedArrays(new int[]{0,0}, new int[]{0,0}));
    }
}


