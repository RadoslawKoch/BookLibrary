package com.rkoch.book.library.services.definitions;

import com.rkoch.book.library.entities.BookData;
import com.rkoch.book.library.entities.search.BookDataSearchCriteria;
import com.rkoch.book.library.exceptions.BookAlreadyExistsException;
import com.rkoch.book.library.exceptions.BookRemovalException;
import com.rkoch.book.library.exceptions.EntityNotFoundException;
import java.util.List;

/**
 *
 * @author rkoch
 */
public interface BookDataServiceDefinition {
    
    BookData add(BookData data) throws BookAlreadyExistsException;
    
    BookData delete(Long isbn) throws BookRemovalException;
    
    List<BookData> search(BookDataSearchCriteria criteria);
    
    public List<BookData> get();
    
    public BookData getByIsbn(Long isbn) throws EntityNotFoundException;
    
}
