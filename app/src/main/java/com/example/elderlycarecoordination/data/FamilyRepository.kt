package com.example.elderlycarecoordination.data

import com.example.elderlycarecoordination.model.FamilyMember
import kotlinx.coroutines.flow.Flow

class FamilyRepository(private val dao: FamilyMemberDao) {

    fun getAllMembers(): Flow<List<FamilyMember>> = dao.getAllMembers()

    fun getMemberById(id: Int): Flow<FamilyMember?> = dao.getMemberById(id)

    suspend fun insertMember(member: FamilyMember): Long = dao.insertMember(member)

    suspend fun updateMember(member: FamilyMember): Int = dao.updateMember(member)

    suspend fun deleteMember(member: FamilyMember): Int = dao.deleteMember(member)
}
