package bo.com.bolventur.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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
    public LiveData<Base<List<Event>>> getEventsTab1(String category) {
        MutableLiveData<Base<List<Event>>> result = new MutableLiveData<>();

        // local
        localRepository.getEventsTab1().observeForever(events -> result.postValue(new Base<>(events)));


        // API
        ApiRepository.getInstance().getEvents().observeForever(events -> {
            if (events.isSuccessful()) {
                result.postValue(events);

                localRepository.update(events.getData());
            }
        });

        return result;
    }

    @Override
    public LiveData<Base<List<Event>>> getEventsTab2(String category) {
        MutableLiveData<Base<List<Event>>> result = new MutableLiveData<>();

        // local
        localRepository.getEventsTab2().observeForever(events -> result.postValue(new Base<>(events)));


        // API
        ApiRepository.getInstance().getEvents().observeForever(events -> {
            if (events.isSuccessful()) {
                result.postValue(events);

                localRepository.update(events.getData());
            }
        });

        return result;
    }

    @Override
    public LiveData<Base<List<Event>>> getEventsTab3(String category) {
        MutableLiveData<Base<List<Event>>> result = new MutableLiveData<>();

        // local
        localRepository.getEventsTab3().observeForever(events -> result.postValue(new Base<>(events)));


        // API
        ApiRepository.getInstance().getEvents().observeForever(events -> {
            if (events.isSuccessful()) {
                result.postValue(events);

                localRepository.update(events.getData());
            }
        });

        return result;
    }

    @Override
    public LiveData<Base<List<Event>>> getEventsTab4(String category) {
        MutableLiveData<Base<List<Event>>> result = new MutableLiveData<>();

        // local
        localRepository.getEventsTab4().observeForever(events -> result.postValue(new Base<>(events)));


        // API
        ApiRepository.getInstance().getEvents().observeForever(events -> {
            if (events.isSuccessful()) {
                result.postValue(events);

                localRepository.update(events.getData());
            }
        });

        return result;
    }





    @Override
    public LiveData<Base<User>> register(String email, String password, String name, String confirmPsswd) {
        return null;
    }
}
