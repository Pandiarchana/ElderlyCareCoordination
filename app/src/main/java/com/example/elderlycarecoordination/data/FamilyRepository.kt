package com.example.elderlycarecoordination.data

import com.example.elderlycarecoordination.model.FamilyMember
import kotlinx.coroutines.flow.Flow

class FamilyRepository(private val dao: FamilyMemberDao) {

    fun getAllMembers(): Flow<List<FamilyMember>> {
        return dao.getAllMembers()
    }

    suspend fun insertMember(member: FamilyMember) {
        dao.insertMember(member)
    }

    suspend fun deleteMember(member: FamilyMember) {
        dao.deleteMember(member)
    }
}
