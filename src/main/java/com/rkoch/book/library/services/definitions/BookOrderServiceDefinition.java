package com.rkoch.book.library.services.definitions;

import com.rkoch.book.library.entities.BookOrder;
import com.rkoch.book.library.entities.Customer;
import com.rkoch.book.library.exceptions.BookNotAvailableException;

/**
 *
 * @author rkoch
 */
public interface BookOrderServiceDefinition {
    
    BookOrder orderBookByIsbn(long isbn, Customer customer) 
            throws BookNotAvailableException;   
    BookOrder orderBook(long id, Customer customer) throws BookNotAvailableException;

    BookOrder returnBook(BookOrder order);
}
