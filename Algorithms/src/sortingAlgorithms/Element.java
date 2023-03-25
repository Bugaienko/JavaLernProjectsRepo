package sortingAlgorithms;

import java.util.Random;

public class Element implements Comparable<Element> {
    private int type;
    private int value;

    static Random random = new Random();

    public Element() {
        this.type = (random.nextInt(10) > 5) ? 1 : 2;
        this.value = random.nextInt(100);
    }

    public int getType() {
        return type;
    }

    public int getValue() {
        return value;
    }


    @Override
    public String toString() {
        return "{" +
                "t=" + type +
                ", v=" + value +
                '}';
    }

    @Override
    public int compareTo(Element element) {
        return this.value - element.value;
    }
}
