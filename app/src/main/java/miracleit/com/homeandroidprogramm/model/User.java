package miracleit.com.homeandroidprogramm.model;

import io.realm.RealmObject;

/**
 * Created by ozzy on 10.01.2018.
 */

public class User extends RealmObject {

    private static Long idEnteredUser;
    private Long id;
    private String name;
    private String email;
    private String password;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static Long getIdEnteredUser() {
        return idEnteredUser;
    }

    public static void setIdEnteredUser(Long idEnteredUser) {
        User.idEnteredUser = idEnteredUser;
    }
}
