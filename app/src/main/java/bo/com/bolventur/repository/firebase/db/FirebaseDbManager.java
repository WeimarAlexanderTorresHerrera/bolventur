package bo.com.bolventur.repository.firebase.db;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import bo.com.bolventur.model.Base;
import bo.com.bolventur.model.Event;
import bo.com.bolventur.model.users.User;
import bo.com.bolventur.model.users.UserProfile;
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
        db.getReference(path).push().setValue(event).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    results.postValue(new Base<>("ID Create"));
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

    public LiveData<Base<User>> getUser(String uidUser) {
        MutableLiveData<Base<User>> results = new MutableLiveData<>();
        String path = "/users/" + uidUser;
        db.getReference(path).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String name = snapshot.child("name").getValue().toString();
                String email = snapshot.child("email").getValue().toString();
                String uid = snapshot.child("uid").getValue().toString();
                String profile = snapshot.child("userProfile").getValue().toString();

                User user = new User(uid, email, null, name);
                user.setUserProfile(UserProfile.valueOf(profile));

                Log.e("My user", new Gson().toJson(user));

                results.postValue(new Base<>(user));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return results;
    }

}
