package com.example.elderlycarecoordination.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elderlycarecoordination.data.FamilyRepository
import com.example.elderlycarecoordination.model.FamilyMember
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FamilyViewModel(
    private val repository: FamilyRepository
) : ViewModel() {

    val familyMembers: StateFlow<List<FamilyMember>> =
        repository.getAllMembers().stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    fun addFamilyMember(name: String, phoneNumber: String, relationship: String) {
        val newMember = FamilyMember(name = name, phoneNumber = phoneNumber, relationship = relationship)
        viewModelScope.launch {
            repository.insertMember(newMember)
        }
    }


    fun deleteFamilyMember(member: FamilyMember) {
        viewModelScope.launch {
            repository.deleteMember(member)
        }
    }
}
