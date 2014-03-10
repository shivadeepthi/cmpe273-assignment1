package edu.sjsu.cmpe.library.api.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.validation.constraints.*;

//import com.yammer.dropwizard.jersey.caching.CacheControl;
//import java.util.*;
import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.library.domain.*;
import edu.sjsu.cmpe.library.domain.Book.Authors;
//import edu.sjsu.cmpe.library.domain.Book.Authors;
import edu.sjsu.cmpe.library.domain.Book.Reviews;
import edu.sjsu.cmpe.library.dto.*;
import edu.sjsu.cmpe.library.repository.BookRepositoryInterface;

@Path("/v1/books/")
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
    public Response getBookByIsbn(@NotNull @PathParam("isbn") LongParam isbn,@Context Request request) {
	Book book = bookRepository.getBookByISBN(isbn.get());
	CacheControl cc = new CacheControl();
    cc.setMaxAge(86400);
	
	 EntityTag etag = new EntityTag(Integer.toString(book.hashCode()));
	 ResponseBuilder builder = request.evaluatePreconditions(etag);
	 if(builder == null){
         builder = Response.ok(book);
         builder.tag(etag);
 }
	builder.cacheControl(cc);
	
	BookDto bookResponse = new BookDto(book);
	bookResponse.addLink(new LinkDto("view-book", "/books/" + book.getIsbn(),"GET"));
	bookResponse.addLink(new LinkDto("update-book","/books/" + book.getIsbn(), "PUT"));
	bookResponse.addLink(new LinkDto("Delete-book","/books/" + book.getIsbn(), "DELETE"));
	bookResponse.addLink(new LinkDto("create-review", "/books/" + book.getIsbn() +"/reviews","POST"));
	bookResponse.addLink(new LinkDto("view-all-reviews","/books/" + book.getIsbn() + "/reviews", "GET"));
	// add more links

	//return bookResponse;
	return builder.build();
    }
    
    

    
    @POST
    @Timed(name = "create-book")
    public Response createBook(Book request) {
	// Store the new book in the BookRepository so that we can retrieve it.
    	System.out.println("i m here");
	Book savedBook = bookRepository.saveBook(request);
	System.out.println("passesSS");
	String location = "/books/" + savedBook.getIsbn();
	System.out.println(location);
	BookDto bookResponse = new BookDto(savedBook);
	System.out.print(bookResponse);
	bookResponse.addLink(new LinkDto("view-book", location, "GET"));
	bookResponse.addLink(new LinkDto("update-book", location, "PUT"));
	bookResponse.addLink(new LinkDto("delete-book", location, "DELETE"));
	bookResponse.addLink(new LinkDto("create-review", location+"/reviews", "POST"));
	// Add other links if needed

	return Response.status(201).entity(bookResponse).build();
    }
    
    @PUT
    @Path("/{isbn}")
    @Timed(name = "update-book")
    public BookDto setBookByIsbn(@NotNull @PathParam("isbn") LongParam isbn,Book response) {
    	Book book = bookRepository.updateBookByIsbn(isbn.get(),response);
    	BookDto bookResponse = new BookDto(book);
    	bookResponse.addLink(new LinkDto("view-book", "/books/" + book.getIsbn(),"GET"));
    	bookResponse.addLink(new LinkDto("new-book","/books/" + book.getIsbn(), "POST"));
    	bookResponse.addLink(new LinkDto("delete-book","/books/" + book.getIsbn(), "DELETE"));
    	bookResponse.addLink(new LinkDto("create-reviews","/books/" + book.getIsbn()+"/reviews", "POST"));
    	bookResponse.addLink(new LinkDto("View-all-reviews","/books/" + book.getIsbn()+"/"
    			+ "reviews", "GET"));
    	
    	// add more links

    	return bookResponse;
        }
    @DELETE
    @Path("/{isbn}")
    @Timed(name = "delete-book")
    public BookDto deleteBookByIsbn(@NotNull @PathParam("isbn") LongParam isbn){
    	Book book = bookRepository.deleteBookByIsbn(isbn.get());
    	BookDto bookResponse = new BookDto(book);
    	bookResponse.addLink(new LinkDto("new-book","/books/" + book.getIsbn(), "POST"));
    	return bookResponse;	
    }
    
    @POST
    @Path("{isbn}/reviews")
    @Timed(name = "create-review")
    public Response createReview(@PathParam ("isbn") LongParam isbn,Book.Reviews request) {
	System.out.println("i m insdie review");
	Book.Reviews savedReview = bookRepository.createReviews(request);
	String location = "/books/" +isbn.get() +"/reviews/"+savedReview.getRid();
	System.out.println(location);
	ReviewDto reviewResponse = new ReviewDto(savedReview);
	reviewResponse.addLink(new LinkDto("view-book", location, "GET"));
	return Response.status(201).entity(reviewResponse).build();
    }
    
    @GET
    @Path("/{isbn}/reviews/{id}")
    @Timed(name = "view-bookReviews-Id")
    public ReviewDto getReviewById(@PathParam("isbn") LongParam isbn,@PathParam("id") LongParam Rid) {
	Book.Reviews review = bookRepository.viewReviewsById(Rid.get());
	System.out.println(review.getComment()+"i am inside get review by id");
	ReviewDto reviewResponse = new ReviewDto(review);
	reviewResponse.addLink(new LinkDto("view-review", "/books/" +isbn.get()+"/reviews/"+ review.getRid(),"GET"));
	return reviewResponse;
    }
    
    @GET
    @Path("{isbn}/reviews")
    @Timed(name = "view-all-reviews")
   public ReviewDto viewAllReviews(@PathParam("isbn") LongParam isbn)
   {
    	 
     Book review = bookRepository.viewAllReviews(isbn.get());
     System.out.println("i am inside review all");
     ReviewDto reviewResponse = new ReviewDto(review.getReviews());
     return reviewResponse;
   }
    	
   @GET
    @Path("/{isbn}/authors/{id}")
    @Timed(name = "view-bookauthors-Id")
    public AuthorsDto viewBookAuthorsById(@NotNull @PathParam("id") LongParam id,@NotNull @PathParam("isbn") LongParam isbn)
    {
    	Book authors=bookRepository.viewBookAuthorsById(id.get());
    	
    	AuthorsDto authorsResponse = new AuthorsDto(authors.getAuthors());
    	authorsResponse.addLink(new LinkDto("view-authors-by-Id", "/books/" +authors.getIsbn()+"/authors/"+ new Authors().getId(),"GET"));
    	return authorsResponse;
    	
    }
    
   @GET
    @Path("/{isbn}/authors")
    @Timed(name = "view-bookauthors")
    public AuthorsDto  viewAllAuthors(@PathParam("isbn") LongParam isbn){
    	
    	Book author=bookRepository.viewAllAuthors(isbn.get());
    	
    	
    	AuthorsDto authorsResponse=new AuthorsDto(author.getAuthors());
    	System.out.print(author.getAuthors());
    	 return authorsResponse;
   }
    	
   
   
}
    
    		
    
    


