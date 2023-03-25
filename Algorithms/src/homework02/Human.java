package homework02;

import java.util.Random;

public class Human implements Comparable<Human> {
    private String name;
    private int age;
    private char gender;
    private int id;
    private static int counter;

    public Human() {
        this.name = "Вася " + new Random().nextInt(99);
        this.age = 18 + new Random().nextInt(30);
        this.gender = (counter % 2 == 0) ? 'f' : 'm';
        this.id = counter++;
    }

    public Human(String name, int age, char gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.id = counter++;
    }



    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public char getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "H " + id + " {" +
                "n='" + name + '\'' +
                ", a=" + age +
                ", g=" + gender +
                '}';
    }

    @Override
    public int compareTo(Human o) {
        return this.getName().compareTo(o.getName());
    }

    public int compareToAge(Human o) {
        return this.getAge() - (o.getAge());
    }

    // В случае совпадения пола - значения дополнительно сортируются по имени
    public int compareToGender(Human o) {
        int res = this.getGender() - o.getGender();
        return (res == 0) ? this.getName().compareTo(o.getName()) : res;
    }



}
