package homework20;

import java.util.Arrays;

/**
 * @author Sergii Bugaienko
 */

public class ArraysUtils {
    public int[] after4ArrayReturn(int[] array) {
        if (array.length == 0) {
            return new int[0];
        }
        int cursor = -1;
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] == 4) {
                cursor = i;
                break;
            }
        }

        if (cursor == -1) {
            throw new RuntimeException();
        }
        if (cursor == array.length - 1) {
            return new int[0];
        }
        int[] result = Arrays.copyOfRange(array, cursor + 1, array.length);

        return result;

    }

    public boolean checkArraysValues(int[] array) {
        if (array.length < 2) {
            return false;
        }
        boolean is1Present = false;
        boolean is4Present = false;

        for (int number : array) {
            if (number == 1) {
                is1Present = true;
            } else if (number == 4) {
                is4Present = true;
            } else {
                return false;
            }
        }
        return (is1Present && is4Present);
    }
}
