package com.example.elderlycarecoordination.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.elderlycarecoordination.R
import com.example.elderlycarecoordination.model.FamilyMember
import com.example.elderlycarecoordination.viewmodel.FamilyViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FamilyChatListScreen(
    familyViewModel: FamilyViewModel,
    navController: NavController
) {
    val members by familyViewModel.familyMembers.collectAsState()
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    Column {
        TopAppBar(
            title = { Text("Chats", fontSize = 22.sp) },
            actions = {
                IconButton(onClick = { }) {
                    Icon(Icons.Default.Create, contentDescription = "New Chat")
                }
                IconButton(onClick = { }) {
                    Icon(Icons.Default.FilterList, contentDescription = "Filter")
                }
            }
        )

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Search or start a new chat") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            singleLine = true
        )

        val filteredList = members.filter {
            it.name.contains(searchQuery.text, ignoreCase = true)
        }

        LazyColumn {
            items(filteredList) { member ->
                ChatItem(member = member, onClick = {
                    println("✅ ChatItem clicked: ${member.name}") // ✅ Check in Logcat
                    navController.navigate("chat/${member.name}")
                })
            }
        }
    }
}

@Composable
fun ChatItem(member: FamilyMember, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.profile_icon),
                contentDescription = "Profile",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(member.name, fontSize = 18.sp)
                Text("Tap to chat", fontSize = 14.sp, color = Color.Gray)
            }

            Text("12:30 PM", fontSize = 12.sp, color = Color.Gray)
        }
    }
}
