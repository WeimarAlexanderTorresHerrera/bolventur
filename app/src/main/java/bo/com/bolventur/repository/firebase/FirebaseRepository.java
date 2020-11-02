package bo.com.bolventur.repository.firebase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

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

    public LiveData<Base<User>> register(String email, String password) {
        MutableLiveData<Base<User>> results = new MutableLiveData<>();
        auth.registerUser(email, password).observeForever(new Observer<Base<User>>() {
            @Override
            public void onChanged(Base<User> userBase) {
                if(userBase.isSuccessful()){
                    db.updateUser(userBase.getData()).observeForever(new Observer<Base<User>>() {
                        @Override
                        public void onChanged(Base<User> userBase) {
                            results.postValue(userBase);
                        }
                    });
                }else{
                    results.postValue(userBase);
                }
            }
        });
        return results;
    }
}
