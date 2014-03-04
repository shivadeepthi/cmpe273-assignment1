package edu.sjsu.cmpe.library.domain;
import static com.google.common.base.Preconditions.checkNotNull;



public class Reviews {
    private long Id;
    private int Rating;
    private String Comment;
    
    public Reviews()
    {
    this.Rating=checkNotNull(Rating,"this is default");
    }


    /**
     * @return the Id
     */
    public long getId() {
	return Id;
    }

    /**
     * @param Id
     *            the Id to set
     */
    public void setId(long Id) {
	this.Id = Id;
    }
    
    public int getRating() {
    	return Rating;
        }

        
        public void setRating(int Rating) {
    	this.Rating = Rating;
        }
    
    public String getComment() {
    	return Comment;
        }
    
    public void setComment(String Comment) {
    	this.Comment = Comment;
        }
}
    
    
    