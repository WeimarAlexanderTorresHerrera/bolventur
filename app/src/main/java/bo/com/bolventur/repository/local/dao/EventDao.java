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

    @Query("SELECT * FROM event_table ORDER BY title ASC")
    LiveData<List<Event>> getAll();

}
