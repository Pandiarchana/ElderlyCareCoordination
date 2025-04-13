package com.example.elderlycarecoordination.data

import com.example.elderlycarecoordination.model.FamilyMember
import kotlinx.coroutines.flow.Flow

/**
 * Repository to abstract access to the FamilyMember data source.
 */
class FamilyRepository(private val dao: FamilyMemberDao) {

    // Returns a Flow of all family members
    fun getAllMembers(): Flow<List<FamilyMember>> = dao.getAllMembers()

    // Returns a Flow for a specific family member by ID
    fun getMemberById(id: Int): Flow<FamilyMember?> = dao.getMemberById(id)

    // Insert new family member
    suspend fun insertMember(member: FamilyMember): Long = dao.insertMember(member)

    // Update existing family member
    suspend fun updateMember(member: FamilyMember): Int = dao.updateMember(member)

    // Delete a family member
    suspend fun deleteMember(member: FamilyMember): Int = dao.deleteMember(member)
}
