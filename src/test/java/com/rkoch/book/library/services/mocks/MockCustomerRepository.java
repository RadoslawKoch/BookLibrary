package com.rkoch.book.library.services.mocks;

import com.rkoch.book.library.entities.Customer;
import com.rkoch.book.library.repositories.definition.RepositoryDefinition;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author rkoch
 */
public class MockCustomerRepository implements RepositoryDefinition<Customer,Long>{

    @Override
    public Customer get(Long id) {
        if(id.equals(TestData.GLOBAL_ID))
            return TestData.GET_CUSTOMER;
        return null;
    }

    @Override
    public List<Customer> get() {
        return TestData.GET_ALL_CUSTOMERS;
    }

    @Override
    public Customer save(Customer obj) {
        return obj;
    }

    @Override
    public Customer delete(Long id) {
        if(id.equals(TestData.GLOBAL_ID))
            return TestData.GET_CUSTOMER;
        return null;
    }

    @Override
    public List<Customer> search(List<Predicate<Customer>> preds) {
        for(Predicate<Customer> x : preds){
            if(!x.test(TestData.GET_CUSTOMER))
                return new ArrayList();                  
        }
        return TestData.GET_ALL_CUSTOMERS;
    }

    @Override
    public List<Customer> search(Predicate<Customer>... preds) {
       return search(List.of(preds));
    }

    @Override
    public Integer count() {
       return 1;
    }   
}
