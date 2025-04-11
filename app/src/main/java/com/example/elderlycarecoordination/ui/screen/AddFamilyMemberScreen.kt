package com.example.elderlycarecoordination.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.elderlycarecoordination.viewmodel.FamilyViewModel

@Composable
fun AddFamilyMemberScreen(
    familyViewModel: FamilyViewModel,
    onNavigateBack: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var relationship by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Phone") },
            modifier = Modifier.padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = relationship,
            onValueChange = { relationship = it },
            label = { Text("Relationship") },
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Button(onClick = {
            familyViewModel.addMember(name, phone, relationship)
            onNavigateBack()
        }) {
            Text("Add Member")
        }
    }
}
