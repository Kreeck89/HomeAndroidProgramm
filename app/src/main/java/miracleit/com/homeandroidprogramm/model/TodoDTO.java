package miracleit.com.homeandroidprogramm.model;

/**
 * Created by ozzy on 15.01.2018.
 */

public class TodoDTO {

    private String name;
    private Long userID;

    @Override
    public String toString() {
        return "TodoDTO: " +
                "name ='" + name + '\'' +
                ", userID =" + userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }
}
