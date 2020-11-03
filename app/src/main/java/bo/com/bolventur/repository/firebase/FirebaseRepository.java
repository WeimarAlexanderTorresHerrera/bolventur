package bo.com.bolventur.repository.firebase;

import android.net.Uri;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.List;

import bo.com.bolventur.model.Base;
import bo.com.bolventur.model.Event;
import bo.com.bolventur.model.users.User;
import bo.com.bolventur.model.users.UserProfile;
import bo.com.bolventur.repository.firebase.auth.FirebaseAuthManager;
import bo.com.bolventur.repository.firebase.db.FirebaseDbManager;
import bo.com.bolventur.repository.firebase.storage.FirebaseStorage;

public class FirebaseRepository {

    private static FirebaseRepository instance;

    private FirebaseAuthManager auth;
    private FirebaseDbManager db;
    private FirebaseStorage storage;


    public static FirebaseRepository getInstance() {
        if (instance == null) {
            instance = new FirebaseRepository();
        }
        return instance;
    }

    public FirebaseRepository() {
        auth = new FirebaseAuthManager();
        db = new FirebaseDbManager();
        storage = new FirebaseStorage();
    }

    public LiveData<Base<User>> loginWithEmailPassword(String email, String password) {
        return auth.loginWithEmailPassword(email, password);
    }

    public LiveData<Base<User>> register(String email, String password, String name) {
        MutableLiveData<Base<User>> results = new MutableLiveData<>();
        auth.registerUser(email, password).observeForever(new Observer<Base<User>>() {
            @Override
            public void onChanged(Base<User> userBase) {
                if(userBase.isSuccessful()){
                    User userRegistered = userBase.getData();
                    userRegistered.setName(name);
                    db.updateUser(userRegistered).observeForever(new Observer<Base<User>>() {
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

    public LiveData<Base<String>> addEventToHost(String uidHost, Event event, Uri image){
        MutableLiveData<Base<String>> results = new MutableLiveData<>();
        //Step1 Create record
        db.addEventToHost(uidHost, event).observeForever(uidEventBase -> {
            if (uidEventBase.isSuccessful()){
                //Step2 upload image
                String uidEvent = uidEventBase.getData();
                storage.uploadEventImage(uidEvent,image).observeForever(urlBase -> {
                    if (urlBase.isSuccessful()){
                        //Step3
                        String url = urlBase.getData();
                        Log.e("CoverPhotoUrl", url);
                        db.updateCoverPhoto(uidHost,uidEvent,url).observeForever(resultUpdateBase -> {
                            if (resultUpdateBase.isSuccessful()){
                                results.postValue(new Base<>(uidEvent));
                            }else {
                                results.postValue(new Base<>(resultUpdateBase.getErrorCode(),resultUpdateBase.getException()));
                            }
                        });
                    }else{
                        results.postValue(new Base<>(urlBase.getErrorCode(), urlBase.getException()));
                    }
                });
            }else{
                results.postValue(new Base<>(uidEventBase.getErrorCode(), uidEventBase.getException()));
            }
        });
        return results;

    }

    public LiveData<Base<List<Event>>> observeHostEvent(String uidHost){
        return db.observeHostEvent(uidHost);
    }
}
