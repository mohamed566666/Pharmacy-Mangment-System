import java.util.*;

public class Login {
    private String username;
    private String password;
    private boolean isLogin;
    private static HashMap<String, String> acceptedDoctors; // must be Static
    // Static block to initialize accepted doctors
    static {
        acceptedDoctors = new HashMap<>();
        acceptedDoctors.put("Mohamed_Gamal", "mogamal17");
        acceptedDoctors.put("Kareem_badr", "KBR2810");
        acceptedDoctors.put("Abdo_Hamada", "123456789");
        acceptedDoctors.put("Zizo", "meshZezo74");
    }

    // constructor
    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // method to validate if this user and passowrd correct
    private boolean validation() {
        return (acceptedDoctors.containsKey(username) && acceptedDoctors.get(username).equals(password));
    }

    // method to check process of Login
    public boolean login() {
        if (validation()) {
            isLogin = true;
            System.out.println("You have successfully logged in.");
            return true;
        } else {
            System.out.println("Invalid username or password.");
            return false;
        }
    }

    // method to logout from System
    public void logout() {
        if (isLogin) {
            isLogin = false;
            System.out.println("Logged out successfully.");
        } else { // for exception
            System.out.println("You are not logged in.");
        }
    }

    // boolean method to return the Statuas of Login
    public boolean loginStatus() {
        return isLogin;
    }
}
