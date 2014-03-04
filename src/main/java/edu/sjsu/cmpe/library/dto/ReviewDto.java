package edu.sjsu.cmpe.library.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.library.domain.Reviews;

@JsonPropertyOrder(alphabetic = true)
public class ReviewDto extends LinksDto {
    private Reviews reviews;

    
    public ReviewDto(Reviews reviews) {
	super();
	this.reviews = reviews;
    }

   
    public Reviews getReviews() {
	return reviews;
    }

    
    public void setReviews(Reviews reviews) {
	this.reviews=reviews;
    }
}
