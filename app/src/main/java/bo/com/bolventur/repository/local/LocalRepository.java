package bo.com.bolventur.repository.local;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import bo.com.bolventur.model.Base;
import bo.com.bolventur.model.Event;
import bo.com.bolventur.repository.local.dao.EventDao;
import bo.com.bolventur.repository.local.db.BolventurDatabase;

public class LocalRepository {
    public static final String LOG = LocalRepository.class.getSimpleName();

    private EventDao eventDao;

    public LocalRepository(Application application) {
        BolventurDatabase db = BolventurDatabase.getDatabase(application);
        eventDao = db.eventDao();
    }

    public LiveData<List<Event>> getEvents() {
        return eventDao.getAll();
    }

    public void update(List<Event> events) {
        eventDao.insert(events);
    }

}
