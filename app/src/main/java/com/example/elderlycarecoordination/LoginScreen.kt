package com.example.elderlycarecoordination.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // App Title in your brand color
            Text(
                text = "Elderly Care Coordination",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3A8667)
            )
            Spacer(modifier = Modifier.height(32.dp))

            // Username/Email Field
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Phone number, username, or email") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Password Field
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Login Button styled in green
            Button(
                onClick = { onLoginSuccess() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3A8667))
            ) {
                Text("Log in", color = Color.White)
            }
            Spacer(modifier = Modifier.height(16.dp))

            // "Forgot password?" Link
            Text(
                text = "Forgot password?",
                color = Color(0xFF3A8667),
                modifier = Modifier.clickable { /* Handle forgot password */ }
            )
            Spacer(modifier = Modifier.height(24.dp))

            // OR Divider
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Divider(
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically),
                    color = Color.LightGray
                )
                Text(
                    text = "  OR  ",
                    color = Color.Gray
                )
                Divider(
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically),
                    color = Color.LightGray
                )
            }
            Spacer(modifier = Modifier.height(24.dp))

            // "Don't have an account? Sign up" Link
            Row {
                Text(text = "Don't have an account? ")
                Text(
                    text = "Sign up",
                    color = Color(0xFF3A8667),
                    modifier = Modifier.clickable { /* Handle sign up */ }
                )
            }
        }
    }
}
