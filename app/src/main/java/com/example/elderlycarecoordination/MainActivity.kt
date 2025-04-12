package com.example.elderlycarecoordination

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.compose.*
import kotlinx.coroutines.delay
import androidx.compose.ui.Alignment

import com.example.elderlycarecoordination.ui.screens.*
import com.example.elderlycarecoordination.ui.screen.FamilyMemberListScreen
import com.example.elderlycarecoordination.ui.screen.AddFamilyMemberScreen

import com.example.elderlycarecoordination.viewmodel.EmergencyAlertViewModel
import com.example.elderlycarecoordination.viewmodel.EmergencyAlertViewModelFactory
import com.example.elderlycarecoordination.viewmodel.FamilyViewModel
import com.example.elderlycarecoordination.data.EmergencyAlertRepository
import com.example.elderlycarecoordination.data.FamilyRepository
import com.example.elderlycarecoordination.data.FamilyMemberDatabase

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = FamilyMemberDatabase.getDatabase(applicationContext)
        val alertRepo = EmergencyAlertRepository(database.emergencyAlertDao())
        val alertViewModel = ViewModelProvider(
            this,
            EmergencyAlertViewModelFactory(alertRepo)
        )[EmergencyAlertViewModel::class.java]

        setContent {
            AppNavigation(alertViewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(alertViewModel: EmergencyAlertViewModel) {
    val navController = rememberNavController()

    val context = LocalContext.current
    val database = FamilyMemberDatabase.getDatabase(context)
    val repository = FamilyRepository(database.familyMemberDao())
    val familyViewModel = remember { FamilyViewModel(repository) }

    NavHost(navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen {
                navController.navigate("login") {
                    popUpTo("splash") { inclusive = true }
                }
            }
        }

        composable("login") {
            LoginScreen(onLoginSuccess = {
                navController.navigate("home") {
                    popUpTo("login") { inclusive = true }
                }
            })
        }

        composable("home") {
            Scaffold(bottomBar = { BottomNavigationBar(navController) }) { padding ->
                HomeScreen(navController, padding)
            }
        }

        composable("medication_tracker") {
            Scaffold(bottomBar = { BottomNavigationBar(navController) }) { padding ->
                MedicationTrackerScreen(padding)
            }
        }

        composable("appointment_scheduler") {
            Scaffold(bottomBar = { BottomNavigationBar(navController) }) { padding ->
                AppointmentSchedulerScreen(padding)
            }
        }

        composable("daily_care_log") {
            Scaffold(bottomBar = { BottomNavigationBar(navController) }) { padding ->
                DailyCareLogScreen(padding)
            }
        }

        composable("emergency_alerts") {
            Scaffold(bottomBar = { BottomNavigationBar(navController) }) { padding ->
                EmergencyAlertsScreen(padding = padding, viewModel = alertViewModel)
            }
        }

        composable("family_members") {
            Scaffold(bottomBar = { BottomNavigationBar(navController) }) { padding ->
                FamilyMemberListScreen(
                    familyViewModel = familyViewModel,
                    onMemberClick = { /* optional */ },
                    padding = padding,
                    navController = navController // ✅ Needed for FAB
                )
            }
        }

        composable("add_family_member") {
            Scaffold(bottomBar = { BottomNavigationBar(navController) }) { padding ->
                AddFamilyMemberScreen(
                    familyViewModel = familyViewModel,
                    onMemberAdded = {
                        navController.popBackStack("family_members", false)
                    },
                    padding = padding
                )
            }
        }
    }
}

@Composable
fun SplashScreen(onClick: () -> Unit) {
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
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "App Logo",
                modifier = Modifier.size(250.dp)
            )
        }
    }
}
