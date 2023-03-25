package lesson02;

import java.util.Random;

public class MainLesson02 {
    static int[] arr = new int[10];
    static Random random = new Random();

    public static void main(String[] args) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(10);
            System.out.print(arr[i] + " ");
        }
        System.out.println();

    LowArray lowArray = new LowArray(100);

    lowArray.setElem(0, 77);

    HighArray highArray = new HighArray(10);
    highArray.insert(10);
    highArray.insert(21);
    highArray.insert(34);
    highArray.insert(45);
    highArray.insert(56);
    highArray.insert(85);

    highArray.display();
        System.out.println(highArray.find(85));
        highArray.delete(56);
        highArray.display();
        highArray.sort();
        highArray.display();
        highArray.insert(55);
        highArray.insert(37);
        highArray.insert(33);
        highArray.insert(84);
        highArray.insert(100);

        highArray.display();
        System.out.println(highArray.findIndex(10));
        System.out.println(highArray.findIndex(134));

    }

}
