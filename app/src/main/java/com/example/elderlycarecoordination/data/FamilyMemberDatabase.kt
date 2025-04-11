package com.example.elderlycarecoordination.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.elderlycarecoordination.model.FamilyMember

@Database(entities = [FamilyMember::class], version = 1, exportSchema = false)
abstract class FamilyMemberDatabase : RoomDatabase() {
    abstract fun familyMemberDao(): FamilyMemberDao

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
                instance
            }
        }
    }
}
