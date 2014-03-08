package edu.sjsu.cmpe.library.dto;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.library.domain.Book;
//import edu.sjsu.cmpe.library.domain.Book.Authors;


@JsonPropertyOrder(alphabetic = true)
public class AuthorsDto extends LinksDto {
    private ArrayList<Book.Authors> author;

    /**
     * @param book
     */
    public AuthorsDto(ArrayList<Book.Authors> author) {
	super();
	this.author=author;
    }
    public AuthorsDto() {
    	super();
    	
        }
    public AuthorsDto(Book book) {
    	super();
    	
        }

    /**
     * @return the book
     */
    public ArrayList<Book.Authors> getAuthors() {
	return author;
    }

    /**
     * @param book
     *            the book to set
     */
    public void setAuthors(ArrayList<Book.Authors> author) {
	this.author=author;
    }
}
