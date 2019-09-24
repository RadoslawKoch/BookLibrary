package com.rkoch.book.library.services;

import com.rkoch.book.library.services.impl.CustomerService;
import com.rkoch.book.library.entities.Customer;
import com.rkoch.book.library.exceptions.EntityAlreadyExistsException;
import com.rkoch.book.library.exceptions.EntityNotFoundException;
import com.rkoch.book.library.services.definitions.CustomerServiceDefinition;
import com.rkoch.book.library.services.mocks.MockCustomerRepository;
import com.rkoch.book.library.services.mocks.TestData;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author rkoch
 */
public class CustomerServiceUnitTest {
    
    private CustomerServiceDefinition customerService;
    
    @Before
    public void setup(){
        this.customerService = new CustomerService(new MockCustomerRepository());
    }

    @Test
    public void addNewCustomerDatabaseEndsOK() 
            throws EntityAlreadyExistsException {
        Customer customer = this.customerService.add(TestData.NEW_CUSTOMER);
        assertEquals(customer,TestData.NEW_CUSTOMER);
    }

    @Test(expected= EntityAlreadyExistsException.class)
    public void addNewCustomerDatabaseThrowsEntityAlreadyExistsException() 
            throws EntityAlreadyExistsException{
        this.customerService.add(TestData.GET_CUSTOMER);
    }
    
    @Test
    public void getCustomerByItsIdReturnsCustomer() throws EntityNotFoundException{
        Customer customer = this.customerService.get(TestData.GLOBAL_ID);
        assertEquals(customer,TestData.GET_CUSTOMER);
    }
    
    @Test(expected=EntityNotFoundException.class)
    public void getCustomerByItsIdThrowsEntityNotFoundException() throws EntityNotFoundException{
        this.customerService.get(56565l);
    }
    
    @Test(expected=EntityNotFoundException.class)
    public void deleteCustomerByItsIdThrowsEntityNotFoundException() throws EntityNotFoundException{
        this.customerService.delete(56565l);
    }
    
    @Test
    public void deleteCustomerByItsIdEndsOK() throws EntityNotFoundException{
        Customer customer = this.customerService.delete(TestData.GLOBAL_ID);
        assertEquals(customer,TestData.GET_CUSTOMER);
    }
    
    @Test
    public void getAllCustomersReturnsListOfCustomers() {
        List<Customer> customers = this.customerService.get();
        assertFalse(customers.isEmpty());
    }

}
