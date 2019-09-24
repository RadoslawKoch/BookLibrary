package com.rkoch.book.library.services.impl;

import com.rkoch.book.library.entities.Book;
import com.rkoch.book.library.entities.BookData;
import com.rkoch.book.library.exceptions.BookRemovalException;
import com.rkoch.book.library.exceptions.EntityNotFoundException;
import com.rkoch.book.library.repositories.definition.RepositoryDefinition;
import com.rkoch.book.library.services.definitions.BookServiceDefinition;
import java.util.List;


/**
 *
 * @author rkoch
 */
public class BookService
    implements BookServiceDefinition{
    
    //@Autowired
    private final RepositoryDefinition<BookData,Long> bookRepo;
    
    private final RepositoryDefinition<Book,Long> books;
    
    public BookService(RepositoryDefinition<BookData,Long> bookRepo,
                        RepositoryDefinition<Book,Long> books) {
        this.bookRepo = bookRepo;
        this.books = books;
    }
    
    @Override
    public Book get(Long id) throws EntityNotFoundException{
        Book book = this.books.get(id);
         if(book==null){
            throw new EntityNotFoundException("Książka o podanym ID nie istnieje w bazie danych.");
        }
         return book;
    }
    
    @Override
    public List<Book> get(){
        return this.books.get();
    }
    
    @Override
    public Integer count(){
        return this.bookRepo.count();
    }
    
    @Override
    public Book add(Book book){
        BookData data = this.bookRepo.get(book.getData().getId());
        if(data!=null){
            data = this.bookRepo.save(book.getData());
        }       
        book = this.books.save(book);    
        data.getBooks().add(book);
        return book;
    }
    
    @Override
    public Book delete(Long id) throws BookRemovalException{
        Book book = this.books.get(id);       
        if(book==null){
            throw new BookRemovalException("Nie można usunąć wybanej książki. Podana pozycja nie istnieje w bazie danych.");
        }
        if(!book.isAvaliable()){
            throw new BookRemovalException("Nie można usunąć wybanej książki. Podana pozycja jest wypożyczona.");
        }
        book.getData().getBooks().remove(book);
        return this.books.delete(id);
    }
    
    
    
}
