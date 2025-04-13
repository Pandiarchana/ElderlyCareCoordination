package com.example.elderlycarecoordination.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "family_member")
data class FamilyMember(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val phoneNumber: String,
    val relationship: String
)
