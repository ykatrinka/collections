package edu.ykv.linkedlist;

import edu.ykv.iterator.CustomIterable;
import edu.ykv.iterator.CustomIterator;

import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class CustomLinkedList<T> implements CustomIterable<T> {
    private int size;
    private Node<T> first;
    private Node<T> last;

    public CustomLinkedList() {
        this.size = 0;
        this.first = null;
        this.last = null;
    }

    public T getFirst() {
        if (first == null) {
            return null;
        }
        return first.value;
    }

    public T getLast() {
        if (last == null) {
            return null;
        }
        return last.value;
    }

    public void addFirst(T value) {
        Node<T> newNode = new Node<>(value, null, first);

        if (first == null) {
            last = newNode;
        } else {
            first.prev = newNode;
        }
        first = newNode;

        size++;
    }

    public void addLast(T value) {
        Node<T> newNode = new Node<>(value, last, null);

        if (last == null) {
            first = newNode;
        } else {
            last.next = newNode;
        }
        last = newNode;

        size++;
    }

    public void add(int index, T value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (index == size) {
            addLast(value);
        } else {
            Node<T> node = findElement(index);

            Node<T> newNode = new Node<>(value, node.prev, node);
            if (node.prev == null) {
                first = newNode;
            } else {
                node.prev.next = newNode;
            }
            node.prev = newNode;
        }

        size++;
    }

    public void removeFirst() {
        if (first == null)
            throw new NoSuchElementException();
        Node<T> removeNode = first;

        first = first.next;
        if (removeNode.next == null) {
            last = null;
        } else {
            removeNode.next.prev = null;
        }
        removeNode.prev = null;
        removeNode.next = null;

        size--;
    }

    public void removeLast() {
        if (last == null)
            throw new NoSuchElementException();
        Node<T> removeNode = last;

        last = last.prev;
        if (removeNode.prev == null) {
            first = null;
        } else {
            removeNode.prev.next = null;
        }
        removeNode.prev = null;
        removeNode.next = null;

        size--;
    }

    public void remove(T value) {
        Node<T> removeNode = findElement(value);
        if (removeNode == null) {
            return;
        }
        Node<T> nextNode = removeNode.next;
        Node<T> prevNode = removeNode.prev;

        if (prevNode == null) {
            first = nextNode;
        } else {
            prevNode.next = nextNode;
        }

        if (nextNode == null) {
            last = null;
        } else {
            nextNode.prev = prevNode;
        }

        removeNode.prev = null;
        removeNode.next = null;

        size--;
    }

    private Node<T> findElement(T value) {
        Node<T> node = first;
        while (node != null) {
            if (value == null) {
                if (node.value == null) {
                    return node;
                }
            } else {
                if (value.equals(node.value)) {
                    return node;
                }
            }
            node = node.next;
        }
        return null;
    }

    private Node<T> findElement(int index) {
        if (index < (size / 2)) {
            Node<T> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        } else {
            Node<T> node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
            return node;
        }
    }

    @Override
    public CustomIterator<T> iterator() {
        return customListIterator(first);
    }

    private CustomIterator<T> customListIterator(Node<T> node) {

        return new CustomIterator<>() {
            private Node<T> current = node;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                T value = current.value;
                current = current.next;
                return value;
            }
        };
    }


    @Override
    public void forEach(Consumer<T> action) {
        Node<T> node = first;

        while (node != null) {
            action.accept(node.value);
            node = node.next;
        }
    }
}
