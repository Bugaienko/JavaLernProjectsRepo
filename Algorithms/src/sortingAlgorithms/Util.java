package sortingAlgorithms;

import java.util.List;

public class Util {
    public static Element[] initArray(int length) {
        Element[] result = new Element[length];
        for (int i = 0; i < length; i++) {
            result[i] = new Element();
        }
        return result;
    }

    public static void printArray(Element[] arr) {
        StringBuilder sb = new StringBuilder("[ ");
        for (Element element : arr) {
            sb.append(element.toString());
            sb.append("; ");
        }
        sb.append("]");
        System.out.println(sb.toString());
    }

    public static void printArray(List<Element> arr) {
        StringBuilder sb = new StringBuilder("[ ");
        for (Element element : arr) {
            sb.append(element.toString());
            sb.append("; ");
        }
        sb.append("]");
        System.out.println(sb.toString());
    }
}
