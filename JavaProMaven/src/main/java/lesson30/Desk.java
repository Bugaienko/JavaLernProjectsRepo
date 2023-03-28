package lesson30;

/**
 * @author Sergii Bugaienko
 */

public class Desk {

    public static void main(String[] args) {
        System.out.println(calculateHours(28800));
    }

    /**
     * @param seconds  time in seconds
     */

    public static String calculateHours(int seconds) {
        System.out.println("Time left in seconds: " + seconds);
        String result = "Осталось меньше часа";
        int SECOND_IN_HOUR = 3600;
        int rest = seconds / SECOND_IN_HOUR;
        if (rest == 0) return result;
        String endOf1 = "ось";
        String endOf2;
        int lastDigit = rest % 10;
        if (lastDigit == 1) {
            endOf1 = "ся";
            endOf2 = "";
        } else if (lastDigit == 2 || lastDigit == 3 || lastDigit == 4) {
            endOf2 = "а";
        } else {
            endOf2 = "ов";
        }
        result = "Остал" + endOf1 + " " + rest + " час" + endOf2;
        return result;
    }
}
