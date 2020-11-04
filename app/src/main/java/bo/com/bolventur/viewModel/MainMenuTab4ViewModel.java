package bo.com.bolventur.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import bo.com.bolventur.model.Base;
import bo.com.bolventur.model.Event;
import bo.com.bolventur.model.Favorite;
import bo.com.bolventur.repository.Repository;
import bo.com.bolventur.repository.RepositoryImpl;

public class MainMenuTab4ViewModel extends AndroidViewModel {

    RepositoryImpl repository;

    public MainMenuTab4ViewModel(@NonNull Application application) {
        super(application);

        repository = new Repository(application);
    }

    public LiveData<Base<List<Event>>> getEvents(String category){
        return repository.getEventsTab4(category);
    }

    public LiveData<Base<List<Favorite>>> getFavorites() {
        return repository.getFavorites(true);
    }
}