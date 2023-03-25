package homework02;

public class Util {
    public static RubberArray<Human> sortList(RubberArray<Human> list, int method){
        int counter = list.size();
        Human temp;
        while (counter > 1) {
            for (int i = 0; i < counter - 1; i++) {
                if (choiceComparator(list.get(i), list.get(i+1), method) ) {
//                if (list.get(i).compareTo(list.get(i+1)) > 0 ) {
                    temp = list.get(i);
                    list.set(i, list.get(i+1));
                    list.set(i + 1, temp);
                }
            }
            counter--;
        }
        return list;

    }

    //Выбираем метод сортировки: по возрасту, имени, полу
    private static boolean choiceComparator(Human h1, Human h2, int choice){
        switch (choice){
            case 1:
                return compName(h1, h2);
            case 2:
                return compAge(h1, h2);
            case 3:
                return compGender(h1, h2);
            default:
                return compName(h1, h2);
        }
    }

    private static boolean compName(Human o1, Human o2){
        return o1.compareTo(o2) > 0;
    }

    private static boolean compAge(Human o1, Human o2){
        return o1.compareToAge(o2) > 0;
    }

    private static boolean compGender(Human o1, Human o2){
        return o1.compareToGender(o2) > 0;
    }


}
