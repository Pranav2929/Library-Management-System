package system.user;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import system.exception.UserException;
import system.util.SystemUtil;


public class UserList {
    ArrayList<User> userList = new ArrayList<>();
    
 // Method to add a user to the userList 
    public void addUser(User user) {
        try {
            if (user == null) {
                throw new UserException("User data is invalid or null");
            }

            if (!SystemUtil.isValid(user.getEmail())) {
                throw new UserException("Invalid email format");
            }

            if (!SystemUtil.isValid(user.getPassword())) {
                throw new UserException("Invalid password format");
            }

        } catch (UserException e) {
            System.out.println(e.getMessage());
            
        }
        userList.add(user);
    }
    // Method to load users
    public void loadUserList(String csvFile) throws UserException {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] stringArray = SystemUtil.lineReader(line);
                String email = stringArray.length > 0 ? stringArray[0] : "Null";
                String password = stringArray.length > 1 ? stringArray[1] : "Null";
                String planType = stringArray.length > 2 ? stringArray[2] : null;
                String isActive = stringArray.length > 3 ? stringArray[3] : null;

                
                System.out.println("Email: " + email);
                System.out.println("Password: " + password);
                System.out.println("Plan Type: " + planType);
                System.out.println("Is Active: " + isActive);

                // Create the UserPlan object
                UserPlan plan = UserPlan.createPlan(planType, isActive);
                if (plan == null) {
                    throw new UserException("Error creating user plan for user: " + email);
                }

                User user = new User(email, password, plan);
                userList.add(user);
            }
        } catch (IOException | IllegalArgumentException e) {
            throw new UserException("Error loading user list: " + e.getMessage());
        }
    }
 // Method to print all users in the userList
    public void printUsers() throws UserException {
        if (userList.isEmpty()) {
            throw new UserException("The user list is empty.");
        }

        for (User user : userList) {
            System.out.println(user);
        }
    }
 // Method to save the userList
    public void saveUserList(String csvFile) throws UserException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile))) {
            for (User user : userList) {
                bw.write(user.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new UserException("Error saving user list: " + e.getMessage());
        }
    }
 // Method to authenticate a user based on email and password
    public User loginUser(String email, String password) throws UserException {
        for (User user : userList) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        throw new UserException("Invalid email or password");
    }

    @Override
    public String toString() {
        if (userList.isEmpty()) {
            return "User list is empty";
        }
        StringBuilder sb = new StringBuilder();
        for (User user : userList) {
            sb.append(user).append("\n");
        }
        return sb.toString();
    }
}
