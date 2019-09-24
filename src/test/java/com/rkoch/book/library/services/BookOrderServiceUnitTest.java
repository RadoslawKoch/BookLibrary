package com.rkoch.book.library.services;

import com.rkoch.book.library.services.definitions.OrderBookServiceDefinition;
import com.rkoch.book.library.services.impl.OrderBookService;
import com.rkoch.book.library.services.mocks.MockBookOrderRepository;
import com.rkoch.book.library.services.mocks.MockBookRepository;
import com.rkoch.book.library.services.mocks.MockCustomerRepository;
import com.rkoch.book.library.services.mocks.TestData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 *
 * @author rkoch
 */
public class BookOrderServiceUnitTest {
    
    private OrderBookServiceDefinition service;
    
    @Before
    public void setup(){
        this.service = new OrderBookService(
                new MockBookRepository(),
                new MockBookOrderRepository(),
                new MockCustomerRepository());
    }
    
    @After
    public void cleanUp(){
        TestData.GET_BOOK.setAvaliable(true);
        TestData.LENT_BOOK.setAvaliable(false);
    }
   
    @Test
    public void orderBookByISBNWithNewCustomerThrowsBookNotAvailableExceptionCauseLent(){
        
    }
    
    @Test
    public void orderBookByIdThrowsBookNotAvailableExceptionCauseNoExist(){
        
    }
    
    @Test
    public void orderBookByIdWithNewCustomerReturnCorrectOrder(){
        
    }
    
    @Test
    public void orderBookByISBNreturnCorrectOrder(){
        
    }
    
    @Test
    public void orderBookByISBNThrowsBookNotAvailableExceptionCauseNoExist(){
        
    }
    
    @Test
    public void orderBookByIdWithNewCustomerThrowsBookNotAvailableExceptionCauseLent(){
        
    }
    
    @Test
    public void orderBookByISBNWithNewCustomerReturnsCorrectOrder(){
        
    }
    
    @Test
    public void orderBookByISBNThrowsBookNotAvailableExceptionCauseLent(){
        
    }
    
    @Test
    public void orderBookBtIdThrowsBookNotAvailableExceptionCauseLent(){
        
    }
    
    @Test
    public void returnBookMakesBookAvailable(){
        
    }
    
    @Test
    public void orderBookByISBNWithNewCustomerThrowsBookNotAvailableExceptionCauseNoExist(){
        
    }
    
    @Test
    public void orderBookByIdReturnsCorrectOrder(){
        
    }
    
    @Test
    public void orderBookByIdWithNewCustomerThrowsBookNotAvailableExceptionCauseNoExist(){
        
    }
    
}
