package com.rkoch.book.library.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import com.rkoch.book.library.entities.Book;
import com.rkoch.book.library.entities.BookOrder;
import com.rkoch.book.library.entities.Customer;
import com.rkoch.book.library.exceptions.BookNotAvailableException;
import com.rkoch.book.library.repositories.definition.RepositoryDefinition;
import com.rkoch.book.library.services.definitions.OrderBookServiceDefinition;

/**
 *
 * @author rkoch
 */
public class OrderBookService 
    implements OrderBookServiceDefinition {
    
    //@Autowired
    private final RepositoryDefinition<Book,Long> repo;
    
    private final RepositoryDefinition<BookOrder,Long> orderRepo;
    
    private final RepositoryDefinition<Customer,Long> customerRepo;
    
    public OrderBookService(RepositoryDefinition<Book,Long> repo, 
                            RepositoryDefinition<BookOrder,Long> orderRepo,
                            RepositoryDefinition<Customer,Long> customerRepo){
        this.repo = repo;
        this.orderRepo = orderRepo;
        this.customerRepo = customerRepo;
    }
    
    @Override
    public BookOrder orderBookByIsbn(long isbn, Customer customer) 
            throws BookNotAvailableException{
        this.addIfAbsent(customer);
        List<Book> books = repo.search(x->x.getData().getId()==isbn && x.isAvaliable());
        if(books.isEmpty()){
            throw new BookNotAvailableException("Książka o wybranym numerze ISBN nie jest dostępna.");
        }
        Book book = books.get(0);
        book.setAvaliable(false);
        BookOrder order = new BookOrder(customer,book);
        book.getOrders().add(order);
        this.orderRepo.save(order);
        customer.getHistory().add(order);
        return order;
    }
    
    @Override
    public BookOrder orderBook(long id, Customer customer) throws BookNotAvailableException{
        this.addIfAbsent(customer);
        Book book = repo.get(id);
        if(book==null || !book.isAvaliable()){
            throw new BookNotAvailableException("Książka o wybranym numerze ISBN nie jest dostępna.");
        }
        book.setAvaliable(false);
        BookOrder order = new BookOrder(customer,book);
        book.getOrders().add(order);
        this.orderRepo.save(order);
        customer.getHistory().add(order);
        return order;
    }
    
    @Override
    public BookOrder returnBook(BookOrder order){
        order.setEndTime(LocalDateTime.now());
        Book book = order.getBook();
        book.setAvaliable(true);
        this.repo.save(book);
        return order;
    }
    
    private void addIfAbsent(Customer customer){
        List<Customer> list = this.customerRepo
                .search(x->x.getEmail().equals(customer.getEmail()) ||
                        x.getId().equals(customer.getId()));
        if(list.isEmpty()){
            this.customerRepo.save(customer);
        }
    }
}
