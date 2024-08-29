package edu.ykv.arraylist;

import edu.ykv.iterator.CustomIterable;
import edu.ykv.iterator.CustomIterator;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * @param <T> type elements in array
 * @author Katerina Yaroshevich
 * Custom implementation ArrayList
 */
public class CustomArrayList<T> implements CustomIterable<T> {
    /**
     * default size for initialization
     */
    public static final int DEFAULT_CAPACITY = 10;

    /**
     * array elements
     */
    private T[] elements;
    /**
     * Current count elements
     */
    private int size = 0;
    /**
     * Current index for iterator
     */
    private int currentIndex;

    /**
     * Default constructor
     * Initialization array with Default capacity
     */
    public CustomArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * Constructor with capacity
     * Initialization array with capacity
     */
    public CustomArrayList(int capacity) {
        elements = (T[]) new Object[capacity];
    }

    /**
     * Add new element in the end array
     *
     * @param value Value for add
     */
    public void add(T value) {
        checkCapacity();
        elements[size++] = value;
    }

    /**
     * Add new element in array with index (in the medium)
     * <p>
     * Implementation without native method
     * System.arraycopy(elements, index, elements, index + 1, size - index);
     *
     * @param index index for add
     * @param value value for add
     */
    public void add(int index, T value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        checkCapacity();

        T[] copyArray = Arrays.copyOfRange(elements, index, size);
        for (int i = 0; i < copyArray.length; i++) {
            elements[index + i + 1] = copyArray[i];
        }

        elements[index] = value;
        size++;
    }

    /**
     * @param index index element, which should remove
     */
    public void remove(int index) {
        if (isNotCorrectIndex(index)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        T[] copyArray = Arrays.copyOfRange(elements, index + 1, size);
        for (int i = 0; i < copyArray.length; i++) {
            elements[index + i] = copyArray[i];
        }

        elements[--size] = null;
    }

    /**
     * @param value element, which should remove
     */
    public void remove(T value) {
        int index = indexOf(value);

        if (index >= 0) {
            T[] copyArray = Arrays.copyOfRange(elements, index + 1, size);
            for (int i = 0; i < copyArray.length; i++) {
                elements[index + i] = copyArray[i];
            }

            elements[--size] = null;
        }
    }

    /**
     * @param index index for get value
     * @return value. If index is not correct, then throw exception
     */
    public T get(int index) {
        if (isNotCorrectIndex(index)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        return elements[index];
    }

    /**
     * Change length array to size
     */
    public void trimToSize() {
        if (size < elements.length) {
            elements = Arrays.copyOf(elements, size);
        }
    }


    /**
     * @param value value for search
     * @return index. If value is absent, then return -1
     */
    public int indexOf(T value) {
        if (value == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == value) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (elements[i].equals(value)) {
                    return i;
                }
            }
        }
        return -1;
    }

    private boolean isNotCorrectIndex(int index) {
        return index < 0 || index >= size;
    }

    /**
     * Increase array if not enough place
     */
    private void checkCapacity() {
        if (size == elements.length) {
            int newCapacity = (elements.length * 3) / 2 + 1;
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    @Override
    public String toString() {
        return "CustomArrayList{" +
                "elements=" + Arrays.toString(elements) +
                '}';
    }

    /**
     * Get implementation for custom iterator
     *
     * @return implementation
     */
    @Override
    public CustomIterator<T> iterator() {
        return customListIterator(0);
    }

    /**
     * Create custom implementation iterator for custom Array list
     *
     * @param index start index
     * @return Custom iterator
     */
    private CustomIterator<T> customListIterator(int index) {
        if (isNotCorrectIndex(index)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        this.currentIndex = index;

        return new CustomIterator<>() {

            /**
             * check next element
             * @return true, if next element is exists
             * false - if not exists
             */
            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            /**
             * Get next element if exists
             * @return value next element
             */
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return elements[currentIndex++];
            }
        };
    }

    /**
     * Run for all elements and execute action
     *
     * @param action Consumer functional interface
     */
    @Override
    public void forEach(Consumer<T> action) {
        for (T value : elements) {
            action.accept(value);
        }
    }
}
