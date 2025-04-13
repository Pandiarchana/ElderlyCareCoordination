package com.example.elderlycarecoordination.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elderlycarecoordination.data.EmergencyAlertRepository
import com.example.elderlycarecoordination.model.EmergencyAlert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EmergencyAlertViewModel(
    private val repository: EmergencyAlertRepository
) : ViewModel() {

    private val _alerts = MutableStateFlow<List<EmergencyAlert>>(emptyList())
    val alerts: StateFlow<List<EmergencyAlert>> = _alerts

    init {
        loadAlerts()
    }

    private fun loadAlerts() {
        viewModelScope.launch {
            repository.getAllAlerts().collect {
                _alerts.value = it
            }
        }
    }

    fun sendAlert(type: String, severity: String, message: String) {
        viewModelScope.launch {
            val alert = EmergencyAlert(
                type = type,
                severity = severity,
                message = message,
                timestamp = System.currentTimeMillis()
            )
            repository.insert(alert)
        }
    }

    fun deleteAlert(alert: EmergencyAlert) {
        viewModelScope.launch {
            repository.delete(alert)
        }
    }
}
