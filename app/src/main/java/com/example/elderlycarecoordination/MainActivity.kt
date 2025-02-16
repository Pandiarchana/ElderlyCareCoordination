package com.example.elderlycarecoordination

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation()
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "splash") {
        composable("splash") { SplashScreen { navController.navigate("home") } }
        composable("home") { HomeScreen(navController) }
        composable("medication_tracker") { MedicationTrackerScreen() }
        composable("appointment_scheduler") { AppointmentSchedulerScreen() }
        composable("daily_care_log") { DailyCareLogScreen() }
        composable("emergency_alerts") { EmergencyAlertsScreen() }
    }
}

@Composable
fun SplashScreen(onClick: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier.fillMaxSize().clickable { onClick() },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "App Logo",
                modifier = Modifier.size(250.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val logoGreen = Color(0xFF3A8667)
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Elderly Care Coordination", fontSize = 20.sp, color = Color.White) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = logoGreen)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomButton("Medication Tracker", navController, "medication_tracker", logoGreen)
            CustomButton("Appointment Scheduler", navController, "appointment_scheduler", logoGreen)
            CustomButton("Daily Care Log", navController, "daily_care_log", logoGreen)
            CustomButton("Emergency Alerts", navController, "emergency_alerts", logoGreen)
        }
    }
}

@Composable
fun CustomButton(text: String, navController: NavController, route: String, color: Color) {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicationTrackerScreen() {
    ContentScreen(
        title = "Medication Tracker",
        description = "Example:\nMorning - Aspirin 100mg at 8:00 AM\nNight - Vitamin D 2000 IU at 9:00 PM",
        backgroundColor = Color(0xFF3A8667)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentSchedulerScreen() {
    ContentScreen(
        title = "Appointment Scheduler",
        description = "Example:\nDr. Smith - Cardiologist\nDate: March 15, 10:00 AM\nLocation: City Hospital",
        backgroundColor = Color(0xFF3A8667)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyCareLogScreen() {
    ContentScreen(
        title = "Daily Care Log",
        description = "Example:\n7:30 AM - Breakfast (Oatmeal & Juice)\n12:30 PM - Afternoon Walk\n6:00 PM - Dinner",
        backgroundColor = Color(0xFF3A8667)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmergencyAlertsScreen() {
    ContentScreen(
        title = "Emergency Alerts",
        description = "Example:\n🚨 Missed Medication Alert - Blood Pressure Medicine at 9:00 AM\n🚨 Heart Rate Drop Alert - Contact Caregiver",
        backgroundColor = Color(0xFF3A8667)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentScreen(title: String, description: String, backgroundColor: Color) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = backgroundColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(title, fontSize = 26.sp, color = Color.White)
            Spacer(modifier = Modifier.height(8.dp))
            Text(description, fontSize = 18.sp, color = Color.White)
        }
    }
}
