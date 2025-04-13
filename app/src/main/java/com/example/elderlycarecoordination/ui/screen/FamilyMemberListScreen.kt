package com.example.elderlycarecoordination.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.elderlycarecoordination.R
import com.example.elderlycarecoordination.model.FamilyMember
import com.example.elderlycarecoordination.viewmodel.FamilyViewModel
import androidx.compose.foundation.layout.PaddingValues

@Composable
fun FamilyMemberListScreen(
    familyViewModel: FamilyViewModel,
    onMemberClick: (FamilyMember) -> Unit,
    padding: PaddingValues,
    navController: NavController
) {
    val members by familyViewModel.familyMembers.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("add_family_member") },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Text("+", color = Color.White)
            }
        }
    ) { innerPadding ->
        if (members.isEmpty()) {
            // Show fallback UI if no members
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No family members yet.\nTap + to add.",
                    fontSize = 18.sp,
                    color = Color.Gray
                )
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(top = 12.dp, bottom = 100.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(innerPadding)
            ) {
                items(members) { member ->
                    WhatsAppStyleFamilyCard(member, onClick = { onMemberClick(member) })
                }
            }
        }
    }
}

@Composable
fun WhatsAppStyleFamilyCard(member: FamilyMember, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile_icon), // Update if you have different icons
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = member.name,
                fontSize = 18.sp,
                color = Color.Black
            )
            Text(
                text = member.relationship,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        Text(
            text = "🕓 Last seen",
            fontSize = 12.sp,
            color = Color.Gray
        )
    }

    Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)
}
