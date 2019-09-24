package com.rkoch.book.library.services.definitions;

import com.rkoch.book.library.entities.Book;
import com.rkoch.book.library.exceptions.BookRemovalException;
import com.rkoch.book.library.exceptions.EntityNotFoundException;
import java.util.List;

/**
 *
 * @author rkoch
 */
public interface BookServiceDefinition {
    
    public Book get(Long id) throws EntityNotFoundException;
    
    public List<Book> get();
    
    public Integer count();
    
    public Book add(Book book);
    
    public Book delete(Long id) throws BookRemovalException;
    
}
