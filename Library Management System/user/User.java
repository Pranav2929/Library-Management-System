package system.user;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import system.util.SystemUtil;

import system.book.Book;
import system.book.BookList;
import system.exception.BookException;



public class User {
    private String email;
    private String password;
    private List<Book> bookList;
    private UserPlan plan;
    public static List<Book> bookListUser = new ArrayList<>();

    //Constructor
    public User(String email, String password, UserPlan plan) {
        if (!SystemUtil.isValid(email) || !SystemUtil.isValid(password)) {
            throw new IllegalArgumentException("Please enter a valid email and password.");
        }
        this.email = email;
        this.password = password;
        this.bookList = new ArrayList<>();
        this.plan = plan;
    }
 // Static method to create a User object from parameters. Validates input and creates a UserPlan.
    public static User createUser(String... params) {
        if (params.length != 4) {
            return null; 
        }

        String email = params[0];
        String password = params[1];

        if (!SystemUtil.isValid(email) || !SystemUtil.isValid(password)) {
            return null; 
        }

        UserPlan plan = UserPlan.createPlan(params[2], params[3]);
        if (plan == null) {
            return null; 
        }

        return new User(email, password, plan);
    }
    
 // Adds a book to the user's book list
    public void addToBooklist(Book book) {
        if (this.plan != null && this.plan.isActive()) {
            bookList.add(book);
            bookListUser.add(book);
        } else {
            throw new IllegalStateException("User plan is not active. Cannot add books.");
        }
    }

 // Displays all books in the user's book list
    public void displayBookList() {
        for (Book book : bookList) {
            System.out.println(book);
        }
    }
 // Finds and returns a book by its index in the user's book list.
    public Book findBookByIndex(int index) {
        if (index >= 0 && index < bookList.size()) {
            return bookList.get(index);
        } else {
            System.out.println("Book not found at index: " + index);
            return null;
        }
    }
 // Saves the user's book list
    public void saveBookList() throws IOException {
        String userFileName = getEmail().replaceAll("@", "_").replaceAll("\\.", "_") + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(userFileName))) {
            for (Book book : bookList) {
                writer.write(book.toString());
                writer.newLine();
            }
        }
    }
 // Loads the user's book list from a file
    public List<Book> loadBookList() throws IOException {
        String userFileName = getEmail().replaceAll("@", "_").replaceAll("\\.", "_") + ".txt";
        List<Book> loadedBooks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(userFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Book book = Book.createBook(line.split(","));
                loadedBooks.add(book);
            }
            this.bookList = loadedBooks;
            System.out.println("Book list loaded successfully!");
        } catch (IOException e) {
            System.err.println("Error reading file: " + userFileName);
            throw new IOException("Error reading file: " + userFileName, e);
        }
        return loadedBooks;
    }
 // Adds a book to the user's book list
    public void addBook(int index, BookList booklist) throws BookException {
        if (index < 0 || index >= booklist.getSize()) {
            throw new BookException("Invalid book index: " + index);
        }
        Book book = booklist.findBookByIndex(index);
        bookListUser.add(book);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (SystemUtil.isValid(email)) {
            this.email = email;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (SystemUtil.isValid(password)) {
            this.password = password;
        }
    }

    public UserPlan getPlan() {
        return plan;
    }

    public void setPlan(UserPlan plan) {
        if (plan != null) {
            this.plan = plan;
        }
    }

    public int getBookListSize() {
        return bookList.size();
    }

    public List<Book> getBookListUser() {
        return bookListUser;
    }

    @Override
    public String toString() {
        return "Email: " + email + ", Plan: " + plan;
    }
}


