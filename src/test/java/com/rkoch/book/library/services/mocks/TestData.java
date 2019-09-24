package com.rkoch.book.library.services.mocks;

import com.rkoch.book.library.entities.Book;
import com.rkoch.book.library.entities.BookData;
import com.rkoch.book.library.entities.BookOrder;
import com.rkoch.book.library.entities.CoverType;
import com.rkoch.book.library.entities.Customer;
import java.util.List;

/**
 *
 * @author rkoch
 */
public class TestData {
    
    public static final Long EXISTING_BOOK_ISBN = 566666l;
    
    public static final Long INEXISTING_BOOK_ISBN= 656598l;
    
    public static final Long LENT_ISBN = 9999l;
    
    public static final String AUTHOR_NAME = "Jeremy Clarkson";
    
    public static final String BOOK_TITLE = "Świat według Clarksona";
    
    public static final String CUSTOMER_EMAIL = "metslesgaz@laposte.net";
    
    public static final Book LENT_BOOK = MockHelper.getBook(MockHelper
                .getBookData(AUTHOR_NAME,BOOK_TITLE,LENT_ISBN),CoverType.HARD);
                
    public static final Book GET_BOOK = MockHelper.getBook(MockHelper
                .getBookData(AUTHOR_NAME,BOOK_TITLE,EXISTING_BOOK_ISBN),CoverType.HARD);
    
    public static final Book NEW_BOOK = MockHelper.getBook(MockHelper
                .getBookData(AUTHOR_NAME,BOOK_TITLE,EXISTING_BOOK_ISBN),CoverType.SOFT);
    
    public static final List<Book> GET_ALL_BOOKS = List.of(GET_BOOK, LENT_BOOK);
    
    public static final Customer GET_CUSTOMER = new Customer(CUSTOMER_EMAIL);
    
    public static final Customer NEW_CUSTOMER = new Customer("test@test.pl");
    
    public static final BookOrder GET_BOOK_ORDER = new BookOrder(GET_CUSTOMER,LENT_BOOK);
    
    public static final List<BookOrder> GET_ALL_ORDERS = List.of(GET_BOOK_ORDER);
    
    public static final List<Customer> GET_ALL_CUSTOMERS = List.of(GET_CUSTOMER);
    
    public static final BookData GET_BOOK_DATA = MockHelper
                .getBookData(BOOK_TITLE,AUTHOR_NAME,EXISTING_BOOK_ISBN);
    
    public static final BookData NEW_BOOK_DATA = MockHelper
                .getBookData(BOOK_TITLE,AUTHOR_NAME,INEXISTING_BOOK_ISBN);
    
    public static final List<BookData> GET_ALL_BOOK_DATA = List.of(GET_BOOK_DATA);
    
    public static final Long GLOBAL_ID = 1l;
    
    public static final Long LENT_ID = 15l;
    
    static {
        GET_BOOK_ORDER.setId(GLOBAL_ID);
        GET_BOOK.setId(GLOBAL_ID);
        GET_CUSTOMER.setId(GLOBAL_ID);
        LENT_BOOK.setId(LENT_ID);
        LENT_BOOK.setAvaliable(false);
        
    }  
}
