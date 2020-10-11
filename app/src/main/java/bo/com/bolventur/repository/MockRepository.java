package bo.com.bolventur.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import bo.com.bolventur.model.Base;
import bo.com.bolventur.model.users.User;
import bo.com.bolventur.utils.Constants;
import bo.com.bolventur.utils.Validations;

public class MockRepository implements RepositoryImpl {
    @Override
    public LiveData<Base<User>> loginEmailPassword(String email, String password) {
        MutableLiveData<Base<User>> results = new MutableLiveData<>();

        if (Validations.isEmpty(email) || Validations.isEmpty(password)) {
            results.postValue(new Base(Constants.ERROR_EMPTY_VALUES, null));
            return results;
        }

        return null;
    }
}
