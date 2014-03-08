package edu.sjsu.cmpe.library.domain;

//import static com.google.common.base.Preconditions.checkNotNull;





import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Book {
	
	
	@JsonProperty
    private long isbn;
	@JsonProperty("title")
    private String title;
	@JsonProperty("language")
    private String Language;
	@JsonProperty("publication-date")
    private String publicationDate;
	@JsonProperty("num-pages")
    private long numberOfPages;
	@JsonProperty("status")
    private String status;
	@JsonProperty("authors")
	private ArrayList<Authors> authors;
	@JsonProperty("reviews")
	private Reviews reviews;
	
	public  static class Authors
	{
		
		@JsonProperty("id")
	    private  static Long Id;
	    @JsonProperty("name")
	    private static String authorsname;
		public Authors(){
			
		}
		
		public long getId() {
	    	return Id;
	        }

	        
	        public  void setId(long id) {
	    	Id = id;
	        }
	        
	    public  String getName() {
	    	System.out.println(authorsname);
	    	return authorsname;
	        }

	        
	        public  void setName(String authors) {
	        	
	    	authorsname=authors;
	    			
	        }
	}
	public static class Reviews
	{ 
		
		@JsonProperty("id")
	    private static long Rid;
		@JsonProperty("rating")
	    private static int Rate;
		@JsonProperty("comment")
	    private static String reviews;
		
		public Reviews()
		{
			
			
		}
		 public long getRid() {
	     	return Rid;
	         }

	         
	         public  void setRid(long Id) {
	     	Rid = Id;
	         }
	         
	         public int getRating() {
	         	return Rate;
	             }

	             
	             public void setRating(int Rating) {
	         	Rate = Rating;
	             }
	         
	         public  String getComment() {
	         	return reviews;
	             }
	         
	         public  void setComment(String Comment) {
	         	reviews=Comment;
	         }
		
	}
    
    

   
    public Book()
    {
    	
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
                public ArrayList<Authors> getAuthors() {
                	return authors;
                    }

                    
                    public void setAuthors(ArrayList<Authors> authors) {
                	this.authors=authors;
                			
                    }
                 public Reviews getReviews() {
                    	return reviews;
                        }

                        
                        public void setReviews(Reviews review) {
                    	this.reviews=review;
                    			
                        }
                
              
                
                
                
                
                
                   
}
