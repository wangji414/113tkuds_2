package midterm;

import java.util.*;

public class M01_BuildHeap {
    static String type;
    static int n;
    static int[] heap;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        type = sc.next();
        n = sc.nextInt();
        heap = new int[n];
        for(int i =0; i<n; i++){
            heap[i] = sc.nextInt();
        }
        buildHeap();

        for(int i=0; i<n; i++){
        System.out.print(heap[i]+(i==n-1 ?"\n": " "));
        }

        sc.close();
    }

    static void buildHeap(){
        for(int i=n/2-1; i>=0; i--){
            heapifyDown(i);
        }
    }

    static void heapifyDown(int i){
        while(true){
            int left = 2*i+1;
            int right = 2*i+2;
            int target = i;

            if (left < n && compare(heap[left], heap[target])) {
                target = left;
            }
            if (right < n && compare(heap[right], heap[target])) {
                target = right;
            }
            if (target == i)break;

            swap(i, target);
            i = target;
        }
    }
    
    static boolean compare(int child, int parent){
        if (type.equals("max")) {
            return child > parent;
        } else {
            return child < parent;
        }
    }
    
    static void swap(int i, int j){
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}

/*
 * Time Complexity: O(n)
 * 說明：
 * Heapify Down：最多需要 log n 次比較（樹的高度）
 * 每次比較操作為 O(1)，因此整體為 O(log n)
 */
