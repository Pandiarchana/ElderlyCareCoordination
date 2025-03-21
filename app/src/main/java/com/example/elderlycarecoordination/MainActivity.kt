package com.example.elderlycarecoordination

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.*
import kotlinx.coroutines.delay

// Import your separate screen files
import com.example.elderlycarecoordination.ui.screens.LoginScreen
import com.example.elderlycarecoordination.ui.screens.HomeScreen
import com.example.elderlycarecoordination.ui.screens.MedicationTrackerScreen
import com.example.elderlycarecoordination.ui.screens.AppointmentSchedulerScreen
import com.example.elderlycarecoordination.ui.screens.DailyCareLogScreen
import com.example.elderlycarecoordination.ui.screens.EmergencyAlertsScreen
import com.example.elderlycarecoordination.ui.screens.BottomNavigationBar

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "splash") {
        // Splash Screen route
        composable("splash") {
            SplashScreen {
                // After 2 seconds, navigate to "login"
                navController.navigate("login") {
                    popUpTo("splash") { inclusive = true }
                }
            }
        }
        // Login Screen route
        composable("login") {
            LoginScreen(onLoginSuccess = {
                // Navigate to Home, clear login from back stack
                navController.navigate("home") {
                    popUpTo("login") { inclusive = true }
                }
            })
        }
        // Home Screen route
        composable("home") {
            Scaffold(
                bottomBar = { BottomNavigationBar(navController) }
            ) { padding ->
                HomeScreen(navController, padding)
            }
        }
        // Medication Tracker route
        composable("medication_tracker") {
            Scaffold(
                bottomBar = { BottomNavigationBar(navController) }
            ) { padding ->
                MedicationTrackerScreen(padding)
            }
        }
        // Appointment Scheduler route
        composable("appointment_scheduler") {
            Scaffold(
                bottomBar = { BottomNavigationBar(navController) }
            ) { padding ->
                AppointmentSchedulerScreen(padding)
            }
        }
        // Daily Care Log route
        composable("daily_care_log") {
            Scaffold(
                bottomBar = { BottomNavigationBar(navController) }
            ) { padding ->
                DailyCareLogScreen(padding)
            }
        }
        // Emergency Alerts route
        composable("emergency_alerts") {
            Scaffold(
                bottomBar = { BottomNavigationBar(navController) }
            ) { padding ->
                EmergencyAlertsScreen(padding)
            }
        }
    }
}

/**
 * A simple SplashScreen that waits 2 seconds, then calls onClick() to navigate.
 * Displays a logo from R.drawable.logo on a white background.
 */
@Composable
fun SplashScreen(onClick: () -> Unit) {
    // Delay for 2 seconds, then call onClick to navigate
    LaunchedEffect(Unit) {
        delay(2000L)
        onClick()
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable { onClick() },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Replace R.drawable.logo with your actual logo resource
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "App Logo",
                modifier = Modifier.size(250.dp)
            )
        }
    }
}
