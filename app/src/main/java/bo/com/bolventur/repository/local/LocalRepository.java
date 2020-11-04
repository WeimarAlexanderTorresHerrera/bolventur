package bo.com.bolventur.repository.local;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import bo.com.bolventur.model.Base;
import bo.com.bolventur.model.Event;
import bo.com.bolventur.model.Favorite;
import bo.com.bolventur.repository.local.dao.EventDao;
import bo.com.bolventur.repository.local.dao.FavoriteDao;
import bo.com.bolventur.repository.local.db.BolventurDatabase;

public class LocalRepository {
    public static final String LOG = LocalRepository.class.getSimpleName();

    private final EventDao eventDao;
    private final FavoriteDao favoriteDao;

    public LocalRepository(Application application) {
        BolventurDatabase db = BolventurDatabase.getDatabase(application);

        eventDao = db.eventDao();
        favoriteDao = db.favoriteDao();
    }

    public LiveData<List<Event>> getEventsTab1() {
        return eventDao.getTab1();
    }
    public LiveData<List<Event>> getEventsTab2() {
        return eventDao.getTab2();
    }

    public LiveData<List<Event>> getEventsTab4() {
        return eventDao.getTab4();
    }

    public void update(List<Event> events) {
        new Thread(() -> {
            eventDao.insert(events);
        }).start();
    }

    public LiveData<List<Favorite>> getFavorites() {
        return favoriteDao.getFavorites();
    }

    public void updateFavorites(List<Favorite> favorites) {
        new Thread(() -> {
            favoriteDao.deleteAll();
            favoriteDao.insert(favorites);
        }).start();
    }
}
