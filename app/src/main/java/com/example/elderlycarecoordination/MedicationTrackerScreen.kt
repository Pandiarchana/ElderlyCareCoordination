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

/**
 * Data class representing a single medication.
 */
data class Medication(
    val name: String,
    val time: String,
    val foodInstruction: String,  // e.g., "Before Food" or "After Food"
    val explanation: String       // Additional details to show in an AlertDialog
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicationTrackerScreen(padding: PaddingValues) {
    // Keep track of which medication was clicked (for AlertDialog)
    var selectedMedication by remember { mutableStateOf<Medication?>(null) }

    // A list of 12 medications for a heart patient
    val medicationList = listOf(
        Medication(
            name = "Aspirin 100mg",
            time = "8:00 AM",
            foodInstruction = "Before Food",
            explanation = "Aspirin helps prevent blood clots; take before food for best absorption."
        ),
        Medication(
            name = "Atorvastatin 20mg",
            time = "8:30 AM",
            foodInstruction = "After Food",
            explanation = "Atorvastatin lowers cholesterol; best taken after breakfast."
        ),
        Medication(
            name = "Lisinopril 10mg",
            time = "12:00 PM",
            foodInstruction = "After Food",
            explanation = "Lisinopril is an ACE inhibitor for blood pressure and heart failure."
        ),
        Medication(
            name = "Metoprolol 50mg",
            time = "1:00 PM",
            foodInstruction = "Before Food",
            explanation = "Beta-blocker used to manage heart rate and blood pressure."
        ),
        Medication(
            name = "Clopidogrel 75mg",
            time = "2:00 PM",
            foodInstruction = "After Food",
            explanation = "Prevents platelets from sticking together, reducing clot risk."
        ),
        Medication(
            name = "Spironolactone 25mg",
            time = "6:00 PM",
            foodInstruction = "After Food",
            explanation = "A diuretic that helps remove excess fluid, protecting heart function."
        ),
        Medication(
            name = "Furosemide 40mg",
            time = "7:00 PM",
            foodInstruction = "Before Food",
            explanation = "Loop diuretic to reduce fluid retention; monitor potassium levels."
        ),
        Medication(
            name = "Nitroglycerin (Sublingual)",
            time = "As needed",
            foodInstruction = "For chest pain",
            explanation = "Used under the tongue to relieve angina (chest pain)."
        ),
        Medication(
            name = "Digoxin 0.25mg",
            time = "9:00 PM",
            foodInstruction = "Before Food",
            explanation = "Helps strengthen heart contractions and control heart rate."
        ),
        Medication(
            name = "Warfarin 2mg",
            time = "10:00 PM",
            foodInstruction = "After Food",
            explanation = "Blood thinner to prevent clots; requires INR monitoring."
        ),
        Medication(
            name = "Magnesium Supplement",
            time = "7:30 AM",
            foodInstruction = "After Food",
            explanation = "Helps maintain normal muscle and nerve function, supports heart health."
        ),
        Medication(
            name = "Vitamin D 2000 IU",
            time = "Night, 9:00 PM",
            foodInstruction = "After Food",
            explanation = "Supports bone health and immune function, often recommended for heart patients."
        )
    )

    // MAIN LAYOUT
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
                    text = "Medication Tracker",
                    fontSize = 22.sp,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            }

            // SCROLLABLE MEDICATION LIST
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                medicationList.forEachIndexed { index, med ->
                    MedicationItem(med) { clickedMed ->
                        selectedMedication = clickedMed
                    }
                    if (index < medicationList.size - 1) {
                        Divider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            color = Color.LightGray,
                            thickness = 1.dp
                        )
                    }
                }
            }
        }
    }

    // ALERT DIALOG for selected medication
    if (selectedMedication != null) {
        AlertDialog(
            onDismissRequest = { selectedMedication = null },
            confirmButton = {
                Button(
                    onClick = { selectedMedication = null },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3A8667))
                ) {
                    Text("OK", color = Color.White)
                }
            },
            containerColor = Color(0xFF3A8667),
            title = {
                Text(
                    text = selectedMedication?.name ?: "",
                    fontSize = 20.sp,
                    color = Color.White
                )
            },
            text = {
                Text(
                    text = buildString {
                        append(selectedMedication?.explanation)
                        append("\n\nTime: ")
                        append(selectedMedication?.time)
                        append("\nFood Instruction: ")
                        append(selectedMedication?.foodInstruction)
                    },
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        )
    }
}

/**
 * A single medication item row (clickable).
 */
@Composable
fun MedicationItem(
    medication: Medication,
    onClick: (Medication) -> Unit
) {
    // Color-code the medication title based on instructions or name
    val medicationColor = when {
        medication.name.contains("Aspirin", ignoreCase = true) -> Color(0xFFB71C1C) // Dark Red
        medication.name.contains("Warfarin", ignoreCase = true) -> Color(0xFF0D47A1) // Dark Blue
        medication.name.contains("Nitroglycerin", ignoreCase = true) -> Color(0xFFFFA000) // Amber
        medication.name.contains("Di", ignoreCase = true) -> Color(0xFF1B5E20) // Dark Green for Digoxin
        medication.name.contains("Furosemide", ignoreCase = true) -> Color(0xFF006064) // Teal
        else -> Color.Black
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(medication) }
            .padding(vertical = 6.dp)
    ) {
        Text(
            text = medication.name,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = medicationColor
        )
        Text(
            text = "${medication.time} (${medication.foodInstruction})",
            fontSize = 16.sp,
            color = Color.DarkGray
        )
    }
}
