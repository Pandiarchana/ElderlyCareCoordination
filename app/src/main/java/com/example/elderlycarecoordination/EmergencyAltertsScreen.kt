package com.example.elderlycarecoordination.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.PaddingValues
import com.example.elderlycarecoordination.viewmodel.EmergencyAlertViewModel


// Data class for an emergency alert.
data class EmergencyAlert(
    val title: String,
    val description: String,
    val time: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmergencyAlertsScreen(padding: PaddingValues, viewModel: EmergencyAlertViewModel){
    var selectedAlert by remember { mutableStateOf<EmergencyAlert?>(null) }

    // List of many emergency alerts with different titles, descriptions (including emojis), and times.
    val alertsList = listOf(
        EmergencyAlert(
            title = "Missed Medication Alert",
            description = "🚨 Patient missed blood pressure medication. Please check immediately. 😟",
            time = "9:00 AM"
        ),
        EmergencyAlert(
            title = "Heart Rate Drop Alert",
            description = "⚠️ Significant drop in heart rate detected. Contact caregiver promptly. 💓",
            time = "10:15 AM"
        ),
        EmergencyAlert(
            title = "Fall Detected",
            description = "❗ A fall has been detected. Check on patient and call for assistance if needed. 🤕",
            time = "2:30 PM"
        ),
        EmergencyAlert(
            title = "Overdose Alert",
            description = "🚑 Possible overdose detected. Urgent medical attention is required. 😨",
            time = "3:45 PM"
        ),
        EmergencyAlert(
            title = "Chest Pain Alert",
            description = "🔥 Severe chest pain reported. Immediate emergency response is necessary. 💔",
            time = "8:00 PM"
        ),
        EmergencyAlert(
            title = "Arrhythmia Alert",
            description = "💡 Irregular heartbeat detected. Please monitor closely and consult your doctor. ⚡",
            time = "11:00 AM"
        ),
        EmergencyAlert(
            title = "High Blood Pressure Alert",
            description = "📈 Elevated blood pressure readings. Re-check and adjust medication if needed. 🩺",
            time = "1:30 PM"
        ),
        EmergencyAlert(
            title = "Low Blood Pressure Alert",
            description = "📉 Blood pressure is too low. Ensure patient is safe and consider increasing fluid intake. 💦",
            time = "4:00 PM"
        ),
        EmergencyAlert(
            title = "Shortness of Breath",
            description = "😮 Patient is experiencing shortness of breath. Provide oxygen if possible and call for help. 🌬️",
            time = "6:30 PM"
        ),
        EmergencyAlert(
            title = "Emergency Call Alert",
            description = "📞 Patient has pressed the emergency call button. Immediate response is required. 🚨",
            time = "Anytime"
        )
    )

    Surface(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize(),
        color = Color.White
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Green header (only header is green)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF3A8667))
                    .padding(16.dp)
            ) {
                Text(
                    text = "Emergency Alerts",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
            // Scrollable list of alerts
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                alertsList.forEachIndexed { index, alert ->
                    EmergencyAlertItem(alert) { clickedAlert ->
                        selectedAlert = clickedAlert
                    }
                    if (index < alertsList.size - 1) {
                        Divider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp),
                            color = Color.LightGray,
                            thickness = 1.dp
                        )
                    }
                }
            }
        }
    }

    // AlertDialog to display alert details when an alert is selected
    if (selectedAlert != null) {
        AlertDialog(
            onDismissRequest = { selectedAlert = null },
            confirmButton = {
                Button(
                    onClick = { selectedAlert = null },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3A8667))
                ) {
                    Text("OK", color = Color.White)
                }
            },
            containerColor = Color(0xFF3A8667),
            title = {
                Text(
                    text = selectedAlert?.title ?: "",
                    fontSize = 20.sp,
                    color = Color.White
                )
            },
            text = {
                Text(
                    text = "${selectedAlert?.description}\n\nTime: ${selectedAlert?.time}",
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        )
    }
}

@Composable
fun EmergencyAlertItem(
    alert: EmergencyAlert,
    onClick: (EmergencyAlert) -> Unit
) {
    // New color codes for each alert type (customized for this app)
    val alertColor = when (alert.title) {
        "Missed Medication Alert" -> Color(0xFFD32F2F)    // Bright Red
        "Heart Rate Drop Alert" -> Color(0xFFFFA000)        // Amber
        "Fall Detected" -> Color(0xFF1976D2)                // Blue
        "Overdose Alert" -> Color(0xFFC62828)               // Deep Red
        "Chest Pain Alert" -> Color(0xFFF57C00)             // Orange
        "Arrhythmia Alert" -> Color(0xFF6A1B9A)             // Purple
        "High Blood Pressure Alert" -> Color(0xFF00897B)    // Teal
        "Low Blood Pressure Alert" -> Color(0xFF42A5F5)     // Light Blue
        "Shortness of Breath" -> Color(0xFFE91E63)          // Pink
        "Emergency Call Alert" -> Color(0xFFFF8F00)          // Dark Orange
        else -> Color.Black
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onClick(alert) },
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = "•",
            fontSize = 20.sp,
            color = Color.Gray,
            modifier = Modifier.padding(end = 8.dp)
        )
        Column {
            Text(
                text = "${alert.title} - ${alert.time}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = alertColor
            )
        }
    }
}
