package com.example.elderlycarecoordination.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.elderlycarecoordination.model.FamilyMember
import com.example.elderlycarecoordination.viewmodel.FamilyViewModel

@Composable
fun FamilyMemberListScreen(
    familyViewModel: FamilyViewModel,
    onMemberClick: (FamilyMember) -> Unit
) {
    val members by familyViewModel.familyMembers.collectAsState(initial = emptyList())

    LazyColumn {
        items(members) { member ->
            Column(
                modifier = Modifier.clickable { onMemberClick(member) }
            ) {
                Text(text = member.name)
                Text(text = member.phoneNumber)
                Text(text = member.relationship)
            }
        }
    }
}
