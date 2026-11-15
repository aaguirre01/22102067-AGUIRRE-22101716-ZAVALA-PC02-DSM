package dev.aaguirre.pc2_dsm.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AsteroidDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAsteroids(asteroids: List<AsteroidEntity>)

    @Query("SELECT * FROM asteroids ORDER BY closeApproachDate DESC")
    fun getAllAsteroids(): Flow<List<AsteroidEntity>>

    @Query("SELECT * FROM asteroids WHERE isPotentiallyHazardous = 1 ORDER BY closeApproachDate DESC")
    fun getHazardousAsteroids(): Flow<List<AsteroidEntity>>

    @Query("SELECT * FROM asteroids")
    suspend fun getAllAsteroidsList(): List<AsteroidEntity>

    @Query("SELECT * FROM asteroids WHERE isFavorite = 1 ORDER BY closeApproachDate DESC")
    fun getFavoriteAsteroids(): Flow<List<AsteroidEntity>>

    @Query("UPDATE asteroids SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavorite(id: String, isFavorite: Boolean)
}
