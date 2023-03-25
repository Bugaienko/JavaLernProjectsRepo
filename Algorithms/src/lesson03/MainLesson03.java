package lesson03;

public class MainLesson03 {
    public static void main(String[] args) {
        stepen(20);
        stepen100(20);
        stepenyx(20);
    }

    static void stepen(int x) {
        for (int i = 0; i <= x; i++) {
            float step = (float) Math.pow(2, i);
            int prevStep = (int) Math.pow(2, i - 1);
            if (i > 1) {
                System.out.print(i+ ": " + step / prevStep + "; ");
            }
        }
        System.out.println();
    }

    static void stepen100(int x) {
        for (int i = 0; i <= x; i++) {
            float step = (float) Math.pow(i, 4);
            int prevStep = (int) Math.pow(i - 1, 4);
            if (i > 1) {
                System.out.print(i+ ": " + (float) (Math.round(10 * step / prevStep)) / 10 + "; ");
            }
        }
        System.out.println();
    }
    static void stepenyx(int x) {
        for (int i = 0; i <= x; i++) {
            float step = (float) i;
            int prevStep = i-1;
            if (i > 1) {
                System.out.print(i+ ": " + (float) (Math.round(10 * step / prevStep)) / 10 + "; ");
            }
        }
        System.out.println();
    }
}
