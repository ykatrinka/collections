package edu.ykv.iterator;

import java.util.function.Consumer;

/**
 * @param <T> type value for Iterable
 * @author Katerina Yaroshevich
 */
public interface CustomIterable<T> {
    /**
     * Get implementation iterator for collection
     * @return Custom iterator
     */
    CustomIterator<T> iterator();

    /**
     * Run for all elements and execute action
     * @param action Consumer functional interface
     */
    void forEach(Consumer<T> action);
}
