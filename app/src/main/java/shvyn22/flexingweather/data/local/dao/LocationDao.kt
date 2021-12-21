package shvyn22.flexingweather.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import shvyn22.flexingweather.data.local.model.Location

@Dao
interface LocationDao {
    @Query("SELECT * FROM Location ORDER BY woeId DESC")
    fun getItems(): Flow<List<Location>>

    @Query("SELECT EXISTS (SELECT 1 FROM Location WHERE woeId = :id)")
    fun isFavorite(id: Int): Flow<Boolean>

    @Insert
    suspend fun insert(item: Location)

    @Query("DELETE FROM Location WHERE woeId = :id")
    suspend fun delete(id: Int)
}