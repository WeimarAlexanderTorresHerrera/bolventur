package bo.com.bolventur.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import bo.com.bolventur.model.Base;
import bo.com.bolventur.model.Event;
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
    public LiveData<Base<List<Event>>> getEvents(String category) {
        MutableLiveData<Base<List<Event>>> results = new MutableLiveData<>();
        String json = "[{\"uid\":\"1\",\"category\":1,\"photo\":\"https://scontent.fcbb1-2.fna.fbcdn.net/v/t1.0-9/82449993_10157274320873995_2607437897661415424_n.jpg?_nc_cat=102&_nc_sid=8bfeb9&_nc_ohc=w-X2keJC_A0AX8enPlY&_nc_ht=scontent.fcbb1-2.fna&oh=d14bdd2839b8e4862691ef4ba51386dd&oe=5FB1A7BD\",\"title\":\"MISKI WARMI - NO COVER\",\"date\":1602961689,\"location\":\"Muela del Diablo - Cochabamba\",\"host\":\"4\",\"description\":\"Presentamos a Miski Warmi Bolivia en el patio central con lo mejor de la música latinoamericana.\",\"price\":0},{\"uid\":\"2\",\"category\":1,\"photo\":\"https://scontent.fcbb1-2.fna.fbcdn.net/v/t1.0-9/69880364_10162346406780595_7403059472542728192_o.jpg?_nc_cat=102&_nc_sid=e3f864&_nc_ohc=z0GUfP3lHncAX-DANhH&_nc_ht=scontent.fcbb1-2.fna&oh=299bc77a772fb51d6424163e9ccaee2f&oe=5FB02AD5\",\"title\":\"Mon Laferte - La gira de Norma\",\"date\":1602962689,\"location\":\"Teatro al Aire Libre\",\"host\":\"3\",\"description\":\"\uD83C\uDF39¡MON LAFERTE EN LA PAZ!\uD83C\uDF39 Por primera vez llega LaPaz, Bolivia\",\"price\":400},{\"uid\":\"3\",\"category\":1,\"photo\":\"https://scontent.fcbb1-2.fna.fbcdn.net/v/t1.0-9/88241911_1533235096844880_543310949172379648_o.jpg?_nc_cat=101&_nc_sid=340051&_nc_ohc=iomjPYdwCOsAX9cXfNj&_nc_ht=scontent.fcbb1-2.fna&oh=91c23cd733a716a2248456279168b1c7&oe=5FB26356\",\"title\":\"Manuel Roca en la casita\",\"date\":1602960689,\"location\":\"Typica - Sopocachi\",\"host\":\"2\",\"description\":\"¡Vengan a pasarla bomba, junto a #ManuelRoca con lo mejor del Rock Pop en Español!\",\"price\":40},{\"uid\":\"4\",\"category\":1,\"photo\":\"https://scontent.fcbb1-2.fna.fbcdn.net/v/t1.0-9/82331661_10157284391068995_995351803195293696_n.jpg?_nc_cat=103&_nc_sid=8bfeb9&_nc_ohc=5zFHVoYmAocAX8vDUOW&_nc_ht=scontent.fcbb1-2.fna&oh=5c0bd6db0deeadd30775243f4ffd889b&oe=5FAFB7C9\",\"title\":\"PUNK BLUES & THE CHEROKEE THING\",\"date\":1602971689,\"location\":\"Muela del Diablo - Cochabamba\",\"host\":\"1\",\"description\":\"Lo mejor del Punk y del Blues, con versiones de clásicos y temas propios.\",\"price\":25}]";
        Type listType = new TypeToken<ArrayList<Event>>() {
        }.getType();
        List<Event> eventList = new Gson().fromJson(json, listType);
        results.postValue(new Base<>(eventList));
        return results;
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
            User user = new User("0000", email, password, name);
            results.postValue(new Base(user));
            return results;
        }

        results.postValue(new Base(Constants.ERROR_DONT_MATCH, null));
        return results;
    }
}
