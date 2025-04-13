package com.example.elderlycarecoordination.data

import androidx.room.*
import com.example.elderlycarecoordination.model.FamilyMember
import kotlinx.coroutines.flow.Flow

@Dao
interface FamilyMemberDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMember(member: FamilyMember)

    @Delete
    suspend fun deleteMember(member: FamilyMember)

    @Query("SELECT * FROM family_member")
    fun getAllMembers(): Flow<List<FamilyMember>>
}
