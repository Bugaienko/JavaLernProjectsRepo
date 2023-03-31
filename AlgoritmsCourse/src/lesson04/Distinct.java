package lesson04;

/**
 * @author Sergii Bugaienko
 */

public class Distinct {
    public static void main(String[] args) {
        int[] array = {7, 7, 8, 8, 1, 2, 2, 3, 3};
        System.out.println(getSingle(array));
    }

    public static int getSingle(int[] arr){
        int xor = 0;
        for (int x : arr){
            xor = xor ^ x;
        }
        return xor;
    }
}

// 0 0 0 0 0 1 1 1  - 7
// 0 0 0 0 0 0 1 1  - 3
// 0 0 0 0 0 1 0 0  - 8
// 0 0 0 0 0 0 1 0  - 2
// 0 0 0 0 0 0 0 1  - 1
