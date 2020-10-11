package bo.com.bolventur.model.users;

public class User {
    protected String uid;
    protected String email;
    protected String password;
    protected String name;
    protected UserProfile userProfile = UserProfile.REGULAR;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public void setName(String name) {
        this.name = name;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }
}
