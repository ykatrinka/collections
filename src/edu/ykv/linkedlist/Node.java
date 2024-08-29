package edu.ykv.linkedlist;

public class Node<T> {
    T value;
    Node<T> prev;
    Node<T> next;

    public Node(T element, Node<T> prev, Node<T> next) {
        this.value = element;
        this.prev = prev;
        this.next = next;
    }
}