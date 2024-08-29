package edu.ykv.arraylist;

import edu.ykv.iterator.CustomIterator;

public class App {
    public static void main(String[] args) {
        checkMethods();
        System.out.println("===============================");
        checkForEach();
        checkIterator();
    }

    private static void checkForEach() {
        CustomArrayList<Person> people = new CustomArrayList<>();
        for (int i = 0; i < 5; i++) {
            people.add(new Person("Ivan " + i));
        }
        people.forEach(System.out::println);
        System.out.println("=========== After trim to size  ==========");
        people.trimToSize();
        people.forEach(System.out::println);
    }

    private static void checkIterator() {
        CustomArrayList<Person> people = new CustomArrayList<>();
        for (int i = 0; i < 5; i++) {
            people.add(new Person("Ivan " + i));
        }

        CustomIterator<Person> customIterator = people.iterator();
        while (customIterator.hasNext()) {
            System.out.println(customIterator.next());
        }

        System.out.println("=========== After trim to size  ==========");
        people.trimToSize();
        people.iterator().forEach(System.out::println);
    }


    private static void checkMethods() {
        CustomArrayList<Integer> elements = new CustomArrayList<>();

        for (int i = 0; i < 9; i++) {
            elements.add(i + 1);
        }

        System.out.println(elements);

        elements.add(3, 12);
        System.out.println(elements);

        elements.add(7, 78);
        System.out.println(elements);

        elements.remove(3);
        System.out.println(elements);

        System.out.println(elements.get(5));

        elements.remove(Integer.valueOf(78));
        System.out.println(elements);

        elements.trimToSize();
        System.out.println(elements);
    }

}
