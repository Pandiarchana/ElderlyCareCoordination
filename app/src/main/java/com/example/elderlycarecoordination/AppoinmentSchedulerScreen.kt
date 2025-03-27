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
import kotlin.math.min

data class Appointment(
    val title: String,
    val dateTime: String,
    val explanation: String
)

data class MonthAppointments(
    val month: String,
    val appointments: List<Appointment>
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentSchedulerScreen(padding: PaddingValues) {
    var selectedAppointment by remember { mutableStateOf<Appointment?>(null) }

    // Appointments data for March through December 2025.
    val monthAppointmentsList = listOf(
        // MARCH 2025
        MonthAppointments(
            "March 2025", listOf(
                Appointment(
                    "ECHOCARDIOGRAPHY (3D/4D)",
                    "Monday 17 March, 10:40 AM",
                    "A 3D/4D echocardiogram to check heart function and structure."
                ),
                Appointment(
                    "Heart Failure Pharmacist Clinic (HFPC)",
                    "Monday 17 March, 11:30 AM",
                    "Clinic visit with a pharmacist to discuss heart failure medication."
                ),
                Appointment(
                    "Blood Test (Non-Fasting)",
                    "Monday 17 March, 11:30 AM",
                    "Routine blood test—no fasting required."
                )
            )
        ),
        // APRIL 2025
        MonthAppointments(
            "April 2025", listOf(
                Appointment(
                    "Heart Failure Pharmacist Clinic (HFPC)",
                    "Monday 17 April, 11:30 AM",
                    "Clinic visit with a pharmacist to discuss heart failure medication."
                ),
                Appointment(
                    "Blood Test (Non-Fasting)",
                    "Monday 17 April, 11:30 AM",
                    "Routine blood test—no fasting required."
                ),
                Appointment(
                    "TTSH Card RV with Hui Qing Violet HOON",
                    "Monday 28 April, 4:10 PM",
                    "Card review appointment with Hui Qing Violet HOON."
                )
            )
        ),
        // MAY 2025
        MonthAppointments(
            "May 2025", listOf(
                Appointment(
                    "Consultation",
                    "Monday 12 May, 10:15 AM",
                    "Placeholder consultation for May."
                ),
                Appointment(
                    "Follow-up",
                    "Wednesday 14 May, 1:30 PM",
                    "Placeholder follow-up for May."
                ),
                Appointment(
                    "Check-up",
                    "Friday 16 May, 11:00 AM",
                    "Placeholder check-up for May."
                )
            )
        ),
        // JUNE 2025
        MonthAppointments(
            "June 2025", listOf(
                Appointment(
                    "Consultation",
                    "Monday 10 June, 9:00 AM",
                    "Placeholder consultation for June."
                ),
                Appointment(
                    "Follow-up",
                    "Wednesday 12 June, 2:30 PM",
                    "Placeholder follow-up for June."
                ),
                Appointment(
                    "Check-up",
                    "Friday 13 June, 11:00 AM",
                    "Placeholder check-up for June."
                )
            )
        ),
        // JULY 2025
        MonthAppointments(
            "July 2025", listOf(
                Appointment(
                    "Consultation",
                    "Monday 08 July, 10:00 AM",
                    "Placeholder consultation for July."
                ),
                Appointment(
                    "Follow-up",
                    "Wednesday 10 July, 3:00 PM",
                    "Placeholder follow-up for July."
                ),
                Appointment(
                    "Check-up",
                    "Friday 11 July, 9:30 AM",
                    "Placeholder check-up for July."
                )
            )
        ),
        // AUGUST 2025
        MonthAppointments(
            "August 2025", listOf(
                Appointment(
                    "Consultation",
                    "Monday 05 August, 10:20 AM",
                    "Placeholder consultation for August."
                ),
                Appointment(
                    "Follow-up",
                    "Tuesday 06 August, 2:15 PM",
                    "Placeholder follow-up for August."
                ),
                Appointment(
                    "Check-up",
                    "Thursday 08 August, 11:45 AM",
                    "Placeholder check-up for August."
                )
            )
        ),
        // SEPTEMBER 2025
        MonthAppointments(
            "September 2025", listOf(
                Appointment(
                    "Consultation",
                    "Monday 02 September, 9:50 AM",
                    "Placeholder consultation for September."
                ),
                Appointment(
                    "Follow-up",
                    "Wednesday 04 September, 2:45 PM",
                    "Placeholder follow-up for September."
                ),
                Appointment(
                    "Check-up",
                    "Friday 06 September, 10:30 AM",
                    "Placeholder check-up for September."
                )
            )
        ),
        // OCTOBER 2025
        MonthAppointments(
            "October 2025", listOf(
                Appointment(
                    "Consultation",
                    "Monday 07 October, 10:00 AM",
                    "Placeholder consultation for October."
                ),
                Appointment(
                    "Follow-up",
                    "Wednesday 09 October, 3:15 PM",
                    "Placeholder follow-up for October."
                ),
                Appointment(
                    "Check-up",
                    "Friday 11 October, 9:00 AM",
                    "Placeholder check-up for October."
                )
            )
        ),
        // NOVEMBER 2025
        MonthAppointments(
            "November 2025", listOf(
                Appointment(
                    "Consultation",
                    "Monday 04 November, 10:30 AM",
                    "Placeholder consultation for November."
                ),
                Appointment(
                    "Follow-up",
                    "Tuesday 05 November, 2:00 PM",
                    "Placeholder follow-up for November."
                ),
                Appointment(
                    "Check-up",
                    "Thursday 07 November, 11:00 AM",
                    "Placeholder check-up for November."
                )
            )
        ),
        // DECEMBER 2025
        MonthAppointments(
            "December 2025", listOf(
                Appointment(
                    "Consultation",
                    "Monday 02 December, 9:45 AM",
                    "Placeholder consultation for December."
                ),
                Appointment(
                    "Follow-up",
                    "Wednesday 04 December, 3:00 PM",
                    "Placeholder follow-up for December."
                ),
                Appointment(
                    "Check-up",
                    "Friday 06 December, 10:00 AM",
                    "Placeholder check-up for December."
                )
            )
        )
    )

    // Pagination: 3 months per page
    val pageSize = 3
    var currentPage by remember { mutableStateOf(0) }
    val startIndex = currentPage * pageSize
    val endIndex = min(startIndex + pageSize, monthAppointmentsList.size)
    val currentPageList = monthAppointmentsList.subList(startIndex, endIndex)

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
                // Changed header text to "Appointment Scheduler" only.
                Text(
                    text = "Appointment Scheduler",
                    fontSize = 22.sp,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            }

            // SCROLLABLE APPOINTMENTS
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                currentPageList.forEachIndexed { index, monthAppointments ->
                    AppointmentSection(
                        month = monthAppointments.month,
                        appointments = monthAppointments.appointments
                    ) { appointment ->
                        selectedAppointment = appointment
                    }
                    if (index < currentPageList.size - 1) {
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

            // PAGINATION BUTTONS
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (currentPage > 0) {
                    Button(
                        onClick = { currentPage-- },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3A8667))
                    ) {
                        Text("Previous", color = Color.White)
                    }
                } else {
                    Spacer(modifier = Modifier.width(8.dp))
                }
                if (endIndex < monthAppointmentsList.size) {
                    Button(
                        onClick = { currentPage++ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3A8667))
                    ) {
                        Text("Next", color = Color.White)
                    }
                }
            }
        }
    }

    // ALERT DIALOG for selected appointment details.
    if (selectedAppointment != null) {
        AlertDialog(
            onDismissRequest = { selectedAppointment = null },
            confirmButton = {
                Button(
                    onClick = { selectedAppointment = null },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3A8667))
                ) {
                    Text("OK", color = Color.White)
                }
            },
            containerColor = Color(0xFF3A8667),
            title = {
                Text(
                    text = "${selectedAppointment?.title} Details",
                    fontSize = 20.sp,
                    color = Color.White
                )
            },
            text = {
                Text(
                    text = "${selectedAppointment?.explanation}\n\nScheduled on: ${selectedAppointment?.dateTime}",
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        )
    }
}

@Composable
fun AppointmentSection(
    month: String,
    appointments: List<Appointment>,
    onAppointmentClick: (Appointment) -> Unit
) {
    Text(
        text = month,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        modifier = Modifier.padding(bottom = 8.dp)
    )
    appointments.forEach { appointment ->
        // Color-code bullet items; using same scheme as other pages.
        val appointmentColor = when (appointment.title) {
            "ECHOCARDIOGRAPHY (3D/4D)" -> Color(0xFF0D47A1) // Dark Blue
            "Heart Failure Pharmacist Clinic (HFPC)" -> Color(0xFFB71C1C) // Dark Red
            "Blood Test (Non-Fasting)" -> Color(0xFF1B5E20) // Dark Green
            "TTSH Card RV with Hui Qing Violet HOON" -> Color(0xFF0D47A1) // Dark Blue
            "Consultation" -> Color(0xFF1B5E20) // Dark Green
            "Follow-up" -> Color(0xFF0D47A1)    // Dark Blue
            "Check-up" -> Color(0xFFB71C1C)     // Dark Red
            else -> Color.Black
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
                .clickable { onAppointmentClick(appointment) },
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
                    text = appointment.title,
                    fontSize = 16.sp,
                    color = appointmentColor,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = appointment.dateTime,
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
            }
        }
    }
}
