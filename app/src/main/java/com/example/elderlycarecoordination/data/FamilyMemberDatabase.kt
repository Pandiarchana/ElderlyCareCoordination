package com.example.elderlycarecoordination.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.elderlycarecoordination.model.FamilyMember
import com.example.elderlycarecoordination.model.EmergencyAlert

@Database(
    entities = [FamilyMember::class, EmergencyAlert::class], // ✅ Added EmergencyAlert entity
    version = 1,
    exportSchema = false
)
abstract class FamilyMemberDatabase : RoomDatabase() {

    abstract fun familyMemberDao(): FamilyMemberDao
    abstract fun emergencyAlertDao(): EmergencyAlertDao // ✅ Added EmergencyAlert DAO

    companion object {
        @Volatile
        private var INSTANCE: FamilyMemberDatabase? = null

        fun getDatabase(context: Context): FamilyMemberDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FamilyMemberDatabase::class.java,
                    "family_member_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
