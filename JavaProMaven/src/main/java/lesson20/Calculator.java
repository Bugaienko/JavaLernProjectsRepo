package lesson20;

/**
 * @author Sergii Bugaienko
 */

public class Calculator {
    public int add (int a, int b) {
        return a + b;
    }

    public int sub (int a, int b) {
        return a - b;
    }

    public int mul (int a, int b) {
        return a * b;
    }

    public int div (int a, int b) {
        return a / b;
    }

    public static void main(String[] args) {
        int a = -2;
        int b = -2;

        System.out.println(a-b);
    }

}
