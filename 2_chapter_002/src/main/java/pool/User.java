package pool;

import java.util.Objects;
/**
 * Class Пользователь.
 */
public class User {
    private String userName;
    private String email;

    public User(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.email, this.userName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        User user = (User) obj;
        return Objects.equals(this.userName, user.getUserName()) && Objects.equals(this.email, user.getEmail());
    }

    @Override
    public String toString() {
        return "User {email: " + this.email + ", name: " + this.userName + "}";
    }
}
