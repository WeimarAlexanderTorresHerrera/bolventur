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

    public LiveData<List<Event>> getEventsTab1() {
        return eventDao.getTab1();
    }

    public LiveData<List<Event>> getEventsTab2() {
        return eventDao.getTab2();
    }
    public LiveData<List<Event>> getEventsTab3() {
        return eventDao.getTab3();
    }
    public LiveData<List<Event>> getEventsTab4() {
        return eventDao.getTab4();
    }

    public void update(List<Event> events) {
        new Thread(() -> {
            eventDao.insert(events);
        }).start();
    }

}
