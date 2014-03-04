package edu.sjsu.cmpe.library.repository;

import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Authors;
import edu.sjsu.cmpe.library.domain.Reviews;

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
    Reviews createReviews(Reviews newreview);
    Reviews viewReviewsById(long Id);
    Reviews viewAllReviews(Reviews review);
    Authors viewBookAuthorsById(long Id);
    Authors viewAllAuthors(Authors author);

    // TODO: add other operations here!
}
