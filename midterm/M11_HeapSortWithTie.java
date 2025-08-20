package midterm;

import java.util.*;

public class M11_HeapSortWithTie {
    static class Student {
        int score;
        int index; // 輸入順序
        Student(int score, int index) {
            this.score = score;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Student[] arr = new Student[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Student(sc.nextInt(), i);
        }

        heapSort(arr);

        for (int i = 0; i < n; i++) {
            System.out.print(arr[i].score + (i == n-1 ? "\n" : " "));
        }
        sc.close();
    }

    static void heapSort(Student[] arr) {
        int n = arr.length;

        // Build Max-Heap
        for (int i = n/2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Heap Sort
        for (int i = n-1; i >= 0; i--) {
            swap(arr, 0, i);       // 將最大值移到末尾
            heapify(arr, i, 0);    // 對剩餘元素 heapify
        }
    }

    // Max-Heapify
    static void heapify(Student[] arr, int n, int i) {
        int largest = i;
        int left = 2*i + 1;
        int right = 2*i + 2;

        if (left < n && compare(arr[left], arr[largest]) > 0) largest = left;
        if (right < n && compare(arr[right], arr[largest]) > 0) largest = right;

        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }

    // 比較兩個 Student
    static int compare(Student a, Student b) {
        if (a.score != b.score) return a.score - b.score; // 分數較大優先
        return b.index - a.index; // 分數相同 index 較小者優先
    }

    static void swap(Student[] arr, int i, int j) {
        Student tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}

/*
* Time Complexity: O(n log n)
* 說明：
* Build-Heap 自底向上 O(n)
* 每次交換最大元素並 heapify O(log n)，共 n 次 → O(n log n)
* 平手比較：score 相同時 index 較小者優先
*/
