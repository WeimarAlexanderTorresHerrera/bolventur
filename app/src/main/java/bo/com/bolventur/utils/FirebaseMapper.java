package bo.com.bolventur.utils;

import com.google.firebase.auth.FirebaseUser;

import bo.com.bolventur.model.users.User;
import bo.com.bolventur.model.users.UserProfile;

public class FirebaseMapper {

    static public User firebaseUserToUser(FirebaseUser firebaseUser) {
        User user = new User(firebaseUser.getUid(), firebaseUser.getEmail(), null, firebaseUser.getDisplayName());
        user.setUserProfile(UserProfile.HOST);
        return user;
    }
}
