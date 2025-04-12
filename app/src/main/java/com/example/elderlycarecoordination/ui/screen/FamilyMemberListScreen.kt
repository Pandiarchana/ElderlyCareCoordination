package com.example.elderlycarecoordination.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.elderlycarecoordination.model.FamilyMember
import com.example.elderlycarecoordination.viewmodel.FamilyViewModel
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.PaddingValues

@Composable
fun FamilyMemberListScreen(
    familyViewModel: FamilyViewModel,
    onMemberClick: (FamilyMember) -> Unit,
    padding: PaddingValues,
    navController: NavController // ✅ Needed for FAB navigation
) {
    val members by familyViewModel.familyMembers.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("add_family_member")
                },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Text("+", color = Color.White)
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            items(members) { member ->
                FamilyMemberItem(member = member, onClick = { onMemberClick(member) })
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun FamilyMemberItem(member: FamilyMember, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "👤 Name: ${member.name}", fontSize = 18.sp)
            Text(text = "📞 Phone: ${member.phoneNumber}", fontSize = 16.sp)
            Text(text = "👥 Relationship: ${member.relationship}", fontSize = 16.sp)
        }
    }
}
