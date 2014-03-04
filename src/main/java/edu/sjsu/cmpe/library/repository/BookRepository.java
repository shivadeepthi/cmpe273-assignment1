package edu.sjsu.cmpe.library.repository;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.concurrent.ConcurrentHashMap;

//import javax.ws.rs.PathParam;

//import com.yammer.dropwizard.jersey.params.LongParam;

import edu.sjsu.cmpe.library.domain.Authors;
import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Reviews;


//import java.util.*;


public class BookRepository implements BookRepositoryInterface {
    /** In-memory map to store books. (Key, Value) -> (ISBN, Book) */
    private final ConcurrentHashMap<Long, Book> bookInMemoryMap;
    private final ConcurrentHashMap<Long, Reviews> reviewMap;
    private final ConcurrentHashMap<Long, Authors> AuthorsInMap;

    /** Never access this key directly; instead use generateISBNKey() */
    private long isbnKey;
    private long id;
    private long Rid;
    

    public BookRepository(ConcurrentHashMap<Long, Book> bookMap,ConcurrentHashMap<Long, Reviews> reviews,ConcurrentHashMap<Long, Authors> AuthorsMap) {
	checkNotNull(bookMap, "bookMap must not be null for BookRepository");
	bookInMemoryMap = bookMap;
	isbnKey = 0;
	reviewMap=reviews;
	AuthorsInMap=AuthorsMap;
	id=0;
	Rid=0;
	
    }

    /**
     * This should be called if and only if you are adding new books to the
     * repository.
     * 
     * @return a new incremental ISBN number
     */
    private final Long generateISBNKey() {
	
	return Long.valueOf(++isbnKey);
    }
    
    private final Long generateAuthorsId() {
    	
    	return Long.valueOf(++id);
        }
    
private final Long generateReviewsId() {
    	
    	return Long.valueOf(++Rid);
        }
    
    

    /**
     * This will auto-generate unique ISBN for new books.
     */
    @Override
    public Book saveBook(Book newBook) {
	checkNotNull(newBook, "newBook instance must not be null");
	// Generate new ISBN
	Long isbn = generateISBNKey();
	newBook.setIsbn(isbn);
	newBook.getLanguage();
	newBook.getPublicationDate();
    newBook.getStatus();
	newBook.getTitle();
	Authors authors=new Authors();
	Long id = generateAuthorsId();
	authors.setId(id);
	authors.getName();
	Reviews review=new Reviews();
	review.getComment();
	
	
	// TODO: create and associate other fields such as author

	// Finally, save the new book into the map
	bookInMemoryMap.putIfAbsent(isbn, newBook);

	return newBook;
    }

    /**
     * @see edu.sjsu.cmpe.library.repository.BookRepositoryInterface#getBookByISBN(java.lang.Long)
     */
    @Override
    public Book getBookByISBN(long isbn) {
	checkArgument(isbn > 0,
		"ISBN was %s but expected greater than zero value", isbn);
	return bookInMemoryMap.get(isbn);
    }
    
    public Book deleteBookByIsbn(long isbn)
    {
    	checkArgument(isbn > 0,
    			"ISBN was %s but expected greater than zero value", isbn);
    	return bookInMemoryMap.remove(isbn);
    }
    
    public Book updateBookByIsbn(long isbn)
    {
    	checkArgument(isbn > 0,
    			"ISBN was %s but expected greater than zero value", isbn);
    	return bookInMemoryMap.get(isbn);
    }
   
    
    public Reviews createReviews(Reviews newreview)
    {
    	Long Rid=generateReviewsId();
    	newreview.setId(Rid);
    	newreview.getRating();
    	newreview.getComment();
    	reviewMap.putIfAbsent(id,newreview);
    	return newreview;
    }
    public Reviews viewReviewsById(long Id)
    {
    	checkArgument(Id > 0,
    			"Id was %s but expected greater than zero value", Id);
    		return reviewMap.get(Id);
    }
   public  Reviews viewAllReviews(Reviews reviews)
    {
	   
	  reviews.getId();
	  reviews.getComment();
	  reviews.getRating();
	  return reviews;
	   
    }
    public Authors viewBookAuthorsById(long Id)
    {
    	checkArgument(Id > 0,
    			"Id was %s but expected greater than zero value", Id);
    		return AuthorsInMap.get(Id);
    }
    public Authors viewAllAuthors(Authors authors)
    {
    	authors.getId();
    	authors.getName();
    	return authors;
    }

	

}
