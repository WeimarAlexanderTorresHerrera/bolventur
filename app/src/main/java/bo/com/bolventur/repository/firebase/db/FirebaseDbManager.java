package bo.com.bolventur.repository.firebase.db;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import bo.com.bolventur.model.Base;
import bo.com.bolventur.model.Event;
import bo.com.bolventur.model.users.User;
import bo.com.bolventur.repository.local.db.BolventurDatabase;
import bo.com.bolventur.utils.Constants;

import static bo.com.bolventur.utils.Constants.ERROR_REGISTER;

public class FirebaseDbManager {

    private FirebaseDatabase db;

    public FirebaseDbManager() {
        db = FirebaseDatabase.getInstance();
    }

    public LiveData<Base<User>> updateUser(User user) {
        MutableLiveData<Base<User>> results = new MutableLiveData<>();
        String path = "/users/" + user.getUid();
        db.getReference(path).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    results.postValue(new Base<>(user));
                } else {
                    results.postValue(new Base<>(ERROR_REGISTER, task.getException()));
                }
            }
        });
        return results;
    }

    public LiveData<Base<String>> addEventToHost (String uidHost, Event event){
        MutableLiveData<Base<String>> results = new MutableLiveData<>();
        String path = Constants.FIREBASE_PATH_EVENT + "/" + uidHost;
        DatabaseReference reference = db.getReference(path).push();
        reference.setValue(event).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    results.postValue(new Base<>(reference.getKey()));
                } else {
                    results.postValue(new Base<>(ERROR_REGISTER, task.getException()));
                }
            }
        });
        return results;
    }

    public LiveData<Base<Boolean>> updateCoverPhoto(String uidHost, String uidEvent, String url){
        MutableLiveData<Base<Boolean>> results = new MutableLiveData<>();
        String path = Constants.FIREBASE_PATH_EVENT + "/" + "null" + "/" + uidEvent;
        path+= "/photo";
        db.getReference(path).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    results.postValue(new Base<>(true));
                } else {
                    results.postValue(new Base<>(ERROR_REGISTER, task.getException()));
                }
            }
        });

        return results;
    }


    public LiveData<Base<List<Event>>> observeHostEvent(String uidHost) {
        MutableLiveData<Base<List<Event>>> results = new MutableLiveData<>();
        return results;
    }

}
