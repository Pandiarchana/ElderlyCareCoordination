package com.example.elderlycarecoordination.data

import androidx.room.*
import com.example.elderlycarecoordination.model.FamilyMember
import kotlinx.coroutines.flow.Flow

@Dao
interface FamilyMemberDao {

    // Insert a new family member
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMember(member: FamilyMember): Long

    // Update a family member
    @Update
    suspend fun updateMember(member: FamilyMember): Int

    // Delete a family member
    @Delete
    suspend fun deleteMember(member: FamilyMember): Int

    // Get all family members
    @Query("SELECT * FROM family_members")
    fun getAllMembers(): Flow<List<FamilyMember>>

    // Get member by ID
    @Query("SELECT * FROM family_members WHERE id = :id")
    fun getMemberById(id: Int): Flow<FamilyMember?>
}
