package com.rkoch.book.library.entities.definition;

/**
 *
 * @author rkoch
 */
public interface Entity<T> {
    
    T getId();
    void setId(T id);
}
