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

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public void setFavorites(List<Event> favorites) {
        this.favorites = favorites;
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
