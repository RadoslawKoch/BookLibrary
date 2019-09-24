package com.rkoch.book.library.services.definitions;

import com.rkoch.book.library.entities.Customer;
import com.rkoch.book.library.exceptions.EntityAlreadyExistsException;
import com.rkoch.book.library.exceptions.EntityNotFoundException;
import java.util.List;

/**
 *
 * @author rkoch
 */
public interface CustomerServiceDefinition {
    
    Customer get(Long id) throws EntityNotFoundException;
    
    Customer add(Customer customer) throws EntityAlreadyExistsException;
    
    Customer delete(Long id) throws EntityNotFoundException;
    
    List<Customer> get();
}
