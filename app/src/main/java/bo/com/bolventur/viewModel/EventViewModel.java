package bo.com.bolventur.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import bo.com.bolventur.model.Base;
import bo.com.bolventur.model.Event;
import bo.com.bolventur.model.Favorite;
import bo.com.bolventur.repository.Repository;
import bo.com.bolventur.repository.RepositoryImpl;

public class EventViewModel extends AndroidViewModel {

    RepositoryImpl repository;
    private MutableLiveData<Event> event = new MutableLiveData<>();

    public EventViewModel(@NonNull Application application) {
        super(application);

        repository = new Repository(application);
    }

    public LiveData<Base<List<Favorite>>> getFavorites() {
        return repository.getFavorites(false);
    }

    public LiveData<Base<Favorite>> updateFavorite(Favorite favorite) {
        return repository.updateFavorite(favorite);
    }

    public LiveData<Base<Favorite>> createFavorite(Favorite favorite) {
        return repository.createFavorite(favorite);
    }

    public MutableLiveData<Event> getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event.postValue(event);
    }
}
