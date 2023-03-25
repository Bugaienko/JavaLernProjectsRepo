package homework02;

/**
 * * Algorithms. Homework #02
 * *
 * * @author Sergey Bugaenko
 * * @version 9.12.22 -
 */

public class MainHomework02 {
    public static void main(String[] args) {
        RubberArray<Human> humans = new RubberArray<>();
        for (int i = 0; i < 10; i++) {
            humans.add(new Human());
        }
//        Comparator<Human> humanComparator= new HumanComparator();
        System.out.println(humans);
        Human temp = humans.get(2);
        humans.set(2, new Human());
        humans.remove(8);
        System.out.println(humans);

        //Выбор метода сортировки. 1- по имени, 2 - по возрасту, 3- по имени; любые другие - по имени

        RubberArray<Human> humansSorted = Util.sortList(humans, 1);
        System.out.println("1-Имя: " + humansSorted);
        humansSorted = Util.sortList(humans, 2);
        System.out.println("2-Возраст: " + humansSorted);
        humansSorted = Util.sortList(humans, 3);
        System.out.println("3-Пол: " + humansSorted);
        humansSorted = Util.sortList(humans, 4);
        System.out.println("4-по умолчанию: " + humansSorted);



    }

}
