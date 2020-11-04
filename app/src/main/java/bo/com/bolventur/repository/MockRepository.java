package bo.com.bolventur.repository;

import android.app.Application;
import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import bo.com.bolventur.model.Base;
import bo.com.bolventur.model.Event;
import bo.com.bolventur.model.Favorite;
import bo.com.bolventur.model.users.User;
import bo.com.bolventur.utils.Constants;
import bo.com.bolventur.utils.Validations;

public class MockRepository implements RepositoryImpl {
    private List<User> mockedUsers = new ArrayList<>();
    private List<Event> mockedEvent = new ArrayList<>();

    public MockRepository(Application application) {
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

    @Override
    public LiveData<Base<List<Event>>> getEventsTab1(int category) {
        return null;
    }

    @Override
    public LiveData<Base<List<Event>>> getEventsTab4(String category) {
        return null;
    }


    @Override
    public LiveData<Base<User>> register(String email, String password, String name, String confirmPsswd) {
        MutableLiveData<Base<User>> results = new MutableLiveData<>();

        if (Validations.isEmpty(email) || Validations.isEmpty(password) || Validations.isEmpty(name) || Validations.isEmpty(confirmPsswd)) {
            results.postValue(new Base(Constants.ERROR_EMPTY_REGISTER_VALUES, null));
            return results;
        }

        if (!Validations.isEmail(email)) {
            results.postValue(new Base(Constants.ERROR_INVALID_EMAIL, null));
            return results;
        }

        if (password.equals(confirmPsswd)) {
            User user = new User("1111", email, password, name);
            this.mockedUsers.add(user);
            results.postValue(getUser(email, password));
            return results;
        }

        results.postValue(new Base(Constants.ERROR_DONT_MATCH, null));
        return results;
    }

    @Override
    public LiveData<Base<String>> addEventToHost(String uidHost, Event event , Uri image) {
        return null;
    }
  
      @Override
    public LiveData<Base<List<Favorite>>> getFavorites(boolean loadLocal) {
        return null;
    }

    @Override
    public LiveData<Base<List<Event>>> observeHostEvent(String uidHost) {
        return null;
    }

    @Override
    public LiveData<Base<Favorite>> updateFavorite(Favorite favorite) {
        return null;
    }

  
    @Override
    public LiveData<Base<List<Event>>> observeMusicalEvent() {
        return null;
    }

    @Override
    public LiveData<Base<Favorite>> createFavorite(Favorite favorite) {
        return null;
    }
}
