package com.example.elderlycarecoordination

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.*

class SplashScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SplashScreen(onClick = {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            })
        }
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
                modifier = Modifier.size(150.dp)
            )
        }
    }
}

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
    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("medication_tracker") { MedicationTrackerScreen() }
        composable("appointment_scheduler") { AppointmentSchedulerScreen() }
        composable("daily_care_log") { DailyCareLogScreen() }
        composable("emergency_alerts") { EmergencyAlertsScreen() }
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    val logoGreen = Color(0xFF3A8667)
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Elderly Care Coordination",
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                backgroundColor = logoGreen
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding).fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { navController.navigate("medication_tracker") }, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(backgroundColor = logoGreen)) {
                Text("Medication Tracker", color = Color.White)
            }
            Button(onClick = { navController.navigate("appointment_scheduler") }, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(backgroundColor = logoGreen)) {
                Text("Appointment Scheduler", color = Color.White)
            }
            Button(onClick = { navController.navigate("daily_care_log") }, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(backgroundColor = logoGreen)) {
                Text("Daily Care Log", color = Color.White)
            }
            Button(onClick = { navController.navigate("emergency_alerts") }, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(backgroundColor = logoGreen)) {
                Text("Emergency Alerts", color = Color.White)
            }
        }
    }
}

@Composable
fun MedicationTrackerScreen() {
    ContentScreen("Medication Tracker", "Manage and track medications efficiently. Example: Morning Medication - Aspirin 100mg at 8:00 AM.", Color(0xFF3A8667))
}

@Composable
fun AppointmentSchedulerScreen() {
    ContentScreen("Appointment Scheduler", "Schedule and track medical appointments. Example: Dr. Smith - Cardiologist, 15th March, 10:00 AM.", Color(0xFF3A8667))
}

@Composable
fun DailyCareLogScreen() {
    ContentScreen("Daily Care Log", "Record daily care routines. Example: Breakfast - Oatmeal & Orange Juice at 7:30 AM.", Color(0xFF3A8667))
}

@Composable
fun EmergencyAlertsScreen() {
    ContentScreen("Emergency Alerts", "Get real-time alerts for emergencies. Example: Missed Medication Alert - Blood Pressure Medicine at 9:00 AM.", Color(0xFF3A8667))
}

@Composable
fun ContentScreen(title: String, description: String, backgroundColor: Color) {
    Surface(modifier = Modifier.fillMaxSize(), color = backgroundColor) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(title, fontSize = 26.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Spacer(modifier = Modifier.height(8.dp))
            Text(description, fontSize = 18.sp, textAlign = TextAlign.Center, color = Color.White)
        }
    }
}
