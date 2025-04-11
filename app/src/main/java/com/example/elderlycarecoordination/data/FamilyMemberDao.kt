package com.example.elderlycarecoordination.data

import androidx.room.*
import com.example.elderlycarecoordination.model.FamilyMember
import kotlinx.coroutines.flow.Flow

@Dao
interface FamilyMemberDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMember(member: FamilyMember): Long

    @Update
    suspend fun updateMember(member: FamilyMember): Int

    @Delete
    suspend fun deleteMember(member: FamilyMember): Int

    @Query("SELECT * FROM family_members")
    fun getAllMembers(): Flow<List<FamilyMember>>

    @Query("SELECT * FROM family_members WHERE id = :id")
    fun getMemberById(id: Int): Flow<FamilyMember?>
}
