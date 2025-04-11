package com.example.elderlycarecoordination.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elderlycarecoordination.data.EmergencyAlertRepository
import com.example.elderlycarecoordination.model.EmergencyAlert
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class EmergencyAlertViewModel(
    private val repository: EmergencyAlertRepository
) : ViewModel() {

    // Expose alerts as a StateFlow for Compose to observe
    val alerts: StateFlow<List<EmergencyAlert>> = repository.getAllAlerts()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        // Insert the static list of alerts only once (e.g., on first launch)
        preloadStaticAlerts()
    }

    // Insert predefined emergency alerts into the database
    private fun preloadStaticAlerts() {
        viewModelScope.launch {
            if (repository.getAllAlerts().stateIn(viewModelScope, SharingStarted.Eagerly, emptyList()).value.isEmpty()) {
                val alerts = listOf(
                    EmergencyAlert(type = "Missed Medication Alert", timestamp = "9:00 AM", severity = "High"),
                    EmergencyAlert(type = "Heart Rate Drop Alert", timestamp = "10:15 AM", severity = "High"),
                    EmergencyAlert(type = "Fall Detected", timestamp = "2:30 PM", severity = "Critical"),
                    EmergencyAlert(type = "Overdose Alert", timestamp = "3:45 PM", severity = "Critical"),
                    EmergencyAlert(type = "Chest Pain Alert", timestamp = "8:00 PM", severity = "Emergency"),
                    EmergencyAlert(type = "Arrhythmia Alert", timestamp = "11:00 AM", severity = "Moderate"),
                    EmergencyAlert(type = "High Blood Pressure Alert", timestamp = "1:30 PM", severity = "High"),
                    EmergencyAlert(type = "Low Blood Pressure Alert", timestamp = "4:00 PM", severity = "Moderate"),
                    EmergencyAlert(type = "Shortness of Breath", timestamp = "6:30 PM", severity = "High"),
                    EmergencyAlert(type = "Emergency Call Alert", timestamp = "Anytime", severity = "Emergency")
                )

                alerts.forEach { repository.insertAlert(it) }
            }
        }
    }

    fun addAlert(type: String, timestamp: String, severity: String) {
        val alert = EmergencyAlert(type = type, timestamp = timestamp, severity = severity)
        viewModelScope.launch {
            repository.insertAlert(alert)
        }
    }
}
