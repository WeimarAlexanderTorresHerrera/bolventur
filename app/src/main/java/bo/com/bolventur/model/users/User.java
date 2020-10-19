package bo.com.bolventur.model.users;

import java.util.List;

import bo.com.bolventur.model.Event;

public class User {
    protected String uid;
    protected String email;
    protected String password;
    protected String name;
    protected UserProfile userProfile = UserProfile.REGULAR;

    protected List<Event> favorites;

    public User(String uid, String email, String password, String name) {
        this.uid = uid;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public List<Event> getFavorites() {
        return favorites;
    }
}
