package com.example.elderlycarecoordination.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * A shared composable that displays a title and description on a colored background.
 * It also applies any PaddingValues passed in (usually from a Scaffold content lambda).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentScreen(
    title: String,
    description: String,
    backgroundColor: Color,
    padding: PaddingValues
) {
    Surface(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize(),
        color = backgroundColor
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(title, fontSize = 26.sp, color = Color.White)
            Spacer(modifier = Modifier.height(8.dp))
            Text(description, fontSize = 18.sp, color = Color.White)
        }
    }
}
