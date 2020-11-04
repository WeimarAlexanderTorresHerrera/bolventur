package bo.com.bolventur.repository;

import android.app.Application;
import android.net.Uri;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import bo.com.bolventur.model.Base;
import bo.com.bolventur.model.Event;
import bo.com.bolventur.model.Favorite;
import bo.com.bolventur.model.users.User;
import bo.com.bolventur.repository.api.ApiRepository;
import bo.com.bolventur.repository.firebase.FirebaseRepository;
import bo.com.bolventur.repository.local.LocalRepository;
import bo.com.bolventur.utils.ErrorMapper;

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
    public LiveData<Base<List<Event>>> getEventsTab1(int category) {
        MutableLiveData<Base<List<Event>>> result = new MutableLiveData<>();

        // local
        localRepository.getEventsTab(category).observeForever(events -> result.postValue(new Base<>(events)));

        // API
        ApiRepository.getInstance().getEvents().observeForever(events -> {
            if (events.isSuccessful()) {
                result.postValue(events);

                localRepository.update(events.getData());
            } else {
                result.postValue(new Base<>(events.getErrorCode(), events.getException()));
                Log.e(String.valueOf(events.getErrorCode()), events.getException().getMessage());
            }
        });

        return result;
    }

    @Override
    public LiveData<Base<List<Event>>> getEventsTab3(int category) {
        MutableLiveData<Base<List<Event>>> result = new MutableLiveData<>();

        // local
        localRepository.getEventsTab(category).observeForever(events -> result.postValue(new Base<>(events)));

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
            } else {
                result.postValue(new Base<>(events.getErrorCode(), events.getException()));
                Log.e(String.valueOf(events.getErrorCode()), events.getException().getMessage());
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

    @Override
    public LiveData<Base<List<Event>>> observeMusicalEvent() {
        return FirebaseRepository.getInstance().observeMusicalEvent();
    }

    @Override
    public LiveData<Base<List<Favorite>>> getFavorites(boolean loadLocal) {
        MutableLiveData<Base<List<Favorite>>> result = new MutableLiveData<>();

        if (loadLocal) {
            // local
            localRepository.getFavorites().observeForever(favorites -> result.postValue(new Base<>(favorites)));
        }

        // API
        ApiRepository.getInstance().getFavorites().observeForever(favorites -> {
            if (favorites.isSuccessful()) {
                result.postValue(favorites);

                localRepository.updateFavorites(favorites.getData());
            } else {
                result.postValue(new Base<>(favorites.getErrorCode(), favorites.getException()));
                Log.e(String.valueOf(favorites.getErrorCode()), favorites.getException().getMessage());
            }
        });

        return result;
    }

    @Override
    public LiveData<Base<Favorite>> updateFavorite(Favorite favorite) {
        MutableLiveData<Base<Favorite>> result = new MutableLiveData<>();

        // API
        ApiRepository.getInstance().updateFavorite(favorite).observeForever(favoriteBase -> {
            if (favoriteBase.isSuccessful()) {
                result.postValue(favoriteBase);
            } else {
                result.postValue(new Base<>(favoriteBase.getErrorCode(), favoriteBase.getException()));
                Log.e(String.valueOf(favoriteBase.getErrorCode()), favoriteBase.getException().getMessage());
            }
        });

        return result;
    }

    @Override
    public LiveData<Base<Favorite>> createFavorite(Favorite favorite) {
        MutableLiveData<Base<Favorite>> result = new MutableLiveData<>();

        // API
        ApiRepository.getInstance().createFavorite(favorite).observeForever(favoriteBase -> {
            if (favoriteBase.isSuccessful()) {
                result.postValue(favoriteBase);
            } else {
                result.postValue(new Base<>(favoriteBase.getErrorCode(), favoriteBase.getException()));
                Log.e(String.valueOf(favoriteBase.getErrorCode()), favoriteBase.getException().getMessage());
            }
        });

        return result;
    }
}
