package edu.sjsu.cmpe.library.repository;

import java.util.ArrayList;

import com.yammer.dropwizard.jersey.params.LongParam;

import edu.sjsu.cmpe.library.domain.Book.Authors;
import edu.sjsu.cmpe.library.domain.Book;
//import edu.sjsu.cmpe.library.domain.Book.Reviews;

//import java.util.concurrent.*;
//import java.util.*;
/**
 * Book repository interface.
 * 
 * What is repository pattern?
 * 
 * @see http://martinfowler.com/eaaCatalog/repository.html
 */
public interface BookRepositoryInterface {
    /**
     * Save a new book in the repository
     * 
     * @param newBook
     *            a book instance to be create in the repository
     * @return a newly created book instance with auto-generated ISBN
     */
    Book saveBook(Book newBook);
   // Authors saveAuthor(Authors newAuthors);
    /**
     * Retrieve an existing book by ISBN
     * 
     * @param isbn
     *            a valid ISBN
     * @return a book instance
     */
    Book getBookByISBN(long isbn);
    Book deleteBookByIsbn(long isbn);
    Book updateBookByIsbn(long isbn);
   Book.Reviews createReviews(Book.Reviews review);
    Book.Reviews viewReviewsById(long Id);
    Book viewAllReviews(long Id);
   Book viewBookAuthorsById(long Id);
Book viewAllAuthors(long isbn);

    // TODO: add other operations here!
}
