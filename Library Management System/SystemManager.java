package system;


import java.util.InputMismatchException;
import java.util.Scanner;

import system.book.Book;
import system.book.BookList;
import system.exception.BookException;
import system.exception.UserException;
import system.user.User;
import system.user.UserList;


public class SystemManager {
    // Define constants for Admin menu 
    private static final int CREATE_BOOK_LIST = 1;
    private static final int SHOW_BOOK_LIST = 2;
    private static final int SEARCH_IN_BOOK_LIST = 3;
    private static final int CREATE_USER = 4;
    private static final int SHOW_USER_LIST = 5;
    private static final int SAVE_USER_LIST = 6;
    private static final int LOAD_USER_LIST = 7;
    private static final int LOGIN_USER = 8;
    private static final int EXIT = 9;
    
    // Define constants for user menu
    private static final int SEARCH = 10;
    private static final int ADD_BOOK_IN_MY_LIST = 11;
    private static final int SHOW_MY_BOOK_LIST = 12;
    private static final int READ_BOOK = 13;
    private static final int DOWNLOAD_BOOK = 14;
    private static final int CHANGE_PASSWORD = 15;
    private static final int LOG_OFF = 16;
    
    private static User currentUser;
    private static UserList userList = new UserList();
    private static BookList bookList = new BookList();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            try {
                if (currentUser == null) {
                    showAdminMenu(scanner);
                } else {
                    showUserMenu(scanner);
                }
            } catch (BookException | UserException e) {
                System.err.println("An error occurred: " + e.getMessage());
                
            }
        }
    }

    // Method to show Admin Menu
    private static void showAdminMenu(Scanner scanner) throws BookException, UserException {
        System.out.println("|| Menu - Mini-System: OOP/A2 ||");
        System.out.println("1. Load Booklist");
        System.out.println("2. Show Booklist");
        System.out.println("3. Search in the list");
        System.out.println("4. Create user");
        System.out.println("5. Show users");
        System.out.println("6. Save users");
        System.out.println("7. Load users");
        System.out.println("8. Login user");
        System.out.println("9. Exit");
        System.out.print("Choose an option: ");

        int option = readOption(scanner);

        switch (option) {
            case CREATE_BOOK_LIST:
                createBookList();
                break;
            case SHOW_BOOK_LIST:
                showBookList();
                break;
            case SEARCH_IN_BOOK_LIST:
                searchInBookList();
                break;
            case CREATE_USER:
                createUser();
                break;
            case SHOW_USER_LIST:
                showUserList();
                break;
            case SAVE_USER_LIST:
                saveUserList();
                break;
            case LOAD_USER_LIST:
                loadUserList();
                break;
            case LOGIN_USER:
                loginUser();
                break;
            case EXIT:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    // Method to show user menu
    private static void showUserMenu(Scanner scanner) throws BookException, UserException {
        System.out.println("|| User Menu ||");
        System.out.println("10. Search Book");
        System.out.println("11. Add Book to My List");
        System.out.println("12. Show My Book List");
        System.out.println("13. Read Book");
        System.out.println("14. Download Book");
        System.out.println("15. Change Password");
        System.out.println("16. Logoff");
        System.out.print("Choose an option: ");

        int option = readOption(scanner);

        switch (option) {
            case SEARCH:
                searchInBookList();
                break;
            case ADD_BOOK_IN_MY_LIST:
                addBookInMyList();
                break;
            case SHOW_MY_BOOK_LIST:
                showMyBookList();
                break;
            case READ_BOOK:
                readBook();
                break;
            case DOWNLOAD_BOOK:
                downloadBook();
                break;
            case CHANGE_PASSWORD:
                changePassword();
                break;
            case LOG_OFF:
                logoff();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private static int readOption(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // Method to create BookList
    private static void createBookList() {
        System.out.print("Name of file to create booklist: ");
        String fileName = scanner.next();
        scanner.nextLine();
        try {
            bookList.loadBookList(fileName);
            System.out.println("Book list created successfully!");
        } catch (BookException e) {
            System.err.println(e.getMessage());
        }
    }

    // Method to show BookList
    private static void showBookList() throws BookException {
        System.out.println(bookList.toString());
    }

    // Method to search in BookList
    private static void searchInBookList() throws BookException {
        System.out.print("Enter the search string: ");
        String searchString = scanner.nextLine();
        bookList.searchInBookList(searchString);
    }

    // Method to create user
    private static void createUser() throws UserException {
        System.out.println("Enter the User data:");
        System.out.print("-Email: ");
        String email = scanner.nextLine();
        System.out.print("-Password: ");
        String password = scanner.nextLine();

        String planType = null;
        while (true) {
            System.out.print("-Plan type: [1]: trial, [2]: standard, [3]: vip: ");
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    planType = "trial";
                    break;
                case 2:
                    planType = "standard";
                    break;
                case 3:
                    planType = "vip";
                    break;
                default:
                    System.out.println("Invalid option, choose 1, 2, or 3.");
                    continue;
            }
            break;
        }

        String isActivated = null;
        while (true) {
            System.out.print("-Activation: [1]: activated, [2]: deactivated: ");
            int option2 = scanner.nextInt();
            scanner.nextLine();
            switch (option2) {
                case 1:
                    isActivated = "true";
                    break;
                case 2:
                    isActivated = "false";
                    break;
                default:
                    System.out.println("Invalid option, choose 1 or 2.");
                    continue;
            }
            break;
        }

        User user = User.createUser(email, password, planType, isActivated);
        if (user != null) {
            userList.addUser(user);
            System.out.println("User added successfully!");
        } else {
            System.out.println("Failed to add user. Please check the input data.");
        }
    }

    // Method to show userList
    private static void showUserList() throws UserException {
        System.out.println("User List ..................");
        try {
            userList.printUsers();
        } catch (UserException e) {
            System.err.println(e.getMessage());
        }
    }

    // Method to save UserList
    private static void saveUserList() throws UserException {
        System.out.print("Enter the name of the file to save users: ");
        String fileName = scanner.nextLine();
        userList.saveUserList(fileName);
        System.out.println("User list saved successfully!");
    }

    // Method to load UserList
    private static void loadUserList() throws UserException {
        System.out.print("Enter the name of the file to load users: ");
        String fileName = scanner.nextLine();
        userList.loadUserList(fileName);
        System.out.println("User list loaded successfully!");
    }

    // Method to login User
    private static void loginUser() throws UserException {
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        currentUser = userList.loginUser(email, password);
        System.out.println("Login successful!");
    }

    // Method to add Book in User's List
    private static void addBookInMyList() throws BookException, UserException {
        if (currentUser != null && currentUser.getPlan().isActive()) {
            int bookListSize = bookList.getSize();
            System.out.println("Book list size: " + bookListSize);

            if (bookListSize == 0) {
                System.out.println("Book list is empty. Please load or create a book list first.");
                return;
            }

            System.out.print("Enter the book index: ");
            int index;
            try {
                index = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.nextLine(); // Consume the invalid input
                System.err.println("Invalid input. Please enter a valid book index.");
                return;
            }
            scanner.nextLine(); // Consume the newline character

            if (index < 0 || index >= bookListSize) {
                System.err.println("Invalid book index: " + index);
                return;
            }

            try {
                currentUser.addBook(index, bookList);
                System.out.println("Book added to your list.");
            } catch (BookException e) {
                System.err.println(e.getMessage());
                throw new BookException("Failed to add the book to your list.");
            }
        } else {
            throw new UserException("User is not logged in or does not have an active plan.");
        }
    }

    // Method to show User's BookList
    private static void showMyBookList() throws BookException {
        System.out.println("USERBOOKLIST ..................");
        try {
            for (Book book : currentUser.getBookListUser()) {
                System.out.println(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BookException("Failed to display the book list.");
        }
    }

    // Method to read Book
    private static void readBook() throws BookException {
        System.out.print("Enter the index of the book: ");
        String indexStr = scanner.nextLine();
        try {
            int index = Integer.parseInt(indexStr);
            Book book = bookList.findBookByIndex(index);
            if (book != null) {
                if (book.read(currentUser)) {
                    System.out.println("You are reading the book!");
                } else {
                    System.out.println("You cannot read the book.");
                }
            } else {
                System.out.println("Book not found at index: " + index);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid index. Please enter a number.");
        }
    }

    // Method to download Book
    private static void downloadBook() throws BookException {
        System.out.print("Enter the index of the book: ");
        String indexStr = scanner.nextLine();
        try {
            int index = Integer.parseInt(indexStr);
            Book book = bookList.findBookByIndex(index);
            if (book != null) {
                if (book.download(currentUser)) {
                    System.out.println("Book \"" + book.getName() + "\" downloaded successfully");
                } else {
                    System.out.println("Book \"" + book.getName() + "\" cannot be downloaded");
                }
            } else {
                System.out.println("Book not found at index: " + index);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid index. Please enter a number.");
        }
    }

    // Method to change password
    private static void changePassword() throws UserException {
        System.out.print("Enter your new password: ");
        String newPassword = scanner.nextLine();
        currentUser.setPassword(newPassword);
        System.out.println("Password changed successfully!");
    }

    // Method to logoff
    private static void logoff() {
        currentUser = null;
        System.out.println("Logged off successfully!");
    }
}


    


	
    

	
    

    
   

   
	
	
	
	
	
	
	

