package Library;

/*
 * @authors
 * Oisin Murphy - D00191700
 * Patricia Bere - D00193593
 */

public class User {
    String username, password, filepath;
    
    public User() {
        this.username = "";
        this.password = "";
        this.filepath = "";
    }
    
    public User(String username, String password, String filepath) {
        this.username = username;
        this.password = password;
        this.filepath = filepath;
    }
}
