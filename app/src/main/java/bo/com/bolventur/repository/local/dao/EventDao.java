package bo.com.bolventur.repository.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import bo.com.bolventur.model.Event;

@Dao
public interface EventDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(List<Event> events);

    @Query("SELECT * FROM event_table")
    LiveData<List<Event>> getAll();

    @Query("DELETE FROM event_table")
    void deleteAll();

}
