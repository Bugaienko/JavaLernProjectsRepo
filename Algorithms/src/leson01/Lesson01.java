package leson01;

import java.util.Random;

public class Lesson01 {
    public static void main(String[] args) {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Random().nextInt(10);
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        int count = arr.length;
        int temp;
        while (count > 2) {
            for (int i = 0; i < count - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }
            count--;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

    }
}
