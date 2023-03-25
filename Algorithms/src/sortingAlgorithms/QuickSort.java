package sortingAlgorithms;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {3, 2, 5, 0, 1, 8, 7, 6, 9, 4};
        quickSort(arr, 0, arr.length - 1);
        System.out.println("Конечный рез: " + Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int low, int high) {
        System.out.print("Вход: ");
        printArrInd(arr, low, high);
        if (arr.length == 0) return;
        if (low >= high) return;
        int i = low - 1;
        int j = low;
        int pv = arr[high];
        while (j < high) {
            while (arr[j] >= pv && j != high) {
                j++;
            }
            i++;
            if (j > i) {
                //swap
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            } else j++;
        }
        System.out.print(" i:" + i + " j:" + j + " Выход: ");
        printArrInd(arr, low, high);
        if (low < i - 1) {
            quickSort(arr, low, i - 1);
        }
        if (j > i + 1) {
            quickSort(arr, i + 1, j);
        }

    }

    private static void printArrInd(int[] arr, int fst, int lst) {
        StringBuilder sb = new StringBuilder("[ ");
        for (int i = fst; i <= lst; i++) {
            sb.append(arr[i]);
            if (i != lst) sb.append(", ");
        }
        sb.append(" ]");
        System.out.println(sb.toString());
    }
}
