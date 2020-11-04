package bo.com.bolventur.repository.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import bo.com.bolventur.model.Favorite;

@Dao
public interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(List<Favorite> favorites);

    @Query("SELECT * FROM favorite_table ORDER BY id ASC")
    LiveData<List<Favorite>> getFavorites();

    @Query("DELETE FROM favorite_table")
    void deleteAll();
}
