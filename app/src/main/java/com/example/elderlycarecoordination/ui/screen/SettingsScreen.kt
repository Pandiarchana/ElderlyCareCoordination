package com.example.elderlycarecoordination.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.elderlycarecoordination.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Health", fontSize = 20.sp, color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF3A8667))
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Image(
                painter = painterResource(id = R.drawable.profile_icon),
                contentDescription = "Profile",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(100.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            EditableInfoItem("First Name", "Rayan")
            EditableInfoItem("Last Name", "Roy")
            EditableInfoItem("Date of Birth", "13-08-1998")
            EditableInfoItem("Sex", "Male")
            EditableInfoItem("Blood Type", "B Positive")
            EditableInfoItem("Medical Conditions", "Good")
            EditableInfoItem("Allergies", "Weather and Dust")
            EditableInfoItem("Weight", "53.23 kg")
        }
    }
}

@Composable
fun EditableInfoItem(label: String, defaultValue: String) {
    var isEditing by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf(TextFieldValue(defaultValue)) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = label, fontSize = 14.sp, color = Color.Gray)

            if (isEditing) {
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            } else {
                Text(
                    text = text.text,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Text(
            text = if (isEditing) "Save" else "Edit",
            color = Color(0xFF3A8667),
            modifier = Modifier
                .clickable { isEditing = !isEditing }
                .padding(start = 8.dp)
        )
    }
}
