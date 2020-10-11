package bo.com.bolventur.repository;

import androidx.lifecycle.LiveData;

import bo.com.bolventur.model.Base;
import bo.com.bolventur.model.users.User;

public interface RepositoryImpl {

    LiveData<Base<User>> loginEmailPassword(String email, String password);

}
