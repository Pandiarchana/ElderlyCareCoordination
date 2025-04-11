package com.example.elderlycarecoordination.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class representing an emergency alert.
 * Stored in the Room database under the 'emergency_alerts' table.
 */
@Entity(tableName = "emergency_alerts")
data class EmergencyAlert(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val type: String,         // e.g., "Fall Detected", "Overdose Alert"
    val timestamp: String,    // e.g., "2025-04-11 18:30"
    val severity: String      // e.g., "High", "Medium", "Low"
)
