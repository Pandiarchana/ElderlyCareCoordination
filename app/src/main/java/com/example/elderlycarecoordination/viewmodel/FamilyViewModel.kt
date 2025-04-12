package com.example.elderlycarecoordination.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elderlycarecoordination.data.FamilyRepository
import com.example.elderlycarecoordination.model.FamilyMember
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FamilyViewModel(private val repository: FamilyRepository) : ViewModel() {

    // ✅ Expose family members as StateFlow
    val familyMembers: StateFlow<List<FamilyMember>> =
        repository.getAllMembers()
            .map { list -> list.sortedBy { it.name } }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                emptyList()
            )

    // ✅ Function to add a new family member
    fun addMember(name: String, phone: String, relationship: String) {
        viewModelScope.launch {
            val newMember = FamilyMember(
                name = name,
                phoneNumber = phone,
                relationship = relationship
            )
            repository.insertMember(newMember)
        }
    }

    // ✅ (Optional) Update or Delete logic
    fun deleteMember(member: FamilyMember) {
        viewModelScope.launch {
            repository.deleteMember(member)
        }
    }

    fun updateMember(member: FamilyMember) {
        viewModelScope.launch {
            repository.updateMember(member)
        }
    }
}
