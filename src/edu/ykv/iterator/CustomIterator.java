package edu.ykv.iterator;

import java.util.function.Consumer;

/**
 * @param <T> type value for Iterator
 * @author Katerina Yaroshevich
 */
public interface CustomIterator<T> {
    /**
     * check next element
     *
     * @return true, if next element is exists
     * false - if not exists
     */
    boolean hasNext();

    /**
     * Get next element if exists
     *
     * @return value next element
     */
    T next();

    /**
     * Run for all elements and execute action
     *
     * @param action Consumer functional interface
     */
    default void forEach(Consumer<T> action) {
        while (hasNext()) {
            action.accept(next());
        }
    }
}
