package bo.com.bolventur.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import bo.com.bolventur.model.Base;
import bo.com.bolventur.model.Event;
import bo.com.bolventur.repository.Repository;
import bo.com.bolventur.repository.RepositoryImpl;


public class HostEventViewModel extends AndroidViewModel {
    private RepositoryImpl repository;
    private MutableLiveData<Event> event = new MutableLiveData<>();

    public HostEventViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public MutableLiveData<Event> getEvent() {
        return event;
    }

    public void setEvent(MutableLiveData<Event> event) {
        this.event = event;
    }

    public LiveData<Base<List<Event>>> observeEvents(String uid){
        return repository.observeHostEvent(uid);
    }
}
