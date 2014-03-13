package edu.sjsu.cmpe.library.repository;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

//import javax.ws.rs.PathParam;



import com.yammer.dropwizard.jersey.params.LongParam;






import edu.sjsu.cmpe.library.domain.Book.Authors;
import edu.sjsu.cmpe.library.domain.Book;
//import edu.sjsu.cmpe.library.domain.Book.Reviews;
import edu.sjsu.cmpe.library.domain.Book.Reviews;


//import java.util.*;


public class BookRepository implements BookRepositoryInterface {
    /** In-memory map to store books. (Key, Value) -> (ISBN, Book) */
    private final ConcurrentHashMap<Long, Book> bookInMemoryMap;
   private final ConcurrentHashMap<Long, Reviews> ReviewInMemoryMap;
   private final ConcurrentHashMap<Long, Book.Authors> AuthorsInMemoryMap;
    
    /** Never access this key directly; instead use generateISBNKey() */
    private long isbnKey;
    private long id;
    private long Rid;
    //Authors newAuthors;
    

    public BookRepository(ConcurrentHashMap<Long, Book> bookMap) {
	checkNotNull(bookMap, "bookMap must not be null for BookRepository");
	bookInMemoryMap = bookMap;
	//Authors newAuthors=new Authors();
	isbnKey = 0;
	ReviewInMemoryMap=new ConcurrentHashMap<Long, Reviews>();
	AuthorsInMemoryMap=new ConcurrentHashMap<Long, Book.Authors>();
	
	
	
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
    	System.out.println("in repo");
	checkNotNull(newBook, "newBook instance must not be null");
	// Generate new ISBN
	
	Long isbn = generateISBNKey();
	newBook.setIsbn(isbn);
	System.out.print("creadted isbn");
	Long id = generateAuthorsId();
	Authors a=new Authors();
	a.setId(id);
	System.out.println("authirs d"+id);
	System.out.println(a.getName());
	
	
	//Long Rid=generateReviewsId();
	//Reviews.setRid(Rid);
	
	// TODO: create and associate other fields such as author

	// Finally, save the new book into the map
	System.out.print(isbn);
	
	bookInMemoryMap.putIfAbsent(isbn, newBook);
	AuthorsInMemoryMap.putIfAbsent(id, a );

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
   
    
    public Book.Reviews createReviews(Book.Reviews newreview)
    {
    	Long Rid=generateReviewsId();
    	Reviews r=new Reviews();
    	r.setRid(Rid);
    	System.out.println(r.getRating()+"rating");
    	System.out.println(r.getRid());
    	//bookInMemoryMap.putIfAbsent(key, value)
    	ReviewInMemoryMap.putIfAbsent(Rid, newreview);
    	return newreview;
    }
    public Book.Reviews viewReviewsById(long Id)
    {
    	checkArgument(Id > 0,
    			"Id was %s but expected greater than zero value", Id);
    	return ReviewInMemoryMap.get(Id);
    		
    }
   public  Book viewAllReviews(long isbn)
    {
	   
	  return bookInMemoryMap.get(isbn);
	   
    }
    public Book viewBookAuthorsById(long Id)
    {
    	checkArgument(Id > 0,
    			"Id was %s but expected greater than zero value", Id);
    	return bookInMemoryMap.get(Id);
    }
    public Book viewAllAuthors(long isbn)
    {
    	System.out.print(isbn+"inside repo");
    	return bookInMemoryMap.get(isbn);
    }

	

}
