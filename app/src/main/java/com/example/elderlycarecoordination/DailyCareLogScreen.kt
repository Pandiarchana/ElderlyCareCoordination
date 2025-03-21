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

// Data class representing a single daily log entry
data class DailyLogEntry(
    val title: String,
    val time: String,
    val explanation: String
)

// Data class grouping daily log entries under a day (e.g., Monday)
data class DayLogs(
    val day: String,
    val logs: List<DailyLogEntry>
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyCareLogScreen(padding: PaddingValues) {
    var selectedLog by remember { mutableStateOf<DailyLogEntry?>(null) }

    // Full-day schedule for each day of the week (Monday to Sunday) with 8 bullet items each.
    val weekLogs = listOf(
        DayLogs(
            "Monday", listOf(
                DailyLogEntry(
                    "Wake Up & Morning Meds",
                    "6:30 AM",
                    "Take morning medications (e.g., blood pressure and heart meds) immediately after waking."
                ),
                DailyLogEntry(
                    "Breakfast",
                    "7:30 AM",
                    "Eat a heart-healthy breakfast such as oatmeal, fruit, and water."
                ),
                DailyLogEntry(
                    "Light Exercise",
                    "9:00 AM",
                    "Engage in gentle stretching or a short walk to improve circulation."
                ),
                DailyLogEntry(
                    "Lunch & Midday Meds",
                    "12:00 PM",
                    "Have a balanced lunch and take any scheduled midday medications."
                ),
                DailyLogEntry(
                    "Afternoon Rest",
                    "2:00 PM",
                    "Take a short nap or relax to reduce fatigue."
                ),
                DailyLogEntry(
                    "Evening Activity",
                    "5:00 PM",
                    "Do light chores or relax with a calm hobby."
                ),
                DailyLogEntry(
                    "Dinner & Evening Meds",
                    "7:00 PM",
                    "Eat a balanced dinner and take any prescribed evening medications."
                ),
                DailyLogEntry(
                    "Bedtime Routine",
                    "10:00 PM",
                    "Wind down, check vitals if necessary, and prepare for sleep."
                )
            )
        ),
        DayLogs(
            "Tuesday", listOf(
                DailyLogEntry(
                    "Wake Up & Meds",
                    "6:30 AM",
                    "Take morning medications and measure your vitals."
                ),
                DailyLogEntry(
                    "Breakfast",
                    "7:30 AM",
                    "Enjoy whole grain toast, eggs, and a fruit smoothie."
                ),
                DailyLogEntry(
                    "Mid-Morning Check",
                    "9:30 AM",
                    "Record blood pressure and note any symptoms."
                ),
                DailyLogEntry(
                    "Lunch",
                    "12:30 PM",
                    "Have a heart-friendly lunch, such as salad with lean protein."
                ),
                DailyLogEntry(
                    "Afternoon Nap",
                    "2:30 PM",
                    "A short nap helps maintain energy levels."
                ),
                DailyLogEntry(
                    "Evening Walk",
                    "5:30 PM",
                    "Take a gentle walk before dinner."
                ),
                DailyLogEntry(
                    "Dinner & Meds",
                    "7:30 PM",
                    "Eat a light dinner and take evening medications."
                ),
                DailyLogEntry(
                    "Prepare for Bed",
                    "10:00 PM",
                    "Wind down with relaxation activities and prepare for sleep."
                )
            )
        ),
        DayLogs(
            "Wednesday", listOf(
                DailyLogEntry(
                    "Wake Up & Meds",
                    "6:30 AM",
                    "Check heart rate and take morning medications."
                ),
                DailyLogEntry(
                    "Breakfast",
                    "7:30 AM",
                    "Have oatmeal, berries, and water for a nutritious start."
                ),
                DailyLogEntry(
                    "Hydration Check",
                    "10:00 AM",
                    "Ensure you’re well-hydrated without overloading on fluids."
                ),
                DailyLogEntry(
                    "Lunch & Meds",
                    "12:00 PM",
                    "Enjoy a balanced lunch along with midday medications."
                ),
                DailyLogEntry(
                    "Afternoon Nap",
                    "2:00 PM",
                    "Take a short nap or relax quietly."
                ),
                DailyLogEntry(
                    "Light Chores",
                    "4:00 PM",
                    "Engage in gentle tasks like folding laundry."
                ),
                DailyLogEntry(
                    "Dinner & Meds",
                    "7:00 PM",
                    "Have dinner and take evening medications as scheduled."
                ),
                DailyLogEntry(
                    "Bedtime Routine",
                    "10:00 PM",
                    "Prepare for sleep with calm activities."
                )
            )
        ),
        DayLogs(
            "Thursday", listOf(
                DailyLogEntry(
                    "Wake Up & Meds",
                    "6:30 AM",
                    "Take morning medications and check vitals."
                ),
                DailyLogEntry(
                    "Breakfast",
                    "7:30 AM",
                    "Have whole grain cereal, fruit, and low-fat milk."
                ),
                DailyLogEntry(
                    "Physical Therapy",
                    "9:00 AM",
                    "Perform prescribed gentle exercises."
                ),
                DailyLogEntry(
                    "Lunch",
                    "12:00 PM",
                    "Eat a balanced meal with lean protein and vegetables."
                ),
                DailyLogEntry(
                    "BP Check",
                    "2:30 PM",
                    "Monitor blood pressure for any unusual readings."
                ),
                DailyLogEntry(
                    "Evening Stroll",
                    "5:00 PM",
                    "Take a light walk to improve circulation."
                ),
                DailyLogEntry(
                    "Dinner & Meds",
                    "7:30 PM",
                    "Enjoy dinner and take evening medications."
                ),
                DailyLogEntry(
                    "Wind Down",
                    "10:00 PM",
                    "Relax and prepare for bed."
                )
            )
        ),
        DayLogs(
            "Friday", listOf(
                DailyLogEntry(
                    "Wake Up & Meds",
                    "6:30 AM",
                    "Record your weight, take morning meds, and check fluid retention."
                ),
                DailyLogEntry(
                    "Breakfast",
                    "7:30 AM",
                    "Egg whites, spinach, and a fruit smoothie."
                ),
                DailyLogEntry(
                    "Mid-Morning Activity",
                    "9:00 AM",
                    "Do some mild stretching or seated exercises."
                ),
                DailyLogEntry(
                    "Lunch",
                    "12:00 PM",
                    "Have salmon with brown rice and vegetables."
                ),
                DailyLogEntry(
                    "Afternoon Nap",
                    "2:00 PM",
                    "Take a 20-30 minute nap."
                ),
                DailyLogEntry(
                    "BP & HR Check",
                    "4:00 PM",
                    "Monitor blood pressure and heart rate."
                ),
                DailyLogEntry(
                    "Dinner & Meds",
                    "7:00 PM",
                    "Enjoy a low-sodium dinner and take medications."
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
                    "Take morning medications upon waking."
                ),
                DailyLogEntry(
                    "Breakfast",
                    "7:45 AM",
                    "Enjoy a relaxed breakfast with eggs and fruit."
                ),
                DailyLogEntry(
                    "Leisure Activity",
                    "10:00 AM",
                    "Engage in a gentle hobby like reading or gardening."
                ),
                DailyLogEntry(
                    "Lunch",
                    "12:30 PM",
                    "Have a light meal such as soup and salad."
                ),
                DailyLogEntry(
                    "Afternoon Nap",
                    "2:30 PM",
                    "A brief nap to recharge energy."
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
                    "Check vitals and take morning medications."
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
                    "Eat a balanced meal with vegetables and lean protein."
                ),
                DailyLogEntry(
                    "Afternoon Rest",
                    "2:00 PM",
                    "Take a short nap or simply relax."
                ),
                DailyLogEntry(
                    "Early Evening Walk",
                    "5:00 PM",
                    "Take a gentle stroll if weather permits."
                ),
                DailyLogEntry(
                    "Dinner & Meds",
                    "7:00 PM",
                    "Have your evening meal and take medications."
                ),
                DailyLogEntry(
                    "Bedtime Routine",
                    "10:00 PM",
                    "Wind down and prepare for a restful sleep."
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
            // GREEN HEADER
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

    // AlertDialog for selected daily log entry
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
                // Use a different color (purple) instead of dark blue
                Text(
                    text = "${logEntry.title} - ${logEntry.time}",
                    fontSize = 16.sp,
                    color = Color(0xFF1B5E20),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
