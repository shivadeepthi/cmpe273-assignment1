package edu.sjsu.cmpe.library.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.library.domain.Authors;

@JsonPropertyOrder(alphabetic = true)
public class AuthorsDto extends LinksDto {
    private Authors authors;

    /**
     * @param book
     */
    public AuthorsDto(Authors authors) {
	super();
	this.authors = authors;
    }

    /**
     * @return the book
     */
    public Authors getAuthors() {
	return authors;
    }

    /**
     * @param book
     *            the book to set
     */
    public void setAuthors(Authors authors) {
	this.authors=authors;
    }
}
