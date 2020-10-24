package bo.com.bolventur.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import bo.com.bolventur.model.Base;
import bo.com.bolventur.model.Event;
import bo.com.bolventur.model.users.User;
import bo.com.bolventur.repository.api.ApiRepository;
import bo.com.bolventur.repository.local.LocalRepository;

public class Repository implements RepositoryImpl {
    private LocalRepository localRepository;

    public Repository(Application application) {
        localRepository = new LocalRepository(application);
    }

    @Override
    public LiveData<Base<User>> loginEmailPassword(String email, String password) {
        return null;
    }

    @Override
    public LiveData<Base<List<Event>>> getEvents(String category) {
        return ApiRepository.getInstance().getEvents();
    }

    @Override
    public LiveData<Base<User>> register(String email, String password, String name, String confirmPsswd) {
        return null;
    }
}
