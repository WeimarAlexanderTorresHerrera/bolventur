package bo.com.bolventur.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import bo.com.bolventur.model.Base;
import bo.com.bolventur.model.users.User;
import bo.com.bolventur.utils.Constants;
import bo.com.bolventur.utils.Validations;

public class MockRepository implements RepositoryImpl {
    private List<User> mockedUsers = new ArrayList<>();

    public MockRepository() {
        User test = new User("0000", "test@test.com", "test", "test");
        mockedUsers.add(test);
    }

    private Base<User> getUser(String email, String password) {
        for (User user: mockedUsers) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return new Base<>(user);
            }
        }
        return new Base<>(Constants.ERROR_LOGIN, null);
    }

    // TODO create user Paola

    @Override
    public LiveData<Base<User>> loginEmailPassword(String email, String password) {
        MutableLiveData<Base<User>> results = new MutableLiveData<>();

        if (Validations.isEmpty(email) && Validations.isEmpty(password)) {
            results.postValue(new Base(Constants.ERROR_EMPTY_LOGIN_VALUES, null));
            return results;
        }
        if (Validations.isEmpty(email)) {
            results.postValue(new Base(Constants.ERROR_EMPTY_EMAIL_VALUE, null));
            return results;
        }
        if (Validations.isEmpty(password)) {
            results.postValue(new Base(Constants.ERROR_EMPTY_PASSWORD_VALUE, null));
            return results;
        }
        if (!Validations.isEmail(email)) {
            results.postValue(new Base(Constants.ERROR_INVALID_EMAIL, null));
            return results;
        }

        results.postValue(getUser(email, password));
        return results;
    }
}
