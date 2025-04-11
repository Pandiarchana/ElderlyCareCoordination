package com.example.elderlycarecoordination.data

import com.example.elderlycarecoordination.model.EmergencyAlert
import kotlinx.coroutines.flow.Flow

/**
 * Repository to manage data operations for Emergency Alerts.
 * Delegates to the EmergencyAlertDao.
 */
class EmergencyAlertRepository(private val dao: EmergencyAlertDao) {

    fun getAllAlerts(): Flow<List<EmergencyAlert>> = dao.getAllAlerts()

    suspend fun insertAlert(alert: EmergencyAlert): Long = dao.insertAlert(alert)
}
