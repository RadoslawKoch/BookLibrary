package com.rkoch.book.library.exceptions;

/**
 *
 * @author rkoch
 */
public class BookAlreadyExistsException extends Exception{

    public BookAlreadyExistsException(String msg) {
        super(msg);
    } 
}
