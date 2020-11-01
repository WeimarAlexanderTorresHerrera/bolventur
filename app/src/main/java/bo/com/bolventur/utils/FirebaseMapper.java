package bo.com.bolventur.utils;

import com.google.firebase.auth.FirebaseUser;

import bo.com.bolventur.model.users.User;

public class FirebaseMapper {

    static public User firebaseUserToUser(FirebaseUser firebaseUser) {
        User user = new User(firebaseUser.getUid(), firebaseUser.getEmail(), null, firebaseUser.getDisplayName());
        return user;
    }
}
