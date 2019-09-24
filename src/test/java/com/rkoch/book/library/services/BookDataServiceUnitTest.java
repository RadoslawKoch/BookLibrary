package com.rkoch.book.library.services;

import com.rkoch.book.library.entities.Book;
import com.rkoch.book.library.services.impl.BookDataService;
import com.rkoch.book.library.entities.BookData;
import com.rkoch.book.library.entities.search.BookDataSearchCriteria;
import com.rkoch.book.library.exceptions.BookAlreadyExistsException;
import com.rkoch.book.library.exceptions.EntityNotFoundException;
import com.rkoch.book.library.services.definitions.BookDataServiceDefinition;
import com.rkoch.book.library.services.mocks.MockBookDataRepository;
import com.rkoch.book.library.services.mocks.MockBookOrderRepository;
import com.rkoch.book.library.services.mocks.MockBookRepository;

import com.rkoch.book.library.services.mocks.TestData;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author rkoch
 */
public class BookDataServiceUnitTest {
    
    private BookDataServiceDefinition bookService;
    

    @Before
    public void setup(){
                this.bookService = new BookDataService(
                new MockBookDataRepository(),
                new MockBookRepository(),
                new MockBookOrderRepository());
    }

    @Test
    public void addNewBookDataToLibraryEndsOK() 
            throws BookAlreadyExistsException, EntityNotFoundException {
        BookData data = this.bookService.add(TestData.NEW_BOOK_DATA);
        assertEquals(data,TestData.NEW_BOOK_DATA);
    }

    @Test(expected=BookAlreadyExistsException.class)
    public void addNewBookDataToLibraryThrowsBookAlreadyExistsException() 
            throws BookAlreadyExistsException{
        this.bookService.add(TestData.GET_BOOK_DATA);
    }
    
    @Test
    public void searchBookByAuthorAndTitleReturnsBook() throws BookAlreadyExistsException {     
        BookDataSearchCriteria criteria = new BookDataSearchCriteria();
        criteria.setAuthor(TestData.AUTHOR_NAME);
        criteria.setTitle(TestData.BOOK_TITLE);
        System.out.println(TestData.AUTHOR_NAME);
        System.out.println(TestData.BOOK_TITLE);
        List<BookData> books = this.bookService.search(criteria);
        assertFalse(books.isEmpty());
    }
    
    @Test
    public void searchBookByAuthorAndTitleReturnsEmptyList(){
        BookDataSearchCriteria criteria = new BookDataSearchCriteria();
        criteria.setAuthor(TestData.AUTHOR_NAME);
        criteria.setTitle("");
        List<BookData> books = this.bookService.search(criteria);
        assertTrue(books.isEmpty());
    }
    
    @Test
    public void getAllBooksReturnsListWithOneEntry(){
        List<BookData> books = this.bookService.get();
        assertTrue(books.size()==1);
    }

}
