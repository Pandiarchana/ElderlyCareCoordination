package com.example.elderlycarecoordination.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.elderlycarecoordination.data.EmergencyAlertRepository

/**
 * ViewModel factory for EmergencyAlertViewModel.
 * Used to inject the repository manually.
 */
class EmergencyAlertViewModelFactory(
    private val repository: EmergencyAlertRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EmergencyAlertViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EmergencyAlertViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
