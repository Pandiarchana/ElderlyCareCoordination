package com.example.elderlycarecoordination.ui.screens

import android.app.Activity
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckBoxOutlineBlank
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

// Data class for a navigation item.
data class NavItem(
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val route: String
)

@Composable
fun BottomNavigationBar(navController: NavController) {
    val context = LocalContext.current
    val activity = context as? Activity

    var showExitDialog by remember { mutableStateOf(false) }

    val items = listOf(
        NavItem(icon = Icons.AutoMirrored.Filled.ArrowBack, route = "nav_back"),
        NavItem(icon = Icons.Filled.RadioButtonUnchecked, route = "home"),
        NavItem(icon = Icons.Filled.CheckBoxOutlineBlank, route = "nav_exit")
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        containerColor = Color(0xFF3A8667),
        contentColor = Color.White
    ) {
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = null) },
                label = { /* No label */ },
                selected = (currentRoute == item.route),
                onClick = {
                    when (item.route) {
                        "nav_back" -> {
                            if (!navController.popBackStack() && activity != null) {
                                activity.finish()
                            }
                        }
                        "home" -> {
                            navController.navigate("home") {
                                launchSingleTop = true
                                restoreState = true
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                            }
                        }
                        "nav_exit" -> {
                            showExitDialog = true
                        }
                    }
                },
                alwaysShowLabel = false,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    unselectedIconColor = Color.White.copy(alpha = 0.7f)
                )
            )
        }
    }

    if (showExitDialog) {
        AlertDialog(
            onDismissRequest = { showExitDialog = false },
            title = {
                Text("Exit App", color = Color.White)
            },
            text = {
                Text("Do you want to exit the app?", color = Color.White)
            },
            confirmButton = {
                Button(
                    onClick = {
                        showExitDialog = false
                        activity?.finishAffinity()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3A8667))
                ) {
                    Text("Exit", color = Color.White)
                }
            },
            dismissButton = {
                Button(
                    onClick = { showExitDialog = false },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3A8667))
                ) {
                    Text("Cancel", color = Color.White)
                }
            },
            containerColor = Color(0xFF3A8667)
        )
    }
}
