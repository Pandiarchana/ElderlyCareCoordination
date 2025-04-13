package com.example.elderlycarecoordination.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.elderlycarecoordination.model.FamilyMember
import com.example.elderlycarecoordination.model.EmergencyAlert

@Database(
    entities = [FamilyMember::class, EmergencyAlert::class],
    version = 2, // 🔄 Increase version if adding new tables!
    exportSchema = false
)
abstract class FamilyMemberDatabase : RoomDatabase() {
    abstract fun familyMemberDao(): FamilyMemberDao
    abstract fun emergencyAlertDao(): EmergencyAlertDao

    companion object {
        @Volatile
        private var INSTANCE: FamilyMemberDatabase? = null

        fun getDatabase(context: Context): FamilyMemberDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FamilyMemberDatabase::class.java,
                    "family_member_database"
                )
                    .fallbackToDestructiveMigration() // 🔄 Auto-reset on version mismatch
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
