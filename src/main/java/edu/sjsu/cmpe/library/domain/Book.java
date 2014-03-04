package edu.sjsu.cmpe.library.domain;

import static com.google.common.base.Preconditions.checkNotNull;


public class Book {
    private long isbn;
    private String title;
    private String Language;
    private String publicationDate;
    private long numberOfPages;
    private String status;
    

    /**
     * @return the isbn
     */
    public Book()
    {
    	this.title=checkNotNull(title,"this is default title");
    	this.publicationDate=checkNotNull(publicationDate,"this is default date");
    	this.status=checkNotNull(status,"available");
    }
    public long getIsbn() {
	return isbn;
    }

    
    public void setIsbn(long isbn) {
	this.isbn = isbn;
    }

   
    public String getTitle() {
	return title;
    }

    
    public void setTitle(String title) {
	this.title = title;
    }
    
    public String getLanguage() {
    	return Language;
        }

        
        public void setLanguage(String language) {
    	this.Language = language;
        }
        
        public String getPublicationDate() {
        	return publicationDate;
            }

            
            public void setPublicationDate(String publicationDate) {
        	this.publicationDate = publicationDate;
            }
            
            public long getNumberOfPages() {
            	return numberOfPages;
                }

                
                public void setNumberOfPages(long NumberOfPages) {
            	this.numberOfPages = NumberOfPages;
                }
            
            public String getStatus() {
            	return status;
                }

                
                public void setStatus(String status) {
            	this.status = status;
            			
                }
}
