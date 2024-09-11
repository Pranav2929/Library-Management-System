package system.book;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import system.exception.BookException;
import system.util.SystemUtil;

public class BookList {

	// List to store the books
    private ArrayList<Book> bestsellers = new ArrayList<>();
    
 // Method to create a list of books from a CSV file
    public void loadBookList(String csvFile) throws BookException {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            
            String line = br.readLine();
            
            while ((line = br.readLine()) != null) {
                String[] stringArray = SystemUtil.lineReader(line);
                
                    	int index=bestsellers.size();
                    	String name = stringArray.length > 0 ? stringArray[0] : "Null";
                    	String author = stringArray.length > 1 ? stringArray[1] : "Null";
                    	String originalLanguage = stringArray.length > 2 ? stringArray[2] : "Null";
                    	int firstPublished = stringArray.length > 3 && !stringArray[3].isEmpty() ? Integer.parseInt(clean(stringArray[3])) : 0;
                    	float millionSales = stringArray.length > 4 && !stringArray[4].isEmpty() ? Float.parseFloat(clean(stringArray[4])) : 0.0F;
                    	String genre = stringArray.length > 5 ? stringArray[5] : "Null ";
                    	Book book = new Book(index, name, author, originalLanguage, firstPublished, millionSales, genre);
                    	bestsellers.add(book);
            }
                    

            } catch (IOException e) {
            throw new BookException("Error reading the CSV file ");
            }
        }
// Helper method to clean and trim a string
private String clean(String value) {
	return (value==null)? "":value.trim();
}
// Method to print the list of books
public void printList() {
    for (Book book : bestsellers) {
        System.out.println(book);
    }
}

// String toString 
public String toString() {
    if (bestsellers.isEmpty()) {
        return "Book list is empty";
    }
    StringBuilder sb = new StringBuilder();
    for (Book book : bestsellers) {
        sb.append(book).append("\n");
    }
    return sb.toString();
}



//Method to find a book by its index
public Book findBookByIndex(int index) {
    for (Book book : bestsellers) {
        if (book.getIndex() == index) {
            return book;
        }    
    }
    return null;
}

public int getSize() {
    return bestsellers.size();
}

// Method to search for books by a search term
public void searchInBookList(String searchTerm) {
    for (Book book : bestsellers) {
        if (book.getName().contains(searchTerm)) {
            System.out.println(book);
        }
    }
}

}













