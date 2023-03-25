package sortingAlgorithms;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {3, 2, 5, 0, 1, 8, 7, 6, 9, 4};
        mergeSort(arr);
        System.out.println("Конечный рез: " + Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr) {
        int lengthArr = arr.length;

        if (lengthArr == 1) { //условие выхода
            return;
        }

        // инициализируем левый и правый подмассивы
        int median = lengthArr / 2;
        int[] leftArr = new int[median];
        int[] rightArr = new int[lengthArr - median];


        //заполняем
        for (int i = 0; i < median; i++) {
            leftArr[i] = arr[i];
        }
        for (int j = 0; j < rightArr.length; j++) {
            rightArr[j] = arr[j + median];
        }

        mergeSort(leftArr);
        mergeSort(rightArr);

        merge(arr, leftArr, rightArr);


    }

    private static void merge(int[] arr, int[] left, int[] right) {
        int leftLength = left.length;
        int rightLength = right.length;

        int leftIndex = 0;
        int rightIndex = 0;

        int arrIndex = 0;

        while (leftIndex < leftLength && rightIndex < rightLength) {
            if (left[leftIndex] < right[rightIndex]) {
                arr[arrIndex] = left[leftIndex];
                leftIndex++;
            } else {
                arr[arrIndex] = right[rightIndex];
                rightIndex++;
            }
            arrIndex++;
        }
        while (leftIndex < leftLength) {
            arr[arrIndex++] = left[leftIndex++];
        }

        while (rightIndex < rightLength) {
            arr[arrIndex++] = right[rightIndex++];
        }
    }

}
