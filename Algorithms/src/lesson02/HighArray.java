package lesson02;

/**
 * Поиск элемента в массиве
 * Динамическое выделение памяти
 * Сортировка
 */

public class HighArray {
    private long[] a;
    private int cursor;

    public HighArray(int max) {
        this.a = new long[max];
        this.cursor = 0;
    }

    public boolean find(long searchKey) {
        int i;
        for (i = 0; i < cursor; i++) {
            if (a[i] == searchKey) {
                break;
            }
        }
        if (i == cursor) return false;
        else {
            return true;
        }
    }
    public int findIndex(long value) {
        for (int i = 0; i < cursor; i++) {
            if (a[i] == value) return i;
        }
        return -1;
    }

    public void insert(long value) {
        if (a.length * 0.8 <= cursor) {
            System.out.println("Выделяем дополнительную память");
            long[] temp = a;
            a = new long[a.length * 2];
            copyArray(temp);
        }
        a[cursor] = value;
        cursor++;
    }

    private void copyArray(long[] temp) {
        for (int i = 0; i < temp.length; i++) {
            a[i] = temp[i];
        }
    }

    public void sort() { //пузырек
        long temp;
        for (int i = 0; i < cursor; i++) {
            for (int j = 0; j < cursor; j++) {
                if (a[i] < a[j]) {
                    temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
    }

    public boolean delete(long value) {
        int i;
        for (i = 0; i < cursor; i++) {
            if (value == a[i]) break;
        }
        if (i == cursor) return false;
        else {
            for (int j = i; j < cursor; j++) {
                a[j] = a[j + 1];
            }
            cursor--;
            return true;
        }
    }

    public void display() {
        for (int i = 0; i < cursor; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    

}
