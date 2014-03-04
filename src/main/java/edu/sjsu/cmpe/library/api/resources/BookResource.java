package edu.sjsu.cmpe.library.api.resources;

//import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
//import java.util.*;
import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.library.domain.*;
import edu.sjsu.cmpe.library.dto.*;
import edu.sjsu.cmpe.library.repository.BookRepositoryInterface;

@Path("/v1/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {
    /** bookRepository instance */
    private final BookRepositoryInterface bookRepository;

    /**
     * BookResource constructor
     * 
     * @param bookRepository
     *            a BookRepository instance
     */
    public BookResource(BookRepositoryInterface bookRepository) {
	this.bookRepository = bookRepository;
    }

    @GET
    @Path("/{isbn}")
    @Timed(name = "view-book")
    public BookDto getBookByIsbn(@PathParam("isbn") LongParam isbn) {
	Book book = bookRepository.getBookByISBN(isbn.get());
	BookDto bookResponse = new BookDto(book);
	bookResponse.addLink(new LinkDto("view-book", "/books/" + book.getIsbn(),"GET"));
	bookResponse.addLink(new LinkDto("update-book","/books/" + book.getIsbn(), "PUT"));
	bookResponse.addLink(new LinkDto("Delete-book","/books/" + book.getIsbn(), "DELETE"));
	bookResponse.addLink(new LinkDto("create-review", "/books/" + book.getIsbn() +"/reviews","POST"));
	bookResponse.addLink(new LinkDto("view-all-reviews","/books/" + book.getIsbn() + "/reviews", "GET"));
	// add more links

	return bookResponse;
    }

    @POST
    @Timed(name = "create-book")
    public Response createBook(Book request) {
	// Store the new book in the BookRepository so that we can retrieve it.
	Book savedBook = bookRepository.saveBook(request);

	String location = "/books/" + savedBook.getIsbn();
	BookDto bookResponse = new BookDto(savedBook);
	bookResponse.addLink(new LinkDto("view-book", location, "GET"));
	bookResponse.addLink(new LinkDto("update-book", location, "PUT"));
	bookResponse.addLink(new LinkDto("delete-book", location, "DELETE"));
	bookResponse.addLink(new LinkDto("create-review", location+"/reviews", "POST"));
	// Add other links if needed

	return Response.status(201).entity(bookResponse).build();
    }
    
    @PUT
    @Path("books/{isbn}")
    @Timed(name = "update-book")
    public BookDto setBookByIsbn(@PathParam("isbn") LongParam isbn) {
    	Book book = bookRepository.updateBookByIsbn(isbn.get());
    	BookDto bookResponse = new BookDto(book);
    	bookResponse.addLink(new LinkDto("view-book", "/books/" + book.getIsbn(),"GET"));
    	bookResponse.addLink(new LinkDto("new-book","/books/" + book.getIsbn(), "POST"));
    	bookResponse.addLink(new LinkDto("delete-book","/books/" + book.getIsbn(), "DELETE"));
    	bookResponse.addLink(new LinkDto("create-reviews","/books/" + book.getIsbn()+"/reviews", "POST"));
    	bookResponse.addLink(new LinkDto("View-all-reviews","/books/" + book.getIsbn()+"/reviews", "GET"));
    	
    	// add more links

    	return bookResponse;
        }
    @DELETE
    @Path("/{isbn}")
    @Timed(name = "delete-book")
    public BookDto deleteBookByIsbn(@PathParam("isbn") LongParam isbn){
    	Book book = bookRepository.deleteBookByIsbn(isbn.get());
    	BookDto bookResponse = new BookDto(book);
    	bookResponse.addLink(new LinkDto("new-book","/books/" + book.getIsbn(), "POST"));
    	return bookResponse;	
    }
    
    @POST
    @Timed(name = "create-review")
    public Response createReview(Reviews request) {
	
	Reviews savedReview = bookRepository.createReviews(request);
	Book book=new Book();

	String location = "/books/" +book.getIsbn() +"/reviews/"+savedReview.getId();
	ReviewDto reviewResponse = new ReviewDto(savedReview);
	reviewResponse.addLink(new LinkDto("view-book", location, "GET"));
	return Response.status(201).entity(reviewResponse).build();
    }
    
    @GET
    @Path("/{isbn}/reviews/{id}")
    @Timed(name = "view-bookReviews-Id")
    public ReviewDto getReviewById(@PathParam("isbn") LongParam isbn,@PathParam("Rid") LongParam Rid) {
	Reviews review = bookRepository.viewReviewsById(Rid.get());
	Book book=bookRepository.getBookByISBN(isbn.get());
	ReviewDto reviewResponse = new ReviewDto(review);
	reviewResponse.addLink(new LinkDto("view-review", "/books/" +book.getIsbn()+"/reviews/"+ review.getId(),"GET"));
	return reviewResponse;
    }
    
    @GET
    @Path("/reviews")
    @Timed(name = "view-all-reviews")
   public ReviewDto viewAllReviews(Reviews request)
   {
    	 
     Reviews review = bookRepository.viewAllReviews(request);
     ReviewDto reviewResponse = new ReviewDto(review);
     return reviewResponse;
   }
    	
    @GET
    @Path("/{isbn}/authors/{id}")
    @Timed(name = "view-bookauthors-Id")
    public AuthorsDto viewBookAuthorsById(@PathParam("id") LongParam id,@PathParam("isbn") LongParam isbn)
    {
    	Authors authors=bookRepository.viewBookAuthorsById(id.get());
    	Book book=bookRepository.getBookByISBN(isbn.get());
    	AuthorsDto authorsResponse = new AuthorsDto(authors);
    	authorsResponse.addLink(new LinkDto("view-authors-by-Id", "/books/" +book.getIsbn()+"/authors/"+ authors.getId(),"GET"));
    	return authorsResponse;
    	
    }
    
    @GET
    @Path("/authors")
    @Timed(name = "view-bookauthors")
    public AuthorsDto  viewAllAuthors(Authors request){
    	
    	Authors authors=bookRepository.viewAllAuthors(request);
    	AuthorsDto authorsResponse=new AuthorsDto(authors);
    	return authorsResponse;
    	
    }
}
    
    		
    
    


