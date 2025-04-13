package com.example.elderlycarecoordination.ui.screens

import android.app.Activity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckBoxOutlineBlank
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

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
        NavItem(icon = Icons.Filled.Home, route = "home"),
        NavItem(icon = Icons.Filled.Settings, route = "settings"),
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
                selected = currentRoute == item.route,
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

                        "settings" -> {
                            navController.navigate("settings") {
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