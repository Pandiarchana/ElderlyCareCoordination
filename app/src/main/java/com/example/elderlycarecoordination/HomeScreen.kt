package com.example.elderlycarecoordination.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
 * A minimal ViewModel to demonstrate how you might store or retrieve data for HomeScreen.
 * If you don't need a greeting, you can remove this class and references to it.
 */
class HomeViewModel : ViewModel() {
    var greeting = mutableStateOf("Welcome to Elderly Care Coordination!")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    padding: androidx.compose.foundation.layout.PaddingValues
) {
    // If you want to display a greeting, you can use this ViewModel:
    val homeViewModel: HomeViewModel = viewModel()
    // val greetingText = homeViewModel.greeting.value

    val logoGreen = Color(0xFF3A8667)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                // Changed the top bar title to "Welcome, CHINNASAMY PALANIAPPAN." as requested
                title = {
                    Text(
                        text = "Welcome, CHINNASAMY PALANIAPPAN.",
                        fontSize = 20.sp,
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = logoGreen)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)       // from the Scaffold
                .padding(padding)           // from NavHost
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            /*
            // If you want to show the greeting from the ViewModel:
            Text(
                text = greetingText,
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            */

            CustomButton("Medication Tracker", navController, "medication_tracker", logoGreen)
            CustomButton("Appointment Scheduler", navController, "appointment_scheduler", logoGreen)
            CustomButton("Daily Care Log", navController, "daily_care_log", logoGreen)
            CustomButton("Emergency Alerts", navController, "emergency_alerts", logoGreen)
        }
    }
}

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
