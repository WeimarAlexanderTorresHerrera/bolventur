package bo.com.bolventur.model.users;

public class User {
    protected String uid;
    protected String email;
    protected String password;
    protected String name;
    protected UserProfile userProfile = UserProfile.REGULAR;

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
}
