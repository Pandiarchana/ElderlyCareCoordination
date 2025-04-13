package com.example.elderlycarecoordination.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "emergency_alert")
data class EmergencyAlert(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val type: String,
    val severity: String,
    val message: String,
    val timestamp: Long
)
