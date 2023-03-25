package homework02;

import java.util.Comparator;

public  class HumanComparator implements Comparator<Human> {
    @Override
    public int compare(Human o1, Human o2) {
        return o2.getName().compareTo(o1.getName());
    }
}
