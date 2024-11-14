import java.util.*;

public class Login {
    private String username;
    private String password;
    private boolean isLogin;
    private static HashMap<String, String> acceptedDoctors;

    static {
        acceptedDoctors = new HashMap<>();
        acceptedDoctors.put("Admin1", "123456789");
    }

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private boolean validation() {
        return acceptedDoctors.containsKey(username) && acceptedDoctors.get(username).equals(password);
    }

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

    public void logout() {
        if (isLogin) {
            isLogin = false;
            System.out.println("Logged out successfully.");
        } else {
            System.out.println("You are not logged in.");
        }
    }

    public boolean loginStatus() {
        return isLogin;
    }

    public static void acceptedDoctorFile() {

    }
}
