package edu.ykv.linkedlist;

import edu.ykv.iterator.CustomIterator;

public class App {
    public static void main(String[] args) {
        CustomLinkedList<String> customLinkedList = new CustomLinkedList<>();

        customLinkedList.addLast("2");
        customLinkedList.addLast("3");
        customLinkedList.addLast("5");

        customLinkedList.addFirst("1");

        customLinkedList.add(3, "4");


        customLinkedList.forEach(System.out::println);

        System.out.println("=====================");
        CustomIterator<String> customIterator = customLinkedList.iterator();
        while (customIterator.hasNext()) {
            System.out.print(customIterator.next() + ", ");
        }
        System.out.println();

        System.out.println("=====================");
        customLinkedList.iterator().forEach(System.out::println);

        customLinkedList.removeFirst();
        customLinkedList.removeLast();
        customLinkedList.remove("3");

        System.out.println("=====================");
        customLinkedList.forEach(System.out::println);
    }
}
