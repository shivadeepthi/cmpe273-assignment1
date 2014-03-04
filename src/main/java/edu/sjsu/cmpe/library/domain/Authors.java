package edu.sjsu.cmpe.library.domain;
import static com.google.common.base.Preconditions.checkNotNull;



public class Authors   {
    private Long Id;
    private String Name;
    
    public Authors()
    {
    this.Name=checkNotNull(Name,"this is default name");
    }

    // add more fields here

    /**
     * @return the Id
     */
    public Long getId() {
	return Id;
    }

    /**
     * @param Id
     *            the Id to set
     */
    public void setId(Long Id) {
	this.Id = Id;
    }
    
    public String getName() {
    	return Name;
        }
    
    public void setName(String Name) {
    	this.Name = Name;
        }
}
    
    
    