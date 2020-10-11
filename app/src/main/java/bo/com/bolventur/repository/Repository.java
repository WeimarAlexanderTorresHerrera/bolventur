package bo.com.bolventur.repository;

import androidx.lifecycle.LiveData;

import bo.com.bolventur.model.Base;
import bo.com.bolventur.model.users.User;

public class Repository implements RepositoryImpl {
    @Override
    public LiveData<Base<User>> loginEmailPassword(String email, String password) {
        return null;
    }
}
