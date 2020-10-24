package bo.com.bolventur.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import bo.com.bolventur.model.Base;
import bo.com.bolventur.model.Event;
import bo.com.bolventur.repository.Repository;
import bo.com.bolventur.repository.RepositoryImpl;

public class MainMenuTab1ViewModel extends AndroidViewModel {
    RepositoryImpl repository;
    public MainMenuTab1ViewModel(@NonNull Application application) {
        super(application);

        repository = new Repository(application);
    }

    public LiveData<Base<List<Event>>> getEvents(String category){
        return repository.getEvents(category);
    }

}