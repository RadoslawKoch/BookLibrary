package com.rkoch.book.library.entities.definition;

/**
 *
 * @author rkoch
 * @param <T> Entity Id
 */
public interface Entity<T> {
    
    T getId();
    void setId(T id);
}
