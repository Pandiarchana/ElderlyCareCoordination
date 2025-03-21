package com.example.elderlycarecoordination.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

/**
 * Local ViewModel for the Home screen.
 * Holds a greeting string that we display in HomeScreen.
 */
class HomeViewModel : ViewModel() {
    var greeting = mutableStateOf("Welcome to Elderly Care Coordination!")
}

/**
 * A fully self-contained HomeScreen composable with a top bar, greeting text,
 * and navigation buttons. Accepts padding from the NavHost's Scaffold content lambda.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, padding: PaddingValues) {
    // Green color for the top bar and buttons
    val logoGreen = Color(0xFF3A8667)
    // Obtain the HomeViewModel instance
    val homeViewModel: HomeViewModel = viewModel()

    // A nested Scaffold to define the top bar
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Elderly Care Coordination",
                        fontSize = 20.sp,
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = logoGreen)
            )
        }
    ) { innerPadding ->
        // Apply both the innerPadding from this Scaffold
        // and the external padding from the NavHost's Scaffold
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Display the greeting from the ViewModel
            Text(
                text = homeViewModel.greeting.value,
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Navigation buttons
            CustomButton("Medication Tracker", navController, "medication_tracker", logoGreen)
            CustomButton("Appointment Scheduler", navController, "appointment_scheduler", logoGreen)
            CustomButton("Daily Care Log", navController, "daily_care_log", logoGreen)
            CustomButton("Emergency Alerts", navController, "emergency_alerts", logoGreen)
        }
    }
}

/**
 * A reusable navigation button composable.
 */
@Composable
fun CustomButton(
    text: String,
    navController: NavController,
    route: String,
    color: Color
) {
    Button(
        onClick = { navController.navigate(route) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color)
    ) {
        Text(text, color = Color.White, fontSize = 16.sp)
    }
}
