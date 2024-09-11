package system.book;
import system.user.User;
import system.util.SystemUtil;
public class Book implements BookDownloadable, BookReadable {
	
	// Implements the BookDownloadable interface 
	public boolean download(User user) {
        if (user.getPlan() != null && user.getPlan().isActive() && user.getPlan().isVip()) {
            return true;
        } else {
            return false;
        }
    }
	
	//Implements the BookReadable interface
	public boolean read(User user) {
		if (user.getPlan().isActive()) {
			return true;
		}
		return false;
	}
	// Attributes of the Book class
		  private int index;
		  private String name;
		  private String author;
		  private String originalLanguage;
		  private int firstPublished;
		  private float salesInMillions;
		  private String genre;
		  
		// Constructor
		  public Book (int index, String name , String author , String originalLanguage , int firstPublished, float salesInMillions, String genre)
		  {
			  this.index= index;
			  this.name = name;
			  this.author = author;
			  this.originalLanguage = originalLanguage;
			  this.firstPublished = firstPublished;
			  this.salesInMillions = salesInMillions;
			  this.genre = genre ;
			    
		  }
		  
		  


		// setters
		  public void setIndex (int index) {
			  this.index= index;
		  }
		  
		  public void setName (String name) {
			  this.name= name;
		  }
		  public void setAuthor (String author) {
			  this.author= author;
		  }
		  public void setOriginalLanguage (String originalLanguage) {
			  this.originalLanguage= originalLanguage;
		  }
		  public void setFirstPublished (int firstPublished) {
			  this.firstPublished= firstPublished;
		  }
		  public void setSalesInMillions (float salesInMillions) {
			  this.salesInMillions= salesInMillions;
		  }
		  public void setGenre (String genre) {
			  this.genre= genre;
		  }
		  
		  
		  //getters
		  public int getIndex(){
			  return this.index;
		  }
		  public String getName() {
			 return this.name;
		  }
		  public String getAuthor() {
			  return this.author;
		  }
		  public String getOriginalLanguage() {
			  return this.originalLanguage;
		  }
		  public int getFirstPublished() {
			  return this.firstPublished;
		  }
		  public float getSalesInMillions() {
			  return this.salesInMillions;
		  }
		  public String getGenre() {
			  return this.genre;
		  }
		  
		  
		//toString method to provide a string representation of the Book object
		  public String toString() {
			    return " Book " + "["+index +"]"+" { Book =' " + name + "'"+ " , Author(s)= " + author +
			           ", Original Language= " + originalLanguage + ", First Published=" + firstPublished + ", Approximate sales in milions=" + salesInMillions
		           + ", Genre="+genre +"}";
		}
		  
		// Method to create Book
		  public static Book createBook(String... params) {
		        if (params.length != 7) {
		            return null; 
		        }
		       
		        for (String param : params) {
		            if (!SystemUtil.isValid(param)) {
		                return null;
		            }
		        }

		        int index;
		        int firstPublished;
		        float millionSales;

		        try {
		            index = Integer.parseInt(params[0]);
		        } catch (NumberFormatException e) {
		            return null; 
		        }

		        try {
		            firstPublished = Integer.parseInt(params[4]);
		        } catch (NumberFormatException e) {
		            return null; 
		        }

		        try {
		            millionSales = Float.parseFloat(params[5]);
		        } catch (NumberFormatException e) {
		            return null; 
		        }

		        String name = params[1];
		        String author = params[2];
		        String originalLanguage = params[3];
		        String genre = params[6];

		        return new Book(index, name, author, originalLanguage, firstPublished, millionSales, genre);
		    }


		
		
		
}
