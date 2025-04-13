package com.example.elderlycarecoordination.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
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
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FamilyChatListScreen(
    familyViewModel: FamilyViewModel,
    navController: NavController
) {
    val members by familyViewModel.familyMembers.collectAsState()
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Chats", fontSize = 22.sp, color = Color.White) },
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF3A8667)),
            actions = {
                IconButton(onClick = { }) {
                    Icon(Icons.Default.Create, contentDescription = "New Chat", tint = Color.White)
                }
                IconButton(onClick = { }) {
                    Icon(Icons.Default.FilterList, contentDescription = "Filter", tint = Color.White)
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

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(filteredList) { member ->
                ChatItem(
                    member = member,
                    onChatClick = {
                        val encodedName = URLEncoder.encode(member.name, StandardCharsets.UTF_8.toString())
                        navController.navigate("chat/$encodedName")
                    }
                )
            }
        }
    }
}

@Composable
fun ChatItem(member: FamilyMember, onChatClick: () -> Unit) {
    var showCallDialog by remember { mutableStateOf(false) }

    if (showCallDialog) {
        AlertDialog(
            onDismissRequest = { showCallDialog = false },
            title = { Text("Calling ${member.name}...") },
            text = { Text("Simulated voice call...") },
            confirmButton = {
                TextButton(onClick = { showCallDialog = false }) {
                    Text("End Call")
                }
            }
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onChatClick() }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile_icon),
            contentDescription = "Profile",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(text = member.name, fontSize = 18.sp)
            Text(text = member.relationship, fontSize = 14.sp, color = Color.Gray)
        }

        IconButton(
            onClick = { showCallDialog = true },
            modifier = Modifier.size(32.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Call,
                contentDescription = "Call",
                tint = Color(0xFF4CAF50)
            )
        }
    }
}
