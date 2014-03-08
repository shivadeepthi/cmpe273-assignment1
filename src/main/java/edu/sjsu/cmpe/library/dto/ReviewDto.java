package edu.sjsu.cmpe.library.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;



import edu.sjsu.cmpe.library.domain.Book;
//import edu.sjsu.cmpe.library.domain.Reviews;

@JsonPropertyOrder(alphabetic = true)
public class ReviewDto extends LinksDto {
    private Book.Reviews review;

    
    public ReviewDto(Book.Reviews review) {
	super();
	this.review=review;
    }
    
    public ReviewDto() {
    	super();
    	
        }

   
    public Book.Reviews getReviews() {
	return review;
    }

    
    public void setReviews(Book.Reviews review) {
	this.review=review;
    }
}
