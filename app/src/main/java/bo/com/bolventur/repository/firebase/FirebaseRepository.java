package bo.com.bolventur.repository.firebase;

import androidx.lifecycle.LiveData;

import bo.com.bolventur.model.Base;
import bo.com.bolventur.model.users.User;
import bo.com.bolventur.repository.firebase.auth.FirebaseAuthManager;
import bo.com.bolventur.repository.firebase.db.FirebaseDbManager;

public class FirebaseRepository {

    private static FirebaseRepository instance;

    private FirebaseAuthManager auth;
    private FirebaseDbManager db;


    public static FirebaseRepository getInstance() {
        if (instance == null) {
            instance = new FirebaseRepository();
        }
        return instance;
    }

    public FirebaseRepository() {
        auth = new FirebaseAuthManager();
        db = new FirebaseDbManager();
    }

    public LiveData<Base<User>> loginWithEmailPassword(String email, String password) {
        return auth.loginWithEmailPassword(email, password);
    }
}
