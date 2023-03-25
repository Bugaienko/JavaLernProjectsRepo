package sortingAlgorithms;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Result {
    private List<Element> listType1;
    private List<Element> listType2;

    public Result(Element[] array) {
        listType1 = new ArrayList<>();
        listType2 = new ArrayList<>();

//        System.out.println("Constructor");

        if (array.length > 0) {
            addElement(0, array);
        }


    }

    private static void sortList(List<Element> list, int low, int high) {
//        System.out.println("Вход сорт");
        // sorting by QuickSort Algorithms
//        System.out.print("принял: " + low + " h:" + high + " ");
//        Util.printArray(list);
        if (list.size() == 0) return;
        if (low >= high) return;
        if (low < 0 || high > list.size() - 1) return;
        int i = low - 1;
        int j = low;
        Element pv = list.get(high);
        while (j < high) {
            while (list.get(j).compareTo(pv) > 0 && j != high) {
                j++;
            }
            i++;
            if (j > i) {
                Element temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            } else j++;
        }
        if (low < i - 1) {
            sortList(list, low, i - 1);
        }
        if (j > i + 1) {
            sortList(list, i + 1, j);
        }
//        System.out.print("отсортировал: ");
//        Util.printArray(list);

    }

    private void addElement(int idx, Element[] arr) {
        if (idx > arr.length - 1) return;
//        System.out.println("Вход add");
        // добавляем элемент в соответсвующий список
        if (arr[idx].getType() == 1) {
            listType1.add(arr[idx]);
            sortList(listType1, 0, listType1.size() - 1);
        } else if (arr[idx].getType() == 2) {
            listType2.add(arr[idx]);
            sortList(listType2, 0, listType2.size() - 1);
        }
        addElement(idx + 1, arr);

    }

    public List<Element> getListType1() {
        return listType1;
    }

    public List<Element> getListType2() {
        return listType2;
    }
}
