package bo.com.bolventur.repository.local.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import bo.com.bolventur.model.Event;
import bo.com.bolventur.model.Favorite;
import bo.com.bolventur.repository.local.dao.EventDao;
import bo.com.bolventur.repository.local.dao.FavoriteDao;
import bo.com.bolventur.utils.MapConverterTicket;

@Database(entities = {Event.class, Favorite.class}, version = 1, exportSchema = false)
@TypeConverters(MapConverterTicket.class)
public abstract class BolventurDatabase extends RoomDatabase {

    private static volatile  BolventurDatabase INSTANCE;

    static public BolventurDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (BolventurDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), BolventurDatabase.class, "bolventur_database").build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract EventDao eventDao();

    public abstract FavoriteDao favoriteDao();
}
