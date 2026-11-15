package dev.aaguirre.pc2_dsm.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "asteroids")
data class AsteroidEntity(
    @PrimaryKey val id: String,
    val name: String,
    val isPotentiallyHazardous: Boolean,
    val diameterMin: Double,
    val diameterMax: Double,
    val velocityKmh: String,
    val missDistanceKm: String,
    val closeApproachDate: String,
    val isFavorite: Boolean = false
)
