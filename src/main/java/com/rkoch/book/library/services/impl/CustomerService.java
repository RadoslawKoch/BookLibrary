package com.rkoch.book.library.services.impl;

import java.util.List;
import com.rkoch.book.library.entities.Customer;
import com.rkoch.book.library.exceptions.EntityAlreadyExistsException;
import com.rkoch.book.library.exceptions.EntityNotFoundException;
import com.rkoch.book.library.repositories.definition.RepositoryDefinition;
import com.rkoch.book.library.services.definitions.CustomerServiceDefinition;

/**
 *
 * @author rkoch
 */
public class CustomerService
    implements CustomerServiceDefinition{
    
    private final RepositoryDefinition<Customer,Long> repo;
    
    public CustomerService(RepositoryDefinition<Customer,Long> repo){
        this.repo = repo;
    }
    
    @Override
    public Customer get(Long id) throws EntityNotFoundException {
        Customer customer = this.repo.get(id);
        if(customer==null){
            throw new EntityNotFoundException("Użytkownik o ID nie istnieje w bazie danych.");
        }
        return customer;
    }
    
    @Override
    public Customer add(Customer customer) throws EntityAlreadyExistsException {       
        List<Customer> list = this.repo.search(x->x.getEmail().equals(customer.getEmail()));
        if(!list.isEmpty()) {
            throw new EntityAlreadyExistsException("Użytkownik o wprowadzonym ID istnieje już w bazie");
        }
        return this.repo.save(customer);
    }
    
    @Override
    public Customer delete(Long id) throws EntityNotFoundException{
        Customer old = this.repo.get(id);
        if(old==null) {
            throw new EntityNotFoundException("Użytkownik o ID nie istnieje w bazie danych.");
        }
        return this.repo.delete(id);
    }
    
    @Override
    public List<Customer> get(){
        return this.repo.get();
    }   
}
