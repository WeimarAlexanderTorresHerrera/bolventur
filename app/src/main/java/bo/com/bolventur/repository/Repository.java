package bo.com.bolventur.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import bo.com.bolventur.model.Base;
import bo.com.bolventur.model.Event;
import bo.com.bolventur.model.users.User;

public class Repository implements RepositoryImpl {
    @Override
    public LiveData<Base<User>> loginEmailPassword(String email, String password) {
        return null;
    }

    @Override
    public LiveData<Base<List<Event>>> getEvents(String category) {
        return null;
    }
}
