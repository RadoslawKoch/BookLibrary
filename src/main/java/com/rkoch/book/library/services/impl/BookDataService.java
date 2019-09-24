package com.rkoch.book.library.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import com.rkoch.book.library.entities.Book;
import com.rkoch.book.library.entities.BookData;
import com.rkoch.book.library.entities.BookOrder;
import com.rkoch.book.library.exceptions.BookAlreadyExistsException;
import com.rkoch.book.library.exceptions.BookRemovalException;
import com.rkoch.book.library.exceptions.EntityNotFoundException;
import com.rkoch.book.library.entities.search.BookDataSearchCriteria;
import com.rkoch.book.library.repositories.definition.RepositoryDefinition;
import com.rkoch.book.library.services.definitions.BookDataServiceDefinition;

/**
 *
 * @author rkoch
 */
public class BookDataService 
    implements BookDataServiceDefinition{
    
    private final RepositoryDefinition<BookData, Long> bookDataRepo;
    
    private final RepositoryDefinition<Book, Long> bookRepo;
    
    private final RepositoryDefinition<BookOrder, Long> orderRepo;
    
    public BookDataService(RepositoryDefinition<BookData, Long> bookDataRepo,
            RepositoryDefinition<Book, Long> bookRepo,RepositoryDefinition<BookOrder, Long> orderRepo){
        this.bookDataRepo = bookDataRepo;
        this.orderRepo = orderRepo;
        this.bookRepo = bookRepo;
    }
    
    @Override
    public BookData add(BookData data) throws BookAlreadyExistsException{
        if(this.bookDataRepo.get(data.getId())!=null){
            throw new BookAlreadyExistsException("Książka o podanym ISBN już istnieje w bazie danych");
        }
        return this.bookDataRepo.save(data);
    }
    
    @Override
    public BookData delete(Long isbn) throws BookRemovalException{
        List<Book> books = this.bookRepo.search(x->Objects.equals(x.getData().getId(), isbn));
        List<Book>tmp = books.stream().filter(x->!x.isAvaliable()).collect(Collectors.toList());
        if(!tmp.isEmpty()){
            throw new BookRemovalException("Wszystkie egzemplarze książki o podanym ISBN nie zostały zwrócone.");
        }
        books.stream().forEach(x->{
            this.bookRepo.delete(x.getId());
        });
        List<BookOrder> orders = this.orderRepo.search(x->Objects.equals(isbn, x.getBook().getData().getId()));
        orders.stream().forEach(x->this.orderRepo.delete(x.getId()));
        return bookDataRepo.delete(isbn);
    }
    
    @Override
    public List<BookData> search(BookDataSearchCriteria criteria){
        List<Predicate<BookData>> criterias = new ArrayList();
        if(criteria.getAuthor()!=null){
            criterias.add(x->
                    x.getAuthor().toLowerCase()
                    .contains(criteria.getAuthor().toLowerCase()));
        }
        if(criteria.getTitle()!=null){
            criterias.add(x->
                    x.getTitle().toLowerCase()
                    .contains(criteria.getTitle().toLowerCase()));
        }
        if(criteria.getYear()!=null){
            criterias.add(x->
                    x.getYear().equals(criteria.getYear()));
        }
        return this.bookDataRepo.search(criterias);
    }
    
    @Override
    public List<BookData> get(){
        return this.bookDataRepo.get();
    } 
    
    @Override
    public BookData getByIsbn(Long isbn) throws EntityNotFoundException{
        BookData data = this.bookDataRepo.get(isbn);
        if(data==null){
            throw new EntityNotFoundException("Ksiażka o podanym ISBN nie została znaleziona.");
        }
        return data;
    }  
}
