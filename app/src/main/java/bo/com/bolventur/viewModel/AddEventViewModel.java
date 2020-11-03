package bo.com.bolventur.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import bo.com.bolventur.model.Base;
import bo.com.bolventur.model.Event;
import bo.com.bolventur.repository.Repository;
import bo.com.bolventur.repository.RepositoryImpl;

public class AddEventViewModel extends AndroidViewModel {
    private RepositoryImpl repository;

    public AddEventViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public LiveData<Base<String>> createEvent(String uidHost, Event event){
        return repository.addEventToHost(uidHost, event);
    }


}
