package sortingAlgorithms;

import java.util.Arrays;

public class TaskStringSort {
    public static void main(String[] args) {
        String input = "qswertyuiopasdfghjklzxcvbnm";

        char[] chars = input.toCharArray();
        Arrays.sort(chars);
        String result = new String(chars).toUpperCase();
        System.out.println(result);
    }
}
