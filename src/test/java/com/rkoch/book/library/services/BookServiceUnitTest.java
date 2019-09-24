package com.rkoch.book.library.services;

import com.rkoch.book.library.services.impl.BookService;
import com.rkoch.book.library.entities.Book;
import com.rkoch.book.library.exceptions.BookRemovalException;
import com.rkoch.book.library.exceptions.EntityAlreadyExistsException;
import com.rkoch.book.library.exceptions.EntityNotFoundException;
import com.rkoch.book.library.services.definitions.BookServiceDefinition;
import com.rkoch.book.library.services.mocks.MockBookDataRepository;
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
public class BookServiceUnitTest {
    
    private BookServiceDefinition bookService;
    
    @Before
    public void setup(){
        this.bookService = new BookService(new MockBookDataRepository(),new MockBookRepository());
    }

    @Test
    public void addNewBookDatabaseEndsOK() 
            throws EntityAlreadyExistsException {
        Book book = this.bookService.add(TestData.NEW_BOOK);
        assertEquals(book,TestData.NEW_BOOK);
    }
    
    @Test
    public void addNewBookDatabaseEndsOKandAddDependcyToBookData() 
            throws EntityAlreadyExistsException {
        Book book = this.bookService.add(TestData.NEW_BOOK);
        assertTrue(book.getData().getBooks().contains(book));
    }
    
    @Test(expected=EntityNotFoundException.class)
    public void getBookByItsIdThrowsEntityNotFoundException() 
            throws EntityNotFoundException{
        this.bookService.get(56565l);
    }
    
    @Test
    public void getBookByItsIdReturnsBook() 
            throws EntityNotFoundException{
        Book book = this.bookService.get(TestData.GLOBAL_ID);
        assertEquals(book,TestData.GET_BOOK);
    }
    
    @Test
    public void deleteBookByItsIdEndsOK() 
            throws BookRemovalException{
        Book book = this.bookService.delete(TestData.GLOBAL_ID);
        assertEquals(book,TestData.GET_BOOK);
    }
    
    
    @Test(expected=BookRemovalException.class)
    public void deleteBookByItsIdThrowsBookRemovalExceptionLentCause() 
            throws BookRemovalException{
        this.bookService.delete(TestData.LENT_ID);
    }
    
    @Test(expected=BookRemovalException.class)
    public void deleteBookByItsIdThrowsBookRemovalExceptionNonExistingCause() 
            throws BookRemovalException{
        this.bookService.delete(565655665l);
    }
    
    @Test
    public void getAllBooksReturnsBookList() {
        List<Book> books = this.bookService.get();
        assertFalse(books.isEmpty());
    }
    
    @Test
    public void countAllBooksReturns1() {
        assertTrue(this.bookService.count()==1);
    }

}
