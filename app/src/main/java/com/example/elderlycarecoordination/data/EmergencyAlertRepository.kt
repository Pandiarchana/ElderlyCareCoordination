package com.example.elderlycarecoordination.data

import com.example.elderlycarecoordination.model.EmergencyAlert
import kotlinx.coroutines.flow.Flow

class EmergencyAlertRepository(private val alertDao: EmergencyAlertDao) {

    // Insert alert (no need to return Long if DAO returns Unit)
    suspend fun insert(alert: EmergencyAlert) {
        alertDao.insertAlert(alert)
    }

    // Get all alerts
    fun getAllAlerts(): Flow<List<EmergencyAlert>> {
        return alertDao.getAllAlerts()
    }

    // Delete an alert
    suspend fun delete(alert: EmergencyAlert) {
        alertDao.deleteAlert(alert)
    }
}
