package bo.com.bolventur.repository;

import android.app.Application;
import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import bo.com.bolventur.model.Base;
import bo.com.bolventur.model.Event;
import bo.com.bolventur.model.users.User;
import bo.com.bolventur.repository.api.ApiRepository;
import bo.com.bolventur.repository.firebase.FirebaseRepository;
import bo.com.bolventur.repository.local.LocalRepository;

public class Repository implements RepositoryImpl {
    private LocalRepository localRepository;

    public Repository(Application application) {
        localRepository = new LocalRepository(application);
    }

    @Override
    public LiveData<Base<User>> loginEmailPassword(String email, String password) {
        return FirebaseRepository.getInstance().loginWithEmailPassword(email, password);
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


        return result;
    }

    @Override
    public LiveData<Base<List<Event>>> getEventsTab3(String category) {
        MutableLiveData<Base<List<Event>>> result = new MutableLiveData<>();

        // local

        localRepository.getEventsTab3().observeForever(events -> result.postValue(new Base<>(events)));

        return result;
    }

    @Override
    public LiveData<Base<List<Event>>> getEventsTab4(String category) {
        MutableLiveData<Base<List<Event>>> result = new MutableLiveData<>();

        // local
        localRepository.getEventsTab4().observeForever(events -> result.postValue(new Base<>(events)));

        // API
        ApiRepository.getInstance().getEventsFav().observeForever(events -> {
            if (events.isSuccessful()) {
                result.postValue(events);

                localRepository.update(events.getData());
            }
        });


        return result;
    }



    @Override
    public LiveData<Base<User>> register(String email, String password, String name, String confirmPsswd) {
        return FirebaseRepository.getInstance().register(email, password, name);
    }

    @Override
    public LiveData<Base<String>> addEventToHost(String uidHost, Event event, Uri image) {
        return FirebaseRepository.getInstance().addEventToHost(uidHost, event,image);
    }

    @Override
    public LiveData<Base<List<Event>>> observeHostEvent(String uidHost) {
        return FirebaseRepository.getInstance().observeHostEvent(uidHost);
    }
}
