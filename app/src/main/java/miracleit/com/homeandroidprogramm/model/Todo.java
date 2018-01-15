package miracleit.com.homeandroidprogramm.model;

import io.realm.RealmObject;

/**
 * Created by ozzy on 10.01.2018.
 */

public class Todo extends RealmObject {
    private String name;
    private Long userId;

    @Override
    public String toString() {
        return "Todo: " +
                " name ='" + name + '\'' +
                ", userId =" + userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
