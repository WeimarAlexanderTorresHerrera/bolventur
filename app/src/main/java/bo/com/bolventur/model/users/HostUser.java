package bo.com.bolventur.model.users;

public class HostUser extends User{
    public HostUser(String uid, String email, String password, String name) {
        super(uid, email, password, name);
        userProfile = UserProfile.HOST;
    }
}
