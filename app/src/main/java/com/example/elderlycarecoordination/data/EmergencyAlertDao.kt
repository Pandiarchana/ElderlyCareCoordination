package com.example.elderlycarecoordination.data

import androidx.room.*
import com.example.elderlycarecoordination.model.EmergencyAlert
import kotlinx.coroutines.flow.Flow

/**
 * DAO interface for accessing Emergency Alert data.
 */
@Dao
interface EmergencyAlertDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlert(alert: EmergencyAlert): Long

    @Query("SELECT * FROM emergency_alerts ORDER BY timestamp DESC")
    fun getAllAlerts(): Flow<List<EmergencyAlert>>
}
