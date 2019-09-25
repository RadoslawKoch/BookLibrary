package com.rkoch.book.library.services;

import com.rkoch.book.library.entities.BookOrder;
import com.rkoch.book.library.exceptions.BookNotAvailableException;
import com.rkoch.book.library.services.impl.BookOrderService;
import com.rkoch.book.library.services.mocks.MockBookOrderRepository;
import com.rkoch.book.library.services.mocks.MockBookRepository;
import com.rkoch.book.library.services.mocks.MockCustomerRepository;
import com.rkoch.book.library.services.mocks.TestData;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import com.rkoch.book.library.services.definitions.BookOrderServiceDefinition;


/**
 *
 * @author rkoch
 */
public class BookOrderServiceUnitTest {
    
    private BookOrderServiceDefinition service;
    
    @Before
    public void setup(){
        this.service = new BookOrderService(
                new MockBookRepository(),
                new MockBookOrderRepository(),
                new MockCustomerRepository());
    }
    
    @After
    public void cleanUp(){
        TestData.GET_BOOK.setAvaliable(true);
        TestData.LENT_BOOK.setAvaliable(false);
    }
   
    @Test(expected=BookNotAvailableException.class)
    public void orderBookByISBNWithNewCustomerThrowsBookNotAvailableExceptionCauseLent() 
            throws BookNotAvailableException{
        this.service.orderBookByIsbn(TestData.LENT_ISBN, TestData.NEW_CUSTOMER);
    }
    
    @Test(expected=BookNotAvailableException.class)
    public void orderBookByIdThrowsBookNotAvailableExceptionCauseNoExist() 
            throws BookNotAvailableException{
        this.service.orderBook(5693323l, TestData.GET_CUSTOMER);
    }
    
    @Test
    public void orderBookByIdWithNewCustomerReturnCorrectOrder() 
            throws BookNotAvailableException{
        BookOrder order = this.service.orderBook(TestData.GLOBAL_ID, TestData.NEW_CUSTOMER);
        assertTrue(!order.getBook().isAvaliable() && 
                order.getCustomer().equals(TestData.NEW_CUSTOMER));
    }
    
    @Test
    public void orderBookByISBNreturnCorrectOrder() 
            throws BookNotAvailableException{
        BookOrder order = this.service.orderBookByIsbn(TestData.EXISTING_BOOK_ISBN, TestData.GET_CUSTOMER);
        assertTrue(!order.getBook().isAvaliable() && 
                order.getCustomer().equals(TestData.GET_CUSTOMER));
    }
    
    @Test(expected=BookNotAvailableException.class)
    public void orderBookByISBNThrowsBookNotAvailableExceptionCauseNoExist() 
            throws BookNotAvailableException{
        this.service.orderBookByIsbn(474545l, TestData.GET_CUSTOMER);
    }
    
    @Test(expected=BookNotAvailableException.class)
    public void orderBookByIdWithNewCustomerThrowsBookNotAvailableExceptionCauseLent() 
            throws BookNotAvailableException{
        this.service.orderBook(TestData.LENT_ID, TestData.NEW_CUSTOMER);
    }
    
    @Test
    public void orderBookByISBNWithNewCustomerReturnsCorrectOrder() 
            throws BookNotAvailableException{
        BookOrder order = this.service.orderBookByIsbn(TestData.EXISTING_BOOK_ISBN, TestData.NEW_CUSTOMER);
        assertTrue(!order.getBook().isAvaliable() && 
                order.getCustomer().equals(TestData.NEW_CUSTOMER));
    }
    
    @Test(expected=BookNotAvailableException.class)
    public void orderBookByISBNThrowsBookNotAvailableExceptionCauseLent() 
            throws BookNotAvailableException{
        this.service.orderBookByIsbn(TestData.LENT_ISBN, TestData.GET_CUSTOMER);
    }
    
    @Test(expected=BookNotAvailableException.class)
    public void orderBookBtIdThrowsBookNotAvailableExceptionCauseLent() 
            throws BookNotAvailableException{
        this.service.orderBook(TestData.LENT_ID, TestData.GET_CUSTOMER);
    }
    
    @Test
    public void returnBookMakesBookAvailable(){
        this.service.returnBook(TestData.GET_BOOK_ORDER);
        assertTrue(TestData.LENT_BOOK.isAvaliable());
    }
    
    @Test(expected=BookNotAvailableException.class)
    public void orderBookByISBNWithNewCustomerThrowsBookNotAvailableExceptionCauseNoExist() 
            throws BookNotAvailableException{
        this.service.orderBookByIsbn(TestData.INEXISTING_BOOK_ISBN, TestData.NEW_CUSTOMER);
    }
    
    @Test
    public void orderBookByIdReturnsCorrectOrder() 
            throws BookNotAvailableException{
        BookOrder order = this.service.orderBook(TestData.GLOBAL_ID, TestData.GET_CUSTOMER);
        assertTrue(!order.getBook().isAvaliable() &&
                order.getCustomer().equals(TestData.GET_CUSTOMER));
    }
    
    @Test(expected=BookNotAvailableException.class)
    public void orderBookByIdWithNewCustomerThrowsBookNotAvailableExceptionCauseNoExist() 
            throws BookNotAvailableException{
        this.service.orderBook(777777l, TestData.NEW_CUSTOMER);
    }
    
}
