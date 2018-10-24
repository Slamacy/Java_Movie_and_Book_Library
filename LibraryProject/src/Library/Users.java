package Library;

import java.util.ArrayList;

/*
 * @authors
 * Oisin Murphy - D00191700
 * Patricia Bere - D00193593
 */

public class Users {
    private ArrayList<User> users;
    
    public Users() {
        users = new ArrayList<>();
    }
    
    public void add(User user) {
        users.add(user);
    }
    
    public void displayAllUsers() {
        int i = 0;
        for (User u: users) {
            System.out.println(i + ": " + u);
        }
    }
}
