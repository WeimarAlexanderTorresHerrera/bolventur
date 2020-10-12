package bo.com.bolventur.model.users;

public class AdminUser extends User{
    public AdminUser(String uid, String email, String password, String name) {
        super(uid, email, password, name);
        userProfile = UserProfile.ADMIN;
    }
}
