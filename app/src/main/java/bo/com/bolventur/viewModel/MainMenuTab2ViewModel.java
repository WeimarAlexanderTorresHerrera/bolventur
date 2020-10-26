package bo.com.bolventur.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import bo.com.bolventur.model.Base;
import bo.com.bolventur.model.Event;
import bo.com.bolventur.repository.MockRepository;
import bo.com.bolventur.repository.RepositoryImpl;

public class MainMenuTab2ViewModel extends AndroidViewModel {

    RepositoryImpl repository;

    public MainMenuTab2ViewModel(@NonNull Application application) {
        super(application);

        repository = new MockRepository(application);
    }

    public LiveData<Base<List<Event>>> getEvents(String category){
        return repository.getEventsTab2(category);
    }

}