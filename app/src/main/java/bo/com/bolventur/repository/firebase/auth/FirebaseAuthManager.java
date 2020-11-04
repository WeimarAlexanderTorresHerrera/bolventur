package bo.com.bolventur.repository.firebase.auth;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import bo.com.bolventur.model.Base;
import bo.com.bolventur.model.Event;
import bo.com.bolventur.model.users.User;
import bo.com.bolventur.repository.firebase.db.FirebaseDbManager;
import bo.com.bolventur.utils.Constants;
import bo.com.bolventur.utils.FirebaseMapper;

public class FirebaseAuthManager {

    private FirebaseAuth mAuth;
    private FirebaseDbManager db;

    public FirebaseAuthManager() {
        mAuth = FirebaseAuth.getInstance();
        db = new FirebaseDbManager();
    }
    public LiveData<Base<User>> loginWithEmailPassword(String email, String password) {
        MutableLiveData<Base<User>> results = new MutableLiveData<>();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            db.getUser(firebaseUser.getUid()).observeForever(new Observer<Base<User>>() {
                                @Override
                                public void onChanged(Base<User> userBase) {
                                    results.postValue(userBase);
                                }
                            });
                        } else {
                            results.postValue(new Base<>(Constants.ERROR_LOGIN, task.getException()));
                        }
                    }
                });

        return results;
    }

    public LiveData<Base<User>> registerUser(String email, String password) {
        MutableLiveData<Base<User>> results = new MutableLiveData<>();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            results.postValue(new Base<>(FirebaseMapper.firebaseUserToUser(firebaseUser)));
                        } else {
                            if (task.getException() instanceof FirebaseAuthException) {
                                if (((FirebaseAuthException) task.getException()).getErrorCode().equals("ERROR_EMAIL_ALREADY_IN_USE")) {
                                    results.postValue(new Base<>(Constants.ERROR_REGISTER_EMAIL_ALREADY_EXISTS, task.getException()));
                                } else {
                                    results.postValue(new Base<>(Constants.ERROR_REGISTER_WEAK_PASSWORD, task.getException()));
                                }
                            } else {
                                results.postValue(new Base<>(Constants.ERROR_REGISTER, task.getException()));
                            }
                        }
                    }
                });
        return results;
    }
}
