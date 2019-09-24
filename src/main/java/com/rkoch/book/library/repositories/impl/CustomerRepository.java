package com.rkoch.book.library.repositories.impl;

import com.rkoch.book.library.entities.Customer;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author rkoch
 */
public class CustomerRepository extends BaseRepository<Customer>{
    
    private static final CustomerRepository  INSTANCE = new CustomerRepository ();
    
    private static final AtomicLong COUNTER = new AtomicLong(1);
    
    private CustomerRepository (){}
    
    public static CustomerRepository getInstance(){
        return INSTANCE;
    }
    
    @Override
    public Customer save(Customer obj) {
        Long id = COUNTER.getAndIncrement();
        obj.setId(id);
        this.dataSource.put(id, obj);
        return obj;
    }
}
