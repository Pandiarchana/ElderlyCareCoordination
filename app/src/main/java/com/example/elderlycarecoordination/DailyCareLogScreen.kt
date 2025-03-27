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

// Data class representing one daily log entry.
data class DailyLogEntry(
    val title: String,
    val time: String,
    val explanation: String
)

// Data class grouping log entries under a day.
data class DayLogs(
    val day: String,
    val logs: List<DailyLogEntry>
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyCareLogScreen(padding: PaddingValues) {
    var selectedLog by remember { mutableStateOf<DailyLogEntry?>(null) }

    // Full-day schedule for each day (Monday to Sunday) with 8 items per day.
    val weekLogs = listOf(
        DayLogs(
            "Monday", listOf(
                DailyLogEntry(
                    "Wake Up & Morning Meds",
                    "6:30 AM",
                    "Take morning medications (blood pressure, heart meds) as soon as you wake up."
                ),
                DailyLogEntry(
                    "Breakfast",
                    "7:30 AM",
                    "A nutritious breakfast: oatmeal, fruit, and water."
                ),
                DailyLogEntry(
                    "Light Exercise",
                    "9:00 AM",
                    "Engage in gentle stretching or a short walk to boost circulation."
                ),
                DailyLogEntry(
                    "Lunch & Midday Meds",
                    "12:00 PM",
                    "Have a balanced lunch and take any scheduled midday medications."
                ),
                DailyLogEntry(
                    "Afternoon Rest",
                    "2:00 PM",
                    "Take a short nap or relax quietly to reduce fatigue."
                ),
                DailyLogEntry(
                    "Evening Activity",
                    "5:00 PM",
                    "Perform light chores or enjoy a calm hobby."
                ),
                DailyLogEntry(
                    "Dinner & Evening Meds",
                    "7:00 PM",
                    "Eat a balanced dinner and take your evening medications."
                ),
                DailyLogEntry(
                    "Bedtime Routine",
                    "10:00 PM",
                    "Wind down with relaxation and prepare for sleep."
                )
            )
        ),
        DayLogs(
            "Tuesday", listOf(
                DailyLogEntry(
                    "Wake Up & Meds",
                    "6:30 AM",
                    "Check vitals and take your morning medications."
                ),
                DailyLogEntry(
                    "Breakfast",
                    "7:30 AM",
                    "Enjoy whole grain toast, eggs, and fruit juice."
                ),
                DailyLogEntry(
                    "Mid-Morning Check",
                    "9:30 AM",
                    "Record your blood pressure and note any unusual symptoms."
                ),
                DailyLogEntry(
                    "Lunch & Meds",
                    "12:30 PM",
                    "A heart-friendly lunch with lean protein and vegetables."
                ),
                DailyLogEntry(
                    "Afternoon Nap",
                    "2:30 PM",
                    "Take a brief nap to recharge your energy."
                ),
                DailyLogEntry(
                    "Evening Walk",
                    "5:30 PM",
                    "A gentle walk to aid digestion and circulation."
                ),
                DailyLogEntry(
                    "Dinner & Meds",
                    "7:30 PM",
                    "Have a light dinner and take evening medications."
                ),
                DailyLogEntry(
                    "Prepare for Bed",
                    "10:00 PM",
                    "Wind down and prepare for a restful sleep."
                )
            )
        ),
        DayLogs(
            "Wednesday", listOf(
                DailyLogEntry(
                    "Wake Up & Meds",
                    "6:30 AM",
                    "Check heart rate and take your morning meds."
                ),
                DailyLogEntry(
                    "Breakfast",
                    "7:30 AM",
                    "Oatmeal with berries and water for a healthy start."
                ),
                DailyLogEntry(
                    "Hydration Check",
                    "10:00 AM",
                    "Ensure you’re well-hydrated without excessive fluid intake."
                ),
                DailyLogEntry(
                    "Lunch & Meds",
                    "12:00 PM",
                    "Eat a balanced lunch and take any scheduled midday medications."
                ),
                DailyLogEntry(
                    "Afternoon Rest",
                    "2:00 PM",
                    "Rest or take a short nap to ease fatigue."
                ),
                DailyLogEntry(
                    "Light Chores",
                    "4:00 PM",
                    "Perform gentle tasks such as folding laundry."
                ),
                DailyLogEntry(
                    "Dinner & Meds",
                    "7:00 PM",
                    "Have dinner and take your evening medications on time."
                ),
                DailyLogEntry(
                    "Bedtime Routine",
                    "10:00 PM",
                    "Prepare for sleep with calming activities."
                )
            )
        ),
        DayLogs(
            "Thursday", listOf(
                DailyLogEntry(
                    "Wake Up & Meds",
                    "6:30 AM",
                    "Take your morning medications and check vitals."
                ),
                DailyLogEntry(
                    "Breakfast",
                    "7:30 AM",
                    "Enjoy whole grain cereal, fruit, and low-fat milk."
                ),
                DailyLogEntry(
                    "Physical Therapy",
                    "9:00 AM",
                    "Perform gentle exercises as prescribed by your therapist."
                ),
                DailyLogEntry(
                    "Lunch",
                    "12:00 PM",
                    "Have a nutritious lunch with lean protein and vegetables."
                ),
                DailyLogEntry(
                    "BP Check",
                    "2:30 PM",
                    "Monitor your blood pressure for any unusual changes."
                ),
                DailyLogEntry(
                    "Evening Stroll",
                    "5:00 PM",
                    "Take a light walk to aid digestion."
                ),
                DailyLogEntry(
                    "Dinner & Meds",
                    "7:30 PM",
                    "Enjoy a balanced dinner and take your evening medications."
                ),
                DailyLogEntry(
                    "Wind Down",
                    "10:00 PM",
                    "Relax and prepare for a good night's sleep."
                )
            )
        ),
        DayLogs(
            "Friday", listOf(
                DailyLogEntry(
                    "Wake Up & Meds",
                    "6:30 AM",
                    "Record your vitals, take morning meds, and check for fluid retention."
                ),
                DailyLogEntry(
                    "Breakfast",
                    "7:30 AM",
                    "Egg whites, spinach, and a fruit smoothie."
                ),
                DailyLogEntry(
                    "Mid-Morning Activity",
                    "9:00 AM",
                    "Perform mild stretching or seated exercises."
                ),
                DailyLogEntry(
                    "Lunch",
                    "12:00 PM",
                    "Have a healthy lunch, like a salad with lean protein."
                ),
                DailyLogEntry(
                    "Afternoon Nap",
                    "2:00 PM",
                    "Take a 20-30 minute nap for rest."
                ),
                DailyLogEntry(
                    "BP & HR Check",
                    "4:00 PM",
                    "Record your blood pressure and heart rate."
                ),
                DailyLogEntry(
                    "Dinner & Meds",
                    "7:00 PM",
                    "Enjoy a low-sodium dinner and take your medications."
                ),
                DailyLogEntry(
                    "Bedtime Routine",
                    "10:00 PM",
                    "Wind down and review the next day’s schedule."
                )
            )
        ),
        DayLogs(
            "Saturday", listOf(
                DailyLogEntry(
                    "Late Wake-Up & Meds",
                    "7:00 AM",
                    "Wake up, take morning medications, and relax."
                ),
                DailyLogEntry(
                    "Breakfast",
                    "7:45 AM",
                    "Enjoy a relaxed breakfast with eggs and fruit."
                ),
                DailyLogEntry(
                    "Leisure Activity",
                    "10:00 AM",
                    "Engage in a gentle hobby, such as reading or gardening."
                ),
                DailyLogEntry(
                    "Lunch",
                    "12:30 PM",
                    "Have a light meal, such as soup and salad."
                ),
                DailyLogEntry(
                    "Afternoon Nap",
                    "2:30 PM",
                    "Take a brief nap to recharge."
                ),
                DailyLogEntry(
                    "Family Time",
                    "4:00 PM",
                    "Spend time with family or engage in a social activity."
                ),
                DailyLogEntry(
                    "Dinner & Meds",
                    "7:30 PM",
                    "Eat a heart-friendly dinner and take evening meds."
                ),
                DailyLogEntry(
                    "Prepare for Bed",
                    "10:00 PM",
                    "Wind down with quiet activities before sleep."
                )
            )
        ),
        DayLogs(
            "Sunday", listOf(
                DailyLogEntry(
                    "Wake Up & Meds",
                    "7:00 AM",
                    "Check your vitals and take morning medications."
                ),
                DailyLogEntry(
                    "Breakfast",
                    "7:45 AM",
                    "Enjoy a smoothie or light cereal with fruit."
                ),
                DailyLogEntry(
                    "Morning Leisure",
                    "9:00 AM",
                    "Relax with reading or light household tasks."
                ),
                DailyLogEntry(
                    "Lunch",
                    "12:00 PM",
                    "Eat a balanced meal with veggies and lean protein."
                ),
                DailyLogEntry(
                    "Afternoon Rest",
                    "2:00 PM",
                    "Take a short nap or simply relax."
                ),
                DailyLogEntry(
                    "Early Evening Walk",
                    "5:00 PM",
                    "Take a gentle stroll if the weather permits."
                ),
                DailyLogEntry(
                    "Dinner & Meds",
                    "7:00 PM",
                    "Have your evening meal and take medications."
                ),
                DailyLogEntry(
                    "Bedtime Routine",
                    "10:00 PM",
                    "Wind down and prepare for sleep."
                )
            )
        )
    )

    Surface(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // GREEN HEADER (only header is green)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF3A8667))
                    .padding(16.dp)
            ) {
                Text(
                    text = "Daily Care Log",
                    fontSize = 22.sp,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            }
            // SCROLLABLE LIST OF DAYS
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                weekLogs.forEachIndexed { index, dayLogs ->
                    DayLogSection(dayLogs) { clickedLog ->
                        selectedLog = clickedLog
                    }
                    if (index < weekLogs.size - 1) {
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

    // ALERT DIALOG for selected log entry
    if (selectedLog != null) {
        AlertDialog(
            onDismissRequest = { selectedLog = null },
            confirmButton = {
                Button(
                    onClick = { selectedLog = null },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3A8667))
                ) {
                    Text("OK", color = Color.White)
                }
            },
            containerColor = Color(0xFF3A8667),
            title = {
                Text(
                    text = selectedLog?.title ?: "",
                    fontSize = 20.sp,
                    color = Color.White
                )
            },
            text = {
                Text(
                    text = "${selectedLog?.explanation}\n\nTime: ${selectedLog?.time}",
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        )
    }
}

@Composable
fun DayLogSection(
    dayLogs: DayLogs,
    onLogClick: (DailyLogEntry) -> Unit
) {
    Text(
        text = dayLogs.day,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        modifier = Modifier.padding(bottom = 8.dp)
    )
    dayLogs.logs.forEach { logEntry ->
        // Determine a dynamic color based on keywords in the title.
        val logColor = when {
            logEntry.title.contains("Wake", ignoreCase = true) -> Color(0xFFD32F2F)     // Red
            logEntry.title.contains("Breakfast", ignoreCase = true) -> Color(0xFFFFA000)  // Amber
            logEntry.title.contains("Exercise", ignoreCase = true) -> Color(0xFF1976D2)   // Blue
            logEntry.title.contains("Lunch", ignoreCase = true) -> Color(0xFF388E3C)      // Green
            logEntry.title.contains("Med", ignoreCase = true) -> Color(0xFF0D47A1)        // Dark Blue for meds
            logEntry.title.contains("Rest", ignoreCase = true) -> Color(0xFF8E24AA)       // Purple
            logEntry.title.contains("Dinner", ignoreCase = true) -> Color(0xFFF57C00)       // Orange
            logEntry.title.contains("Bedtime", ignoreCase = true) -> Color(0xFF6D4C41)      // Brown
            else -> Color.Black
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
                .clickable { onLogClick(logEntry) },
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
                    text = "${logEntry.title} - ${logEntry.time}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = logColor
                )
            }
        }
    }
}
