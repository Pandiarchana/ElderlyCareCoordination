package com.example.elderlycarecoordination.data

import androidx.room.*
import com.example.elderlycarecoordination.model.EmergencyAlert
import kotlinx.coroutines.flow.Flow

@Dao
interface EmergencyAlertDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlert(alert: EmergencyAlert)

    @Query("SELECT * FROM emergency_alert ORDER BY timestamp DESC")
    fun getAllAlerts(): Flow<List<EmergencyAlert>>

    @Delete
    suspend fun deleteAlert(alert: EmergencyAlert)
}
